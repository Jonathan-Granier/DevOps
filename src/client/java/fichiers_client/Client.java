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

/**
 * Classe interface du client : envoi des requêtes
 * @Author lavvsont
 * 
 */
public class Client {

	private final  ArrayList<Object> data = new ArrayList<Object>();
	
	private Socket socket; 

	private static int numReq = 0;
	
	public static void main(String[] args) {
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
			} catch (IOException e) {
				e.printStackTrace();
			}	
				
			//TODO - traiter la réponse et afficher en fonction du résultat
			
		}
		
	}

	private static Request parse_cmd(String cmd){
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
			
			case "setObject" :
				if(vals.length != 3){
					return null;
				}
				req.op_code = Request.opCode.setObject;
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
				//Unknown CMD exception
				return null;
		}
		
		req.reqNumber = numReq;
		numReq++;
		
		return req;
	}
	
	
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
	Object entier2 = new Integer(42);
	
	Object chaine1 = new String("Bonjour");
	Object chaine2 = new String("Coucou comment ca va?");
	
	Object listeEntier = new ArrayList<Integer>().add(12);

	Object listeString = new ArrayList<String>().add("chaine de caracteres");
	
}
