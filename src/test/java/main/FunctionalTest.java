package test.java.main;

import org.junit.*;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.stockage_cle_valeur.RequestHandler;
import main.java.stockage_cle_valeur.StorageServer;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Test fonctionnel : verifie que les donnees sont coherentes pour le client
 */
public class FunctionalTest{
	
	private RequestHandler rqHdl;
	
	/**
     * Create the test case
     * @param testName name of the test case
     */
    public FunctionalTest(){
    }
    
    @Before
    public void init(){
    	rqHdl = new RequestHandler(new BaseDeDonnees());
    	rqHdl.addServer(new StorageServer());
    }

	@Test
    public void test_add_get(){
    	rqHdl.add(42, 23);
    	rqHdl.add(23, new ArrayList<String>());
    	rqHdl.add(66, "Hello world !");
    	
        assertTrue(rqHdl.get(23) instanceof ArrayList<?>);
        assertTrue(((ArrayList<?>)rqHdl.get(23)).size() == 0);
        
        assertTrue(rqHdl.get(42) instanceof Integer);
        assertTrue((Integer)rqHdl.get(42) == 23);
        
        assertTrue(rqHdl.get(66).equals("Hello world !"));
    }
    
    @Test
    public void test_contains(){
    	
    }
	
}


