package main.java.commande_structure;

import java.io.Serializable;

import main.java.interfaceserveur.operationCode;

/**
 * Classe structure pour les requetes au RequestHandler
 * @author bouvigab
 *
 */
public class Request implements Serializable {
	
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



	public static opCode opcode_from_char(char input){
		switch(input){
		case operationCode.increment:
			return Request.opCode.increment;
			
		case operationCode.get:
			return Request.opCode.get;
			
		case operationCode.list_add:
			return Request.opCode.list_add;
			
		case operationCode.list_remove:
			return Request.opCode.list_remove;
			
		case operationCode.remove:
			return Request.opCode.remove;
			
		case operationCode.setInt:
			return Request.opCode.setInt;
			
		case operationCode.setObject:
			return Request.opCode.setObject;
			
		case operationCode.setString:
			return Request.opCode.setString;//  8
			
		default:
			return Request.opCode.get;
		}
	}
}
