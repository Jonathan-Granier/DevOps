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
	public void test_getAtIndex1() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_add1List("list",42);
		reqNum++;
		helper_add1List("list","coucou");
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.get_elem_of_list_at_index;
		req.key = "list";
		req.data = 1;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals("coucou",ans.data);
	}
	
	@Test
	public void test_getAtIndexOnInt1() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_getAtIndexOnSmthingElse1(42);
	}
	
	@Test
	public void test_getAtIndexOnString1() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_getAtIndexOnSmthingElse1("666");
	}
	
	private void helper_getAtIndexOnSmthingElse1(Serializable something_else) throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("list ?",something_else);
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.get_elem_of_list_at_index;
		req.key = "list ?";
		req.data = 0;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.WrongDataType,ans.return_code);
	}
	
	@Test
	public void test_getAtIndex2() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_add1List("list",42);
		reqNum++;
		helper_add1List("list","coucou");
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.get_elem_of_list_at_index;
		req.key = "list";
		req.data = "1";
		Answer ans = rqHdl.handleRequest(req);
		assertEquals("coucou",ans.data);
	}
	
	@Test
	public void test_getAtIndexOnInt2() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_getAtIndexOnSmthingElse2(42);
	}
	
	@Test
	public void test_getAtIndexOnString2() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_getAtIndexOnSmthingElse2("666");
	}
	
	private void helper_getAtIndexOnSmthingElse2(Serializable something_else) throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("list ?",something_else);
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.get_elem_of_list_at_index;
		req.key = "list ?";
		req.data = "0";
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.WrongDataType,ans.return_code);
	}
	
	@Test
	public void test_getAtWrongIndex1() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_add1List("list",69);
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.get_elem_of_list_at_index;
		req.key = "list";
		req.data = 42;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.IndexOutOfListBounds,ans.return_code);
	}
	
	@Test
	public void test_getAtWrongIndex2() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_add1List("list",69);
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.get_elem_of_list_at_index;
		req.key = "list";
		req.data = "42";
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.IndexOutOfListBounds,ans.return_code);
	}
	
	@Test
	public void test_incrInt() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("cle42",42);
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.increment;
		req.key = "cle42";
		req.data = 24;
		rqHdl.handleRequest(req);
		
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.get;
		req.key = "cle42";
		req.data = null;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(66,ans.data);
	}
	
	@Test
	public void test_incrString() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("cle42","random string");
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.increment;
		req.key = "cle42";
		req.data = 24;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.WrongDataType,ans.return_code);
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
	public void test_addElemToInt1() throws BDDNotFoundException, ServerMgrNotFoundException{
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
	public void test_addElemToString1() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("cle4","chaine de caracteres quelconque");
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.list_add;
		req.key = "cle4";
		req.data = 23;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.WrongDataType,ans.return_code);
	}
	
	@Test
	public void test_addElemToInt2() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("cle4",42);
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.list_add;
		req.key = "cle4";
		req.data = "23";
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.WrongDataType,ans.return_code);
	}
	
	@Test
	public void test_addElemToString2() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("cle4","chaine de caracteres quelconque");
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.list_add;
		req.key = "cle4";
		req.data = "23";
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.WrongDataType,ans.return_code);
	}
	
	@Test
	public void test_removeAtIndexOnInt1() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_removeAtIndexOnSmthingElse1(42);
	}
	
	@Test
	public void test_removeAtIndexOnString1() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_removeAtIndexOnSmthingElse1("666");
	}
	
	private void helper_removeAtIndexOnSmthingElse1(Serializable something_else) throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("list ?",something_else);
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.list_remove;
		req.key = "list ?";
		req.data = 0;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.WrongDataType,ans.return_code);
	}
	
	@Test
	public void test_removeAtIndexOnInt2() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_removeAtIndexOnSmthingElse2(42);
	}
	
	@Test
	public void test_removeAtIndexOnString2() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_removeAtIndexOnSmthingElse2("666");
	}
	
	private void helper_removeAtIndexOnSmthingElse2(Serializable something_else) throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("list ?",something_else);
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.list_remove;
		req.key = "list ?";
		req.data = "0";
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.WrongDataType,ans.return_code);
	}
	
	@Test
	public void test_removeAtWrongIndex() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_add1List("list",69);
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.list_remove;
		req.key = "list";
		req.data = 42;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.IndexOutOfListBounds,ans.return_code);
	}
	
	@Test
	public void test_setRemoveGet() throws BDDNotFoundException, ServerMgrNotFoundException{
		helper_set("1","truc");
		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.remove;
		req.key = "1";
		req.data = null;
		rqHdl.handleRequest(req);

		reqNum++;
		req.reqNumber = reqNum;
		req.op_code = opCode.get;
		req.key = "1";
		req.data = null;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.NonExistingKey,ans.return_code);
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
	

	
	@Test
	public void test_getWrongKey() throws BDDNotFoundException, ServerMgrNotFoundException{
		req.reqNumber = reqNum;
		req.op_code = opCode.get;
		req.key = "list";
		req.data = 42;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.NonExistingKey,ans.return_code);
	}
	
	@Test
	public void test_removeWrongKey() throws BDDNotFoundException, ServerMgrNotFoundException{
		req.reqNumber = reqNum;
		req.op_code = opCode.remove;
		req.key = "42";
		req.data = null;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.NonExistingKey,ans.return_code);
	}
	
	@Test
	public void test_getAtIndexWrongKey() throws BDDNotFoundException, ServerMgrNotFoundException{
		req.reqNumber = reqNum;
		req.op_code = opCode.get_elem_of_list_at_index;
		req.key = "list";
		req.data = 42;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.NonExistingKey,ans.return_code);
	}
	
	@Test
	public void test_IncrWrongKey() throws BDDNotFoundException, ServerMgrNotFoundException{
		req.reqNumber = reqNum;
		req.op_code = opCode.increment;
		req.key = "list";
		req.data = 42;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.NonExistingKey,ans.return_code);
	}
	
	@Test
	public void test_removeAtIndexWrongKey() throws BDDNotFoundException, ServerMgrNotFoundException{
		req.reqNumber = reqNum;
		req.op_code = opCode.list_remove;
		req.key = "list";
		req.data = 42;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.NonExistingKey,ans.return_code);
	}
}


