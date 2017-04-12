package test.java.main;

import org.junit.*;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.commande_structure.Request;
import main.java.commande_structure.Request.opCode;
import main.java.exception.NonExistingKeyException;
import main.java.stockage_cle_valeur.RequestHandler;
import main.java.stockage_cle_valeur.ServerManager;
import main.java.stockage_cle_valeur.StorageServer;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Test fonctionnel : verifie que les donnees sont coherentes pour le client
 */
public class RequestHandlerTest{
	
	private RequestHandler rqHdl;
	private Request req;
	private int reqNum;
	
	/**
     * Create the test case
     * @param testName name of the test case
     */
    public RequestHandlerTest(){
    	reqNum = 0;
    }
    
    @Before
    public void init(){
    	ServerManager svMgr = new ServerManager(new BaseDeDonnees());
    	svMgr.addServer(new StorageServer());
    	rqHdl = new RequestHandler(svMgr);
    	req = new Request();
    }

	@Test
	public void test_setString(){
		req.reqNumber = reqNum;
		req.op_code = opCode.setString;
		req.key = "Bonjour le monde";
		req.data = "Hello world !";
	}
	
	@After
	public void incrReqNum(){
		reqNum++;
	}
}


