package test.java.main;

import org.junit.*;

import main.java.commande_structure.Answer;
import main.java.commande_structure.Request;

import static org.junit.Assert.*;


/**
 * Test fonctionnelle : Test si les données avant et après un echange client serveur sont equivalente.
 * @author granijon
 *
 */

public class EchangeClientServeurTest {
	
	public Request Send_Request;
	public Request Rcv_Request;
	
	public Answer Send_Answer;
	public Answer Rcv_Answer;
	
	@Before
	public void init(){
		
    }
	
	
	
	
	/*-------------------------------Traduction Seulement -------------------------------*/
	@Test
	public void test_ClientToServer_Traduction_1()
	{
		
		
		
		
		
		assertTrue(true);
	}
	
	@Test
	public void test_ClientToServer_Traduction_2()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ClientToServer_Traduction_3()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ServerToClient_Traduction_1()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ServerToClient_Traduction_2()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ServerToClient_Traduction_3()
	{
		assertTrue(true);
	}

	
	/*-------------------------------Echange l'un vers l'autre -------------------------------*/
	
	
	@Test
	public void test_ClientToServer_Echange_1()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ClientToServer_Echange_2()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ClientToServer_Echange_3()
	{
		assertTrue(true);
	}
	
	
	
	@Test
	public void test_ServerToClient_Echange_1()
	{
		assertTrue(true);
	}
	
	@Test
	public void test_ServerToClient_Echange_2()
	{
		assertTrue(true);
	}

	
	@Test
	public void test_ServerToClient_Echange_3()
	{
		assertTrue(true);
	}




	
	
}
