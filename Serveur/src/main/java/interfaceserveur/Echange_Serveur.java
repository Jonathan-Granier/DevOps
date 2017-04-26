package main.java.interfaceserveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import main.java.commande_structure.Answer;
import main.java.commande_structure.Request;
import main.java.exception.BDDNotFoundException;
import main.java.stockage_cle_valeur.RequestHandler;
import main.java.stockage_cle_valeur.ServerManager;

/**
 * Thread qui effectue un echange client serveur - coté server
 * @author granijon
 *
 */

public class Echange_Serveur implements Runnable {
	
	private Socket socket;
	private ObjectInputStream in;
    private ObjectOutputStream out;
	
    
    
	private boolean Maintient_connexion;
	
	
	
	private RequestHandler requestHandler;
	
	public Echange_Serveur(Socket socket,ServerManager serverManager)
	{
		requestHandler = new RequestHandler(serverManager);
		
		Maintient_connexion = true;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	/**
	 * Effectue en boucle reception de donné , puis traitement puis envoi du retour 
	 */
	public void run() {
		Request request;
		Answer answer;
		
		while(Maintient_connexion)
		{
			try {	
				//System.out.println("[Echange_Server] J'attends une nouvelle requete");
				
				request = reception();
				System.out.println("[Echange_Server] J'ai recu quelque chose : "+ request.toString());
				
				answer = requestHandler.handleRequest(request);
				
				System.out.println("[Echange_Server] J'envoi quelque chose" );
				emmision(answer);
			} catch (BDDNotFoundException e) {
				// TODO Auto-generated catch block
				Maintient_connexion = false;
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//Maintient_connexion = false;
				//e.printStackTrace();
				// C'est NORMAL , TOUT VA BIEN !
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				Maintient_connexion = false;
				e.printStackTrace();
			}
			
		}
		try {
			stopconnexion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Récupere ce que le client à envoyé sous la forme d'un tableau de bytes
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private Request reception() throws ClassNotFoundException, IOException{
		
		Object data_rcv = in.readObject();
		Request request_rcv = null;
		if(data_rcv instanceof Request)
		{
			request_rcv = (Request) data_rcv;
		}
		//in.close();
		
		return request_rcv;
	}
	
	/**
	 * Envoie au client le retour de ça requete sous la forme d'un tableau de bytes
	 * @throws IOException
	 */
	private void emmision(Answer data_emmision) throws IOException{
		out.writeObject(data_emmision);
		out.flush();
       // out.close();
	}
	
	/**
	 * Stop la connexion
	 * @throws IOException
	 */
	public void stopconnexion() throws IOException{
		System.out.println("[Echange_client] Arret de la connexion avec le client");
		Maintient_connexion = false;
		socket.close();
		
	}

	
}
