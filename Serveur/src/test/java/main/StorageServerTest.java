package test.java.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.*;

import main.java.exception.NonExistingKeyException;
import main.java.stockage_cle_valeur.StorageServer;

public class StorageServerTest {

	private StorageServer stSrv;
	
	public StorageServerTest(){
		stSrv = new StorageServer();
	}
	
	@Test
	public void test_init(){
		assertTrue(stSrv.getID()>=0);
	}
	
	@Test
	public void test_setGet() throws NonExistingKeyException {
		stSrv.put("cle1", "valeur");
		assertEquals("valeur",stSrv.get("cle1"));
	}
	
	@Test
	public void test_setUntilFull() throws NonExistingKeyException {
		for(int i=0; i<stSrv.getCapacity(); i++){
			stSrv.put(Integer.toString(i), i);
		}
		assertTrue(stSrv.isFull());
	}
	
	@Test(timeout=5000)
	public void test_exceedSize() throws NonExistingKeyException {
		Random r = new Random();
		while(stSrv.put(Integer.toString(r.nextInt()), r.nextInt()));
	}
	
	@Test
	public void test_evinctionLRU_onlyPut() throws NonExistingKeyException {
		for(int i=0; i<stSrv.getCapacity(); i++){
			stSrv.put(Integer.toString(i), i);
		}
		stSrv.evinceLRU();
		assertFalse(stSrv.containsKey("0"));
	}
	
	@Test
	public void test_evinctionLRU_putGetContains() throws NonExistingKeyException {
		for(int i=0; i<stSrv.getCapacity(); i++){
			stSrv.put(Integer.toString(i), i);
		}
		stSrv.contains(0);
		stSrv.containsKey("1");
		stSrv.get("2");
		stSrv.evinceLRU();
		assertFalse(stSrv.containsKey("3"));
	}
	
	@Test
	public void test_remove() throws NonExistingKeyException {
		stSrv.put("1", 42);
		stSrv.put("2", 23);
		stSrv.put("3", 66);
		stSrv.remove("2");
		assertFalse(stSrv.contains(23));
	}
	
	@Test(expected=NonExistingKeyException.class)
	public void test_removeUnexisting() throws NonExistingKeyException {
		stSrv.remove("yolo");
	}
	
	@Test(expected=NonExistingKeyException.class)
	public void test_UnknownKey() throws NonExistingKeyException{
		stSrv.get("pas une cle");
	}
	
	@Test
	public void test_clear() {
		for(int i=0; i<stSrv.getCapacity(); i++){
			stSrv.put(Integer.toString(i), i);
		}
		stSrv.clear();
		for(int i=0; i<stSrv.getCapacity(); i++){
			assertFalse(stSrv.contains(i));
		}
	}
}
