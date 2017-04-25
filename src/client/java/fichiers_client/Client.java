package client.java.fichiers_client;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import client.java.echange_client.Echange_Client;
import main.java.commande_structure.Answer;
import main.java.commande_structure.Request;
import main.java.interfaceserveur.operationCode;

/**
 * Classe interface du client : envoi des requêtes
 * @Author lavvsont
 * 
 */
public class Client {
	
	public static void main(String[] args) {
		//Connection au serveur
		Echange_Client share = null;
		String cmd;
		Request req = new Request();
		Answer ans;
		Scanner input = new Scanner(System.in);
		try {
			share = new Echange_Client();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("PROJET DEVOPS - Client");
		
		while(true){
			//Entrée des requêtes sur la ligne de commande (boucle)
			
			System.out.print(">");
			
			cmd = input.nextLine();
			
			if(cmd.equals("exit"))
				System.exit(0);
			
			try {
				req = parse_cmd(cmd);
			} catch (UnknownCmdException e1) {
				e1.printStackTrace();
			}
		
			try {
				ans = share.faire_un_echange(req);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
				
			//TODO - traiter la réponse et afficher en fonction du résultat
			
		}
		
	}

	/**
	 * Parse l'entrée du client et rempli une structure request en fonction
	 * @param cmd l'input du client
	 * @return la requete correspondante
	 * @throws UnknownCmdException 
	 */
	private static Request parse_cmd(String cmd) throws UnknownCmdException{
		Request req = new Request();
		
		String delimiters = "[]+";
		String[] vals = cmd.split(delimiters);

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
				req.data = assign_data(vals[2]);
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
				req.data = assign_data(vals[2]);
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
				req.data = assign_data(vals[2]);
				break;
			
			case "list_remove" :
				if(vals.length != 3){
					return null;
				}
				req.op_code = Request.opCode.list_remove;
				req.data = assign_data(vals[2]);
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
		
		req.reqNumber = operationCode.reqNumber();
		
		return req;
	}
	
	/**
	 * Fonction qui assigne une data à la requête.
	 * Si la donnée commence par un '-' alors on assignera une donnée pré-remplie à la data
	 * @param val la donnée entrée au clavier par l'utilisateur
	 * @return la donnée qui sera envoyée en requête
	 */
	private static Serializable assign_data(String val){
		if(val.startsWith("-")){
			switch(val.substring(1)){
			case "entier1" :
				return (Serializable) entier1;
			case "entier2" :
				return (Serializable) entier2;
			case "chaine1" :
				return (Serializable) chaine1;
			case "chaine2" :
				return (Serializable) chaine2;
			case "listeEntier" :
				return (Serializable) listeEntier;
			case "listeString" :
				return (Serializable) listeString;
			case "listeVide" :
				return (Serializable) listeVide;
			default :
				return val;
			}
		}
			
		return val;
	}
	
	/////////////////////// OBJETS PRE-REMPLIS ///////////////////////
	static Object entier1 = new Integer(2);
	static Object entier2 = new Integer(42424242);
	
	static Object chaine1 = new String("Bonjour");
	static Object chaine2 = new String("Coucou comment ca va?");
	
	static Object listeEntier = new ArrayList<Integer>().add(12);

	static Object listeString = new ArrayList<String>().add("chaine de caracteres");

	static Object listeVide = new ArrayList<Object>();
}
