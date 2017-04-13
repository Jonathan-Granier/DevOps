package test.java.main;

import org.junit.*;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.commande_structure.Answer;
import main.java.commande_structure.Answer.returnCode;
import main.java.commande_structure.Request;
import main.java.commande_structure.Request.opCode;
import main.java.stockage_cle_valeur.RequestHandler;
import main.java.stockage_cle_valeur.ServerManager;
import main.java.stockage_cle_valeur.StorageServer;

import static org.junit.Assert.*;

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
	public void test_setInt(){
		helper_set(opCode.setInt,"Reponse a la vie, l'univers et le reste",42);
	}

	@Test
	public void test_setString(){
		helper_set(opCode.setString,"Bonjour le monde","Hello world !");
	}
	
	@Test
	public void test_setObject(){
		helper_set(opCode.setObject,"Un objet",this);
	}
	
	private void helper_set(opCode code, String key, Object data){
		req.reqNumber = reqNum;
		req.op_code = code;
		req.key = key;
		req.data = data;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.OK,ans.return_code);
		assertEquals(reqNum,ans.reqNumber);
	}
	
	@Test
	public void test_setInt_wrongType(){
		helper_set_wrongType(opCode.setInt,"Est-ce un entier ?","Ce n'est pas un entier");
	}
	
	@Test
	public void test_setString_wrongType(){
		helper_set_wrongType(opCode.setString,"Est-ce une chaine de caracteres ?",false);
	}
	
	@Test
	public void test_setObject_wrongType(){
		helper_set_wrongType(opCode.setObject,"Let's fool rqHdl",666);
	}
	
	private void helper_set_wrongType(opCode code, String key, Object data){
		req.reqNumber = reqNum;
		req.op_code = code;
		req.key = key;
		req.data = data;
		Answer ans = rqHdl.handleRequest(req);
		assertEquals(returnCode.WrongDataType,ans.return_code);
		assertEquals(reqNum,ans.reqNumber);
	}

	@Test
	public void test_setGetInt(){
		helper_setGet(opCode.setInt,"1",42);
	}
	
	@Test
	public void test_setGetSrting(){
		helper_setGet(opCode.setString,"2","oui");
	}
	
	@Test
	public void test_setGetObject(){
		helper_setGet(opCode.setObject,"3",this);
	}
	
	private void helper_setGet(opCode code, String key, Object data){
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


