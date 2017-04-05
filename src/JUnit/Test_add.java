package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import main.Main;



public class Test_add {
	Main m;
	@org.junit.Test
	public void test() {
	}
	
	
	@Before
	public void setUp()
	{
		m = new Main();
	}
	
	
	@Test
	public void test_Add() {
		assertTrue( m.add(2,2)==4);
	}
	
}
