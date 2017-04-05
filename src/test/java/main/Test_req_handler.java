package test.java.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.WrongDataTypeException;

public class Test_req_handler {
	
	public class Test_add {
		//Main m;
		@org.junit.Test
		public void test() {
		}
		
		
		@Before
		public void setUp()
		{
			//m = new Main();
		}
		
		
		@Test
		public void test_Add() {
			//assertTrue( m.add(2,2)==4);
		}
		
		@Test//(expected=WrongDataTypeException.class)
		public void test_atomic_function_wrong_data_type(){
			//Add une data d'un type
			//tente une fonction d'un type non correspondant
			assertTrue(true);
		}
		
		//public void 
		
	}

}
