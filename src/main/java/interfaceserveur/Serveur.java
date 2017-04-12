package main.java.interfaceserveur;

import java.io.IOException;
import java.net.ServerSocket;
/**
 * Classe qui lance le serveur
 * @author granijon
 *
 */

public class Serveur {
	private final static int numSocket = 1337;
	public static ServerSocket ss = null;

	public static Thread t;

	
	/**
	 * Lance le serveur sur un socket (1337) et lance le thread qui accepte les connexions 
	 * @throws IOException
	 */
	public void StartServeur() throws IOException{
		ss = new ServerSocket(1337);

        System.out.println("Le serveur est à l'écoute du port "+ss.getLocalPort());
        t = new Thread(new Accepter_connexion(ss));
        t.start();
	}
	
}