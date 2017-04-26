package test.java.client;

import org.junit.*;

import main.java.commande_structure.Request;
import main.java.commande_structure.Request.opCode;
import main.java.fichiers_client.Client;
import main.java.fichiers_client.InvalidInstructionException;
import main.java.fichiers_client.InvalidNumArgumentException;
import main.java.fichiers_client.UnknownCmdException;

import static org.junit.Assert.*;

public class ClientTest {

    @Before
    public void init(){
    	
    }

    @Test
 	public void test_parseGet() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
 		Request req = Client.parse_cmd("get x");
 		assertEquals(opCode.get,req.op_code);
 		assertEquals("x",req.key);
 	}
    
	@Test
	public void test_parseSet() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Request req = Client.parse_cmd("set y 1");
		assertEquals(opCode.set,req.op_code);
		assertEquals("y",req.key);
		assertEquals(1,req.data);
	}
	   
	@Test
	public void test_parseGetAtIndex() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Request req = Client.parse_cmd("getAtIndex list 0");
		assertEquals(opCode.get_elem_of_list_at_index,req.op_code);
		assertEquals("list",req.key);
		assertTrue(req.data.equals(0) || req.data.equals("0"));
	}
	   
	@Test
	public void test_parseIncr() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Request req = Client.parse_cmd("increment i 42");
		assertEquals(opCode.increment,req.op_code);
		assertEquals("i",req.key);
		assertTrue(req.data.equals(42) || req.data.equals("42"));
	}
	   
	@Test
	public void test_parseAddToList() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Request req = Client.parse_cmd("list_add list 666");
		assertEquals(opCode.list_add,req.op_code);
		assertEquals("list",req.key);
		assertTrue(req.data.equals(666) || req.data.equals("666"));
	}
	   
	@Test
	public void test_parseRemoveFromList() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Request req = Client.parse_cmd("list_remove list 666");
		assertEquals(opCode.list_remove,req.op_code);
		assertEquals("list",req.key);
		assertTrue(req.data.equals(666) || req.data.equals("666"));
	}
	   
	@Test
	public void test_parseRemove() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Request req = Client.parse_cmd("remove x");
		assertEquals(opCode.remove,req.op_code);
		assertEquals("x",req.key);
	}
	
	
	//////////////////////
	
	
	@Test(expected=UnknownCmdException.class)
	public void test_parseUnknown() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Client.parse_cmd("wololo blblbl");
	}
	
	@Test
	public void test_parseInvalidArgumentNb() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Request req = Client.parse_cmd("get");
		assertEquals(null,req);
	}
	
	@Test(expected=InvalidNumArgumentException.class)
	public void test_parseInvalidArgumentNbGet() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Client.parse_cmd("get x 23");
	}
	
	@Test(expected=InvalidNumArgumentException.class)
	public void test_parseInvalidArgumentNbSet() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Client.parse_cmd("set x");
	}
	
	@Test(expected=InvalidNumArgumentException.class)
	public void test_parseInvalidArgumentNbGetAtIndex() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Client.parse_cmd("getAtIndex x");
	}
	
	@Test(expected=InvalidNumArgumentException.class)
	public void test_parseInvalidArgumentNbIncr() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Client.parse_cmd("increment x 21 23");
	}
	
	@Test(expected=InvalidNumArgumentException.class)
	public void test_parseInvalidArgumentNbAddToList() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Client.parse_cmd("list_add x");
	}
	
	@Test(expected=InvalidNumArgumentException.class)
	public void test_parseInvalidArgumentNbRemoveFromList() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Client.parse_cmd("list_remove x");
	}
	
	@Test(expected=InvalidNumArgumentException.class)
	public void test_parseInvalidArgumentNbRemove() throws UnknownCmdException, InvalidInstructionException, InvalidNumArgumentException {
		Client.parse_cmd("remove x 1");
	}
}
