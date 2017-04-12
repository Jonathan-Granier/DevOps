package client.java.echange_client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * 
 * @author granijon
 * Interface d'echange entre le client (nous) et le serveur.
 * IP : 127.0.0.1
 * Socket : 1337
 */
public class Echange_Client {

	private final static String IP = "127.0.0.1";
	private final static int num_Socket = 1337;
    private Socket socket = null;

    
    /**
     * Constructeur qui execute start_Connexion
     * @throws UnknownHostException
     * @throws IOException
     */
    public Echange_Client() throws UnknownHostException, IOException {
        start_Connexion();    
    }
    
    /**
     * Commence la connexion avec l'adresse IP : 127.0.0.1 et le socket 1337
     * @throws UnknownHostException
     * @throws IOException
     */
    public void start_Connexion()throws UnknownHostException, IOException{
    	socket = new Socket(IP,num_Socket);;
    }
    
    /**
     * Effectue un echange avec le serveur., Prend en parametres les données à envoyer et retour les données recu 
     */
    public void faire_un_echange(){
    	
    }
    
    /**
     * Envoi des données
     */
    private void envoi_data(){
    	
    }
    
    /**
     * Récupere des données
     */
    private void reception_data(){
    	
    }
}
