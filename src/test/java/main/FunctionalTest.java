package test.java.main;

import org.junit.*;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.exception.NonExistingKeyException;
import main.java.stockage_cle_valeur.ServerManager;
import main.java.stockage_cle_valeur.StorageServer;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Test fonctionnel : verifie que les donnees sont coherentes pour le client
 */
public class FunctionalTest{
	
	private ServerManager svMgr;
	
	/**
     * Create the test case
     * @param testName name of the test case
     */
    public FunctionalTest(){
    }
    
    @Before
    public void init(){
    	svMgr = new ServerManager(new BaseDeDonnees());
    	svMgr.addServer(new StorageServer());
    }

	@Test
    public void test_AddGet() throws NonExistingKeyException{
		svMgr.add(42, 23);
		svMgr.add(23, new ArrayList<String>());
		svMgr.add(66, "Hello world !");
    	
        assertTrue(svMgr.get(23) instanceof ArrayList<?>);
        assertEquals(0,((ArrayList<?>)svMgr.get(23)).size());
        
        assertTrue(svMgr.get(42) instanceof Integer);
        assertEquals(23,svMgr.get(42));
        
        assertEquals("Hello world !",svMgr.get(66));
    }
    
    @Test(expected = NonExistingKeyException.class)
    public void test_WrongKey() throws NonExistingKeyException{
    	svMgr.add(42, 23);
    	svMgr.get(23);
    }
	
}


