package main.java.commande_structure;

import java.io.Serializable;

/**
 * Classe structure pour les reponses du RequestHandler
 * @author bizarda
 *
 */
public class Answer implements Serializable {

	public enum returnCode{
		OK,
		NonExistingKey,
		WrongDataType
	}
	
	public returnCode return_code;
	public Serializable data; // Je pense que ce truc doit probablement implementer l'interface serializable aussi.
	public int reqNumber;
}
