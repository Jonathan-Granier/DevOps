package client.java.fichiers_client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

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
		//TODO - Appeler les fonctions de jon et faire des sexy moves
		//Connection au serveur
	
		
		System.out.println("PROJET DEVOPS - Client");
		
		//Entrée des requêtes sur la ligne de commande (boucle)
		System.out.print(">");
		
		
			//Parser la commande
			//Si "quit" tout quitter
			
			
			//Envoyer la cmd_struct au serveur
			
			
			//attendre/recevoir une réponse
			//traiter la réponse et afficher en fonction du résultat
			
		
	}

	private Request parse_cmd(String cmd){
		Request req = new Request();
		
		String delimiters = "[]+";
		String[] vals = cmd.split(delimiters);
		
		if(vals.length != 3){
			return null;
		}
		
		
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
