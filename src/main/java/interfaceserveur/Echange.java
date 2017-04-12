package main.java.interfaceserveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Thread qui effectue un echange client serveur - coté server
 * @author granijon
 *
 */

public class Echange implements Runnable {
	
	private Socket socket;
	private InputStream in;
    private OutputStream out;
	
	private String data_reception;
	private String data_emmision;
	private boolean Maintient_connexion;
	
	public Echange(Socket socket)
	{
		Maintient_connexion = true;
		try {
			
			in = socket.getInputStream();
			out = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	
	@Override
	/**
	 * Effectue en boucle reception de donné , puis traitement puis envoi du retour 
	 */
	public void run() {
		while(Maintient_connexion)
		{
			
			
			try {
				reception();
				//TODO avec data_reception
				emmision();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Récupere ce que le client à envoyé sous la forme d'un tableau de bytes
	 */
	private void reception(){
		//TODO recepetion , gaby
		
	}
	
	/**
	 * Envoie au client le retour de ça requete sous la forme d'un tableau de bytes
	 * @throws IOException
	 */
	private void emmision() throws IOException{
        //TODO envoie, gaby
		out.flush();
        
	}
	
	/**
	 * Stop la connexion
	 * @throws IOException
	 */
	private void stopconnexion() throws IOException{
		Maintient_connexion = false;
		socket.close();
		
	}

}
