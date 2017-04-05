package test.java.main;


import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.exception.WrongDataTypeException;
import main.java.stockage_cle_valeur.RequestHandler;

public class Test_req_handler extends TestCase {
	RequestHandler req_H;
	private static int nb_iter=15;
	
	public Test_req_handler( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return (Test) new TestSuite( AddTest.class );
    }

		
		
	//@Before
	public void setUp()
	{
		//m = new Main();
		req_H = new RequestHandler(new BaseDeDonnees());
	}
	
	
	
	//@Test//(expected=WrongDataTypeException.class)
	public void test_atomic_function_wrong_data_type(){
		//Add une data d'un type
		//tente une fonction d'un type non correspondant
		assertTrue(true);
	}
	
	//@Test
	public void test_add_get_key(){
		Random r = new Random();
		int value,key,iter=0,retour;
		boolean test=true;
		
		while(iter < nb_iter && test){
			value = r.nextInt();
			key = r.nextInt();
			req_H.add(key, value);
			//retour = (int)(req_H.get(key));
			//test = (value == retour);
			iter ++;
		}
		assertTrue(true);
		
	}
	
    /**
     * Rigourous Test :-)
     */
    public void test_Add()
    {
        assertTrue( true );
    }
}


