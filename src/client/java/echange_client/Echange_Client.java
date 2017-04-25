package client.java.echange_client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import main.java.commande_structure.Answer;
import main.java.commande_structure.Request;


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
    
    private InputStream in;
    private OutputStream out;
    
    private byte[] data_reception;
	//private byte[] data_emmision;
    
    
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
    	in = socket.getInputStream();
    	out = socket.getOutputStream();
    }
    
    /**
     * Effectue un echange avec le serveur., Prend en parametres les données à envoyer et retour les données recu
     * Faire la traduction avant
     * @throws IOException 
     */
    public Answer faire_un_echange(Request data_emmision) throws IOException{
    	
    	envoi_data(data_emmision);
    	reception_data();
    	//out.flush();
    	
    	return null;
    }
    

    
    
    
    /**
     * Envoi des données
     */
    private void envoi_data(Request data_emmision){
    	
    }
    
    /**
     * Récupere des données
     */
    private Answer reception_data(){
    	return null;
    }
}
