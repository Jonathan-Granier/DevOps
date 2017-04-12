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
	public void test_AddGet1() throws NonExistingKeyException{
		helper_AddGet(42,23);
	}
	
	@Test
	public void test_AddGet2() throws NonExistingKeyException{
		helper_AddGet(66,"Hello world !");
	}

	@Test
    public void test_AddGet3() throws NonExistingKeyException{
		svMgr.add(23, new ArrayList<String>());
        assertEquals(0,((ArrayList<?>)svMgr.get(23)).size());
    }
	
	private void helper_AddGet(Object key, Object value) throws NonExistingKeyException{
		svMgr.add(key, value);
        assertEquals(value,svMgr.get(key));
	}
    
    @Test(expected = NonExistingKeyException.class)
    public void test_WrongKey() throws NonExistingKeyException{
    	svMgr.add(42, 23);
    	svMgr.get(23);
    }
	
}


