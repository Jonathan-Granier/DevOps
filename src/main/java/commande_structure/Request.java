package main.java.commande_structure;

import java.io.Serializable;

/**
 * Classe structure pour les requetes au RequestHandler
 * @author bouvigab
 *
 */
public class Request implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum opCode{	// Il y a donc deux sources de opcode (dans interface serveur) redondant probablement?
		set,
		get,
		get_elem_of_list_at_index,
		increment,
		list_add,
		list_remove,
		remove
	}
	public opCode op_code;
	public String key;
	public Serializable data; // Je pense que ce truc doit probablement implementer l'interface serializable aussi pour être envoyable
	public int reqNumber;
	
	public Request() {
		super();
	}
	
	public Request(opCode op_code, String key, Serializable data, int reqNumber) {
		super();
		this.op_code = op_code;
		this.key = key;
		this.data = data;
		this.reqNumber = reqNumber;
	}
	
	public boolean equals(Object that){
		if(!(that instanceof Request))
			return false;
		Request reqthat = (Request)that;
		return this.op_code == reqthat.op_code 
				&& this.key.equals(reqthat.key) 
				&& this.reqNumber == reqthat.reqNumber 
				&& this.data.equals(reqthat.data);
		
	}

}
