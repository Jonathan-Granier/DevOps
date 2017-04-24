package main.java.commande_structure;

/**
 * Classe structure pour les requetes au RequestHandler
 * @author bouvigab
 *
 */
public class Request {
	
	public enum opCode{	// Il y a donc deux sources de opcode (dans interface serveur) redondant probablement?
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
	public byte[] data_as_byte;
	public int reqNumber;
}
