package main.java.commande_structure;

public class Request {
	
	public enum opCode{
		setString,
		setInt,
		setObject,
		get,
		increment,
		list_add,
		list_remove,
		remove
	}
	public opCode op_code;
	public String key;
	public Object data;
	public int reqNumber;
}
