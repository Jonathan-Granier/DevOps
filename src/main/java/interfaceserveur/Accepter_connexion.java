package main.java.interfaceserveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author granijon
 *
 */
public class Accepter_connexion implements Runnable{


    private ServerSocket socketserver;
    private Socket socket;
    //Ya un thread
    public Thread t1;

    public Accepter_connexion(ServerSocket ss){
    	socketserver = ss;
    }

    
    /**
     * Accepte une connexion et lance un thread d'emmission
     */
    public void run() { 
        try {

            while(true){
	
		        socket = socketserver.accept();
		
		        System.out.println("Un client veut se connecter ");
		        t1 = new Thread(new Echange(socket));
		
		        t1.start();
		        }

        } catch (IOException e) {
            System.err.println("Erreur serveur");
        }

        

    }

}


