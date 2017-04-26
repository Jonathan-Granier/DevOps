package test.java.main;

import org.junit.*;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.commande_structure.Answer;
import main.java.commande_structure.Answer.returnCode;
import main.java.commande_structure.Request;
import main.java.commande_structure.Request.opCode;
import main.java.exception.BDDNotFoundException;
import main.java.stockage_cle_valeur.RequestHandler;
import main.java.stockage_cle_valeur.ServerManager;
import main.java.stockage_cle_valeur.StorageServer;

import static org.junit.Assert.*;

import java.io.Serializable;

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
	public void test_setInt() throws BDDNotFoundException{
		helper_set(opCode.set,"Reponse a la vie, l'univers et le reste",42);
	}

	@Test
	public void test_setString() throws BDDNotFoundException{
		helper_set(opCode.set,"Bonjour le monde","Hello world !");
	}
		
	private void helper_set(opCode code, String key, Object data) throws BDDNotFoundException{
		req.reqNumber = reqNum;
		req.op_code = code;
		req.key = key;
		req.data = (Serializable) data;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.OK,ans.return_code);
		assertEquals(reqNum,ans.reqNumber);
	}
		
	@Test
	public void test_setGetInt() throws BDDNotFoundException{
		helper_setGet(opCode.set,"1",42);
	}
	
	@Test
	public void test_setGetSrting() throws BDDNotFoundException{
		helper_setGet(opCode.set,"2","oui");
	}
		
	private void helper_setGet(opCode code, String key, Serializable data) throws BDDNotFoundException{
		req.reqNumber = reqNum;
		req.op_code = code;
		req.key = key;
		req.data = data;
		rqHdl.handleRequest(req);

		reqNum++;
		
		req.reqNumber = reqNum;
		req.op_code = opCode.get;
		req.key = key;
		req.data = null;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.OK,ans.return_code);
		assertEquals(reqNum,ans.reqNumber);
		assertEquals(ans.data,data);
	}
	
	@After
	public void incrReqNum(){
		reqNum++;
	}
}


