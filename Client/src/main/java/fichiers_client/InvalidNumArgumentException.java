package main.java.fichiers_client;

public class InvalidNumArgumentException extends Exception {

	int expected,input;
	private static final long serialVersionUID = 1L;
	public InvalidNumArgumentException(int expected,int input){
		super();
		this.expected = expected;
		this.input = input;
	}
}
