package test.java.main;




import main.java.main.Main;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AddTest extends TestCase
{
    
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AddTest( String testName )
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

    /**
     * Rigourous Test :-)
     */
    public void test_Add()
    {
        assertTrue( true );
    }
	
}


