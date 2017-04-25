package client.java.fichiers_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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

	private final  ArrayList<Object> data = new ArrayList<Object>();
	
	private Socket socket; 
	
	public static void main(String[] args) throws UnknownCmdException {
		//Connection au serveur
		Echange_Client share = null;
		String cmd;
		Request req;
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
			
			req = parse_cmd(cmd);
		
			try {
				ans = share.faire_un_echange(req);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
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
			case "setString" :
				if(vals.length != 3){
					return null;
				}
				req.op_code = Request.opCode.setString;
				req.data = vals[2];
				break;
			
			case "setInt" :
				if(vals.length != 3){
					return null;
				}
				req.op_code = Request.opCode.setInt;
				req.data = Integer.parseInt(vals[2]);
				break;
			
			case "getAtIndex" :
				if(vals.length != 3){
					return null;
				}
				req.op_code = Request.opCode.get_elem_of_list_at_index;
				req.data = vals[2];
				break;
			
			case "get" :
				if(vals.length != 2){
					return null;
				}
				req.op_code = Request.opCode.get;
				req.data = null;
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
				req.data = vals[2];
				break;
			
			case "list_remove" :
				if(vals.length != 3){
					return null;
				}
				req.op_code = Request.opCode.list_remove;
				req.data = vals[2];
				break;
			
			case "remove" :
				if(vals.length != 3){
					return null;
				}
				req.op_code = Request.opCode.remove;
				req.data = vals[2];
				break;
			default :
				throw new UnknownCmdException();
		}
		
		req.reqNumber = operationCode.reqNumber();
		
		return req;
	}
	
	/**
	 * Rempli une liste de données pré-remplies pour les tests
	 */
	private void fill_data(){
		data.add(entier1);
		data.add(entier2);
		data.add(chaine1);
		data.add(chaine2);
		
		data.add(listeEntier);
		data.add(listeString);
	}
	
	
	/////////////////////// OBJETS PRE-REMPLIS ///////////////////////
	Object entier1 = new Integer(2);
	Object entier2 = new Integer(42424242);
	
	Object chaine1 = new String("Bonjour");
	Object chaine2 = new String("Coucou comment ca va?");
	
	Object listeEntier = new ArrayList<Integer>().add(12);

	Object listeString = new ArrayList<String>().add("chaine de caracteres");
	
}
