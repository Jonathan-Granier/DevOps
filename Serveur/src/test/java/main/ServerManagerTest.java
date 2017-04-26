package test.java.main;

import org.junit.*;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.exception.BDDNotFoundException;
import main.java.exception.NonExistingKeyException;
import main.java.stockage_cle_valeur.ServerManager;
import main.java.stockage_cle_valeur.StorageServer;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Test fonctionnel : verifie que les donnees sont coherentes pour le client
 */
public class ServerManagerTest{
	
	private ServerManager svMgr;
	
    public ServerManagerTest(){
    }
    
    @Before
    public void init(){
    	svMgr = new ServerManager(new BaseDeDonnees());
    	svMgr.addServer(new StorageServer());
    }

	@Test
	public void test_AddGet1() throws NonExistingKeyException, BDDNotFoundException{
		helper_AddGet("42",23);
	}
	
	@Test
	public void test_AddGet2() throws NonExistingKeyException, BDDNotFoundException{
		helper_AddGet("66","Hello world !");
	}

	@Test
    public void test_AddGet3() throws NonExistingKeyException, BDDNotFoundException{
		svMgr.add("23", new ArrayList<String>());
        assertEquals(0,((ArrayList<?>)svMgr.get("23")).size());
    }
	
	private void helper_AddGet(String key, Serializable value) throws NonExistingKeyException, BDDNotFoundException{
		svMgr.add(key, value);
        assertEquals(value,svMgr.get(key));
	}
	
	@Test
	public void test_contains() throws BDDNotFoundException{
		svMgr.add("oui","pitetre");
		assertTrue(svMgr.contains("pitetre"));
		assertFalse(svMgr.contains("surement"));
		assertTrue(svMgr.containsKey("oui"));
		assertFalse(svMgr.containsKey("non"));
	}
	
	@Test
	public void test_withoutSrv() throws NonExistingKeyException, BDDNotFoundException{
		svMgr.clearServers();
		svMgr.add("42", "smth");
		assertEquals("smth",svMgr.get("42"));
		assertEquals(1,svMgr.getBDDWriteAccess());
		assertEquals(1,svMgr.getBDDReadAccess());
		assertTrue(svMgr.containsKey("42"));
		assertTrue(svMgr.contains("smth"));
		assertFalse(svMgr.contains(0));
		assertFalse(svMgr.containsKey("66"));
		svMgr.remove("42");
	}
	
	@Test
	public void test_addUntilFull() throws NonExistingKeyException, BDDNotFoundException{
		for(int i=0; i<100; i++){
			svMgr.add(Integer.toString(i), i);
		}
		assertTrue(svMgr.getBDDWriteAccess()>0);
		assertTrue(svMgr.containsKey("0"));
		assertTrue(svMgr.contains(42));
		assertEquals(1,svMgr.get("1"));
	}
	
	@Test
	public void test_remove() throws NonExistingKeyException, BDDNotFoundException{
		svMgr.add("42", "smth");
		svMgr.remove("42");
		assertFalse(svMgr.contains("smth"));
		assertFalse(svMgr.containsKey("42"));
	}
	
	@Test
	public void test_operationsOutofSrv() throws NonExistingKeyException, BDDNotFoundException{
		for(int i=0; i<100; i++){
			svMgr.add(Integer.toString(i), i);
		}
		svMgr.remove("0");
		assertTrue(svMgr.contains(1));
		assertFalse(svMgr.contains(0));
		assertTrue(svMgr.containsKey("42"));
		assertFalse(svMgr.containsKey("101"));
		
	}
	
	@Test(expected = NonExistingKeyException.class)
	public void test_removeUnexisting() throws NonExistingKeyException, BDDNotFoundException{
		svMgr.add("42", "smth");
		svMgr.remove("23");
	}
	
    @Test(expected = NonExistingKeyException.class)
    public void test_WrongKey() throws NonExistingKeyException, BDDNotFoundException{
    	svMgr.add("42", 23);
    	svMgr.get("23");
    }
    
    @Test(expected = BDDNotFoundException.class)
    public void test_NoBDD() throws NonExistingKeyException, BDDNotFoundException{
    	svMgr.changeBDD(null);
    	svMgr.add("oui", "non");
    }
}


