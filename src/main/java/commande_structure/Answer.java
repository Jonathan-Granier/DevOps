package main.java.commande_structure;

public class Answer {

	public enum returnCode{
		OK,
		NonExistingKey
	}
	
	public returnCode return_code;
	public Object data;
	public int reqNumber;
}
