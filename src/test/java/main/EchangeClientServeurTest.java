package test.java.main;

import org.junit.*;

//import client.java.echange_client.Echange_Client;
import main.java.commande_structure.Answer;
import main.java.commande_structure.Request;
import main.java.interfaceserveur.Echange_Serveur;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Test fonctionnelle : Test si les données avant et après un echange client serveur sont equivalente.
 * @author granijon
 *
 */

public class EchangeClientServeurTest {
	
	private Request Send_Request;
	private Request Rcv_Request;
	
	private Answer Send_Answer;
	private Answer Rcv_Answer;
	
	
	private Socket client_socket;
	//private Echange_Client client;
	private Echange_Serveur serveur;
	
	@Before
	public void init(){
		//Mis en place du client et du server
	/*	ServerRunnable sr= null;
		try {
			sr = new ServerRunnable();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Thread serverThread = new Thread(sr);
	    serverThread.start();

	    // connection du client
	    try {
			Socket client_socket = new Socket("localhost", sr.getPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //client = new Echange_Client(client_socket);*/
    }
	
	@After
	public void end(){
		/*try {
			client_socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	
	
	/*-------------------------------Traduction Seulement -------------------------------*/
	@Test
	public void test_ClientToServer_Traduction_1()
	{
		
		//creer la requete
		/*try {
			//client.envoi_data(Send_Request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//Rcv_Request = 
		assertTrue(true);
	}
	
	@Test
	public void test_ClientToServer_Traduction_2()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ClientToServer_Traduction_3()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ServerToClient_Traduction_1()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ServerToClient_Traduction_2()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ServerToClient_Traduction_3()
	{
		assertTrue(true);
	}

	
	/*-------------------------------Echange l'un vers l'autre -------------------------------*/
	
	
	@Test
	public void test_ClientToServer_Echange_1()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ClientToServer_Echange_2()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ClientToServer_Echange_3()
	{
		assertTrue(true);
	}
	
	
	
	@Test
	public void test_ServerToClient_Echange_1()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ServerToClient_Echange_2()
	{
		assertTrue(true);
	}

	
	@Test
	public void test_ServerToClient_Echange_3()
	{
		assertTrue(true);
	}


	/**
	 * Cette classe lance un serveur, attend une connexion , et recupere la requete recu. 
	 */
	class ServerRunnable implements Runnable {
	    private ServerSocket socketserver;
	    private Echange_Serveur serveur;
	    private Request request;
	    
	    
	    public ServerRunnable() throws IOException {
	    	socketserver = new ServerSocket(0);
	    }

	    public void run() {
	        try {
	        	
	            	Socket sock = socketserver.accept();
	                serveur = new Echange_Serveur(sock,null); 
	                request = serveur.reception();
	                //sock.close();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    public int getPort() {
	        return socketserver.getLocalPort();
	    }
	    
	    public Request getRequest(){
	    	return request;
	    }
	}
	
	
}
