package main.java.commande_structure;

public class structure_commande {
	
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
	public opCode opCode;
	public String key;
	public Object data;
	public int reqNumber;
}
