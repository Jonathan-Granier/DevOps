package test.java.main;

import org.junit.*;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.exception.NonExistingKeyException;

import static org.junit.Assert.*;

/**
 * Classe de test pour la couverture de code
 * @author bizarda
 *
 */
public class BaseDeDonneesTest {
	private BaseDeDonnees BDD;
	
	public BaseDeDonneesTest(){
		BDD = new BaseDeDonnees();
	}
	
	@Test
	public void test_clear_elements_values_entrySet_keys_keySet(){
		BDD.put("cle","valeur");
		assertEquals(1,BDD.entrySet().size());
		assertEquals(1,BDD.keySet().size());
		BDD.clear();
		assertEquals(0,BDD.values().size());
		assertFalse(BDD.keys().hasMoreElements());
		assertFalse(BDD.elements().hasMoreElements());
		assertTrue(BDD.isEmpty());
	}
	
	@Test
	public void test_remove() throws NonExistingKeyException{
		BDD.put("cle","valeur");
		BDD.remove("cle");
		assertTrue(BDD.isEmpty());
	}
	
	@Test(expected=NonExistingKeyException.class)
	public void test_removeWrongKey() throws NonExistingKeyException{
		BDD.put("cle","valeur");
		BDD.remove("wrong");
	}
}
