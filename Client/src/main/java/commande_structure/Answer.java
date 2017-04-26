package main.java.commande_structure;

import java.io.Serializable;

/**
 * Classe structure pour les reponses du RequestHandler
 * @author bizarda
 *
 */
public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;
	public enum returnCode{
		OK,
		NonExistingKey,
		WrongDataType,
		IndexOutOfListBounds
	}
	
	public returnCode return_code;
	public Serializable data; // Je pense que ce truc doit probablement implementer l'interface serializable aussi.
	public int reqNumber;
	
	public boolean equals(Object that){
		if(!(that instanceof Answer))
			return false;
		Answer ansthat = (Answer)that;
		return this.return_code == ansthat.return_code 
				&& this.reqNumber == ansthat.reqNumber 
				&& this.data.equals(ansthat.data);
		
	}
	public String toString(){
		if(data!= null)
		{
			return "ReqNumber : "+ reqNumber + " | Return_Code : "+ return_code + " | data : "+data.toString();
		}
		return "ReqNumber : "+ reqNumber + " | Return_Code : "+ return_code ;
	}
}
