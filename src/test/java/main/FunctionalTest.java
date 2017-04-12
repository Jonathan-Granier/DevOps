package test.java.main;

import org.junit.*;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.exception.NonExistingKeyException;
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
    public void test_AddGet() throws NonExistingKeyException{
    	rqHdl.add(42, 23);
    	rqHdl.add(23, new ArrayList<String>());
    	rqHdl.add(66, "Hello world !");
    	
        assertTrue(rqHdl.get(23) instanceof ArrayList<?>);
        assertEquals(0,((ArrayList<?>)rqHdl.get(23)).size());
        
        assertTrue(rqHdl.get(42) instanceof Integer);
        assertEquals(23,rqHdl.get(42));
        
        assertEquals("Hello world !",rqHdl.get(66));
    }
    
    @Test(expected = NonExistingKeyException.class)
    public void test_WrongKey() throws NonExistingKeyException{
    	rqHdl.add(42, 23);
    	rqHdl.get(23);
    }
	
}


