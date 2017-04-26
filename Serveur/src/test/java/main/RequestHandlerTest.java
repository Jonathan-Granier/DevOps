package test.java.main;

import org.junit.*;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.commande_structure.Answer;
import main.java.commande_structure.Answer.returnCode;
import main.java.commande_structure.Request;
import main.java.commande_structure.Request.opCode;
import main.java.exception.BDDNotFoundException;
import main.java.exception.ServerMgrNotFoundException;
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
	private static int reqNum;
	
	/**
     * Create the test case
     * @param testName name of the test case
     */
    public RequestHandlerTest(){
    	rqHdl = new RequestHandler();
    }
    
    @BeforeClass
    public static void first_init(){
    	reqNum = 0;
    }
    
    @Before
    public void init(){
    	ServerManager svMgr = new ServerManager(new BaseDeDonnees());
    	svMgr.addServer(new StorageServer());
    	rqHdl = new RequestHandler(new ServerManager());
    	rqHdl.changeServerManager(svMgr);
    	req = new Request();
    }

	@Test
	public void test_setInt() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("Reponse a la vie, l'univers et le reste",42);
	}

	@Test
	public void test_setString() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("Bonjour le monde","Hello world !");
	}
		
	private void helper_set(String key, Object data) throws BDDNotFoundException, ServerMgrNotFoundException{
		req.reqNumber = reqNum;
		req.op_code = opCode.set;
		req.key = key;
		req.data = (Serializable) data;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.OK,ans.return_code);
		assertEquals(reqNum,ans.reqNumber);
	}
		
	@Test
	public void test_setGetInt() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_setGet("1",42);
	}
	
	@Test
	public void test_setGetString() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_setGet("2","oui");
	}
		
	private void helper_setGet(String key, Serializable data) throws BDDNotFoundException, ServerMgrNotFoundException{
		req.reqNumber = reqNum;
		req.op_code = opCode.set;
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
	
	@Test
	public void test_add1ListInt() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_add1List("cle1",42);
	}
	
	@Test
	public void test_add1ListString() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_add1List("cle2","valeur");
	}
	
	@Test
	public void test_add2List() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_add1List("cle3",42);
		reqNum++;
		helper_add1List("cle3","coucou");
	}
	
	private void helper_add1List(String key, Serializable data) throws BDDNotFoundException, ServerMgrNotFoundException{
		req.reqNumber = reqNum;
		req.op_code = opCode.list_add;
		req.key = key;
		req.data = data;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.OK,ans.return_code);
		assertEquals(reqNum,ans.reqNumber);
	}
	
	@Test
	public void test_addElemToInt() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("cle4",42);
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.list_add;
		req.key = "cle4";
		req.data = 23;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.WrongDataType,ans.return_code);
	}
	
	@Test
	public void test_addElemToString() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("cle4","chaine de caracteres quelconque");
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.list_add;
		req.key = "cle4";
		req.data = 23;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.WrongDataType,ans.return_code);
	}
	
	@Test(expected=BDDNotFoundException.class)
	public void test_NoBDD() throws BDDNotFoundException, ServerMgrNotFoundException{
		rqHdl.changeServerManager(new ServerManager(null));
		helper_set("cle5",42);
	}
	
	@Test(expected=ServerMgrNotFoundException.class)
	public void test_NoSvMgr() throws BDDNotFoundException, ServerMgrNotFoundException{
		rqHdl.changeServerManager(null);
		helper_set("cle6",666);
	}
	
	@After
	public void incrReqNum(){
		reqNum++;
	}
}


