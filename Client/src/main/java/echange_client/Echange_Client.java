package main.java.echange_client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
   
	
    
    
    /**
     * Constructeur qui execute start_Connexion
     * @throws UnknownHostException
     * @throws IOException
     */
    public Echange_Client() throws UnknownHostException, IOException {
        start_Connexion();    
    }
    
    /**
     * Constructeur avec une socket déjà existante.
     * @param socket
     */
    public Echange_Client( Socket socket){
    	this.socket = socket;
    }
    
    
    
    /**
     * Commence la connexion avec l'adresse IP : 127.0.0.1 et le socket 1337
     * @throws UnknownHostException
     * @throws IOException
     */
    private void start_Connexion()throws UnknownHostException, IOException{
    	socket = new Socket(IP,num_Socket);
    	in = new ObjectInputStream(socket.getInputStream());
    	out = new ObjectOutputStream(socket.getOutputStream());
    }
    
    /**
     * Effectue un echange avec le serveur., Prend en parametres les données à envoyer et retour les données recu
     * Faire la traduction avant
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public Answer faire_un_echange(Request data_emmision) throws IOException, ClassNotFoundException{
    	
    	envoi_data(data_emmision);
    	
    	
    	
    	return reception_data();
    }
    

    
    
    
    /**
     * Envoi des données
     * @throws IOException 
     */
    private void envoi_data(Request data_emmision) throws IOException{
    	out.writeObject(data_emmision);
    	out.flush();
    }
    
    /**
     * 
     * Récupere des données
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    private Answer reception_data() throws ClassNotFoundException, IOException{
    	Object data_rcv = in.readObject();
    	Answer answer_rcv = null;
		if(data_rcv instanceof Answer)
		{
			answer_rcv = (Answer) data_rcv;
		}
		return answer_rcv;
    }
    
    
    public void stop_connexion() throws IOException{
    	socket.close();
    }


    
    
   
}
