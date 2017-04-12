package main.java.commande_structure;

/**
 * Classe structure pour les reponses du RequestHandler
 * @author bizarda
 *
 */
public class Answer {

	public enum returnCode{
		OK,
		NonExistingKey
	}
	
	public returnCode return_code;
	public Object data;
	public int reqNumber;
}
