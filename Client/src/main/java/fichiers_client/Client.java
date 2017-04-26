package main.java.fichiers_client;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.echange_client.Echange_Client;
import main.java.commande_structure.Answer;
import main.java.commande_structure.Request;

/**
 * Classe interface du client : envoi des requêtes
 * @Author lavvsont
 * 
 */
public class Client {
	
	public static void main(String[] args) {
		boolean loop = false;
		//Connection au serveur
		Echange_Client share = null;
		String cmd;
		Request req = new Request();
		Answer ans = new Answer();
		Scanner input = new Scanner(System.in);
		
		try {
			share = new Echange_Client();
			System.out.println("PROJET DEVOPS - Client");
			
			do {
			 //Entrée des requêtes sur la ligne de commande (boucle)
				
				System.out.print(">");
				
				cmd = input.nextLine();
				
				if(cmd.equals("exit") || cmd.equals("quit"))
					loop = false;
					
				else if(cmd.equals("man") || cmd.equals("help"))
					print_list_cmd();
				
				if(loop){
					try {
						req = parse_cmd(cmd);
					} catch (UnknownCmdException | InvalidInstructionException e1) {
						e1.printStackTrace();
					}
				
					try {
						ans = share.faire_un_echange(req);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
						
					print_ans(ans);
				}
			}
			while(loop);
			share.stop_connexion();
		} catch (IOException e) {
			System.out.println("Echec de connexion");
		}
	}

	private static void print_list_cmd(){
		System.out.println("Liste des commandes dispo :");
		System.out.println("*set <cle> <valeur> -- sauvegarde la valeur <valeur> avec la cle <cle> ");
		System.out.println("*get <cle> -- récupère la valeur sauvegardee avec la cle <cle>");
		System.out.println("*increment <cle> <valeur> -- incremente de <valeur> la valeur sauvegardee avec la cle <cle> ");
		System.out.println("*list_add <cle> <valeur> -- ajoute <valeur> a la liste sauvegardee avec <cle>. Cree une liste a cle si elle n'existe pas deja");
		System.out.println("*getAtIndex <cle> <indice> -- recupere l'element sauvegarde a l'<indice> dans la liste <cle> ");
		System.out.println("*list_remove <cle> <indice> -- supprime l'element a l'<indice> dans la liste <cle>");
		System.out.println("*remove <cle> -- supprime la donnee sauvegardee avec <cle> et <cle> elle meme");
		System.out.println();
	}
	
	/**
	 * Feedback de la requête (affiche des messages de validité ou d'erreur en fonction de l'état de la réponse)
	 * @param ans la réponse à une requête
	 */
	private static void print_ans(Answer ans){
		System.out.println("\treq "+ ans.reqNumber +" : ");
		switch(ans.return_code){
		case OK:
			if(ans.data.equals(null))
				System.out.print("OK");
			else{
				//TODO
			}
			System.out.println();
			break;
		case NonExistingKey :
			System.out.println("Non existing key");
			break;
		case WrongDataType :
			System.out.println("Wrong data type");
			break;
		default :
			System.out.println("Nil");
			break;
		}
	}
	
	/**
	 * Parse l'entrée du client et rempli une structure request en fonction
	 * @param cmd l'input du client
	 * @return la requete correspondante
	 * @throws UnknownCmdException 
	 * @throws InvalidInstructionException 
	 */
	public static Request parse_cmd(String cmd) throws UnknownCmdException, InvalidInstructionException{
		Request req = new Request();
		
		String[] vals = cmd.split("[ ]+");

		System.out.println();
		if(vals.length<2)
			return null;
		
		req.key = vals[1];
		
		switch (vals[0]){
			case "set" :
			case "SET" :
				if(vals.length != 3){
					return null;
				}
				req.op_code = Request.opCode.set;
				req.data = assign_data(vals[2],req.op_code);
				break;
			
			case "get" :
			case "GET" :
				if(vals.length != 2){
					return null;
				}
				req.op_code = Request.opCode.get;
				req.data = null;
				break;

			case "getAtIndex" :
				if(vals.length != 3){
					return null;
				}
				req.op_code = Request.opCode.get_elem_of_list_at_index;
				req.data = assign_data(vals[2],req.op_code);
				break;
				
			case "increment" :
				if(vals.length != 2){
					return null;
				}
				req.op_code = Request.opCode.increment;
				req.data = null;
				break;
			
			case "list_add" :
				if(vals.length != 3){
					return null;
				}
				req.op_code = Request.opCode.list_add;
				req.data = assign_data(vals[2],req.op_code);
				break;
			
			case "list_remove" :
				if(vals.length != 3){
					return null;
				}
				req.op_code = Request.opCode.list_remove;
				req.data = assign_data(vals[2],req.op_code);
				break;
			
			case "remove" :
				if(vals.length != 2){
					return null;
				}
				req.op_code = Request.opCode.remove;
				req.data = null;
				break;
			default :
				throw new UnknownCmdException();
		}
		
		req.reqNumber = Request.getReqNumber();
		
		return req;
	}
	
	/**
	 * Fonction qui assigne une data à la requête.
	 * Si la donnée commence par un '-' alors on assignera une donnée pré-remplie à la data
	 * @param val la donnée entrée au clavier par l'utilisateur
	 * @return la donnée qui sera envoyée en requête
	 * @throws InvalidInstructionException 
	 */
	private static Serializable assign_data(String val, Request.opCode op) throws InvalidInstructionException{
		if(val.startsWith("-")){
			switch(val.substring(1)){
			case "entier1" :
				return   entier1;
			case "entier2" :
				return  entier2;
			case "chaine1" :
				if(op.equals(Request.opCode.increment) || op.equals(Request.opCode.get_elem_of_list_at_index))
					throw new InvalidInstructionException();
				return  chaine1;
			case "chaine2" :
				if(op.equals(Request.opCode.increment) || op.equals(Request.opCode.get_elem_of_list_at_index))
					throw new InvalidInstructionException();
				return  chaine2;
			case "listeEntier" :
				if(op.equals(Request.opCode.increment) || op.equals(Request.opCode.get_elem_of_list_at_index)
						|| op.equals(Request.opCode.list_add) || op.equals(Request.opCode.list_remove))
					throw new InvalidInstructionException();
				return  listeEntier;
			case "listeString" :
				if(op.equals(Request.opCode.increment) || op.equals(Request.opCode.get_elem_of_list_at_index)
						|| op.equals(Request.opCode.list_add) || op.equals(Request.opCode.list_remove))
					throw new InvalidInstructionException();
				return  listeString;
			case "listeVide" :
				if(op.equals(Request.opCode.increment) || op.equals(Request.opCode.get_elem_of_list_at_index)
						|| op.equals(Request.opCode.list_add) || op.equals(Request.opCode.list_remove))
					throw new InvalidInstructionException();
				return listeVide;
			default :
				return val;
			}
		}
			
		return val;
	}
	
	/////////////////////// OBJETS PRE-REMPLIS ///////////////////////
	static Serializable entier1 = new Integer(2);
	static Serializable entier2 = new Integer(42424242);
	
	static Serializable chaine1 = new String("Bonjour");
	static Serializable chaine2 = new String("Coucou comment ca va?");
	
	static Serializable listeEntier = new ArrayList<Integer>().add(12);

	static Serializable listeString = new ArrayList<String>().add("chaine de caracteres");

	static Serializable listeVide = new ArrayList<Object>();
}

