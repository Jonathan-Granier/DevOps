package client.java.fichiers_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {

	private final  ArrayList<Object> data = new ArrayList<Object>();
	
	private Socket socket; 
	BufferedReader in;
	PrintWriter out;


	private int port;
	
	public static void main(String[] args) {
		//TODO - Appeler les fonctions de jon et faire des sexy moves
		
		
	}
	
	/*
	private void connection(){
		
		try {
			socket = new Socket(InetAddress.getLocalHost(),port);
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
		
		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	private void deconnexion(){
		
		try {
			socket.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
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
	Object entier2 = new Integer(42);
	
	Object chaine1 = new String("Bonjour");
	Object chaine2 = new String("Coucou comment ca va?");
	
	Object listeEntier = new ArrayList<Integer>().add(12);

	Object listeString = new ArrayList<String>().add("chaine de caracteres");
	
}
