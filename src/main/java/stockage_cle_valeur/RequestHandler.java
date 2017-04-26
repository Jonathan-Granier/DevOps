package main.java.stockage_cle_valeur;

import java.io.Serializable;

import main.java.commande_structure.Answer;
import main.java.commande_structure.Answer.returnCode;
import main.java.commande_structure.Request;
import main.java.exception.BDDNotFoundException;
import main.java.exception.NonExistingKeyException;

/**
 * Classe d'interpretation des requetes
 * @author bizarda
 *
 */
public class RequestHandler {
	
	private ServerManager server_manager;
	
	/**
	 * Constructeur par defaut
	 */
	public RequestHandler(){
		this.server_manager = null;
	}
	
	/**
	 * Constructeur
	 * @param server_manager le ServerManager auquel se "brancher"
	 */
	public RequestHandler(ServerManager server_manager){
		this.server_manager = server_manager;
	}
	
	/**
	 * Change le ServerManager auquel on se "branche"
	 * @param server_manager
	 */
	public void changeServerManager(ServerManager server_manager){
		this.server_manager = server_manager;
	}
	
	/**
	 * Traite une requete
	 * @param req la requete a traiter
	 * @return le resultat de cette requete
	 */
	public Answer handleRequest(Request req) throws BDDNotFoundException{
		System.out.println("Traitment de la requete n" + req.reqNumber);
		Answer ans = new Answer();
		ans.reqNumber = req.reqNumber;
		ans.data = null;
		switch(req.op_code){
		case get:
			try {
				ans.data = (Serializable) server_manager.get(req.key);
			} catch (NonExistingKeyException e) {
				ans.return_code = returnCode.NonExistingKey;
				return ans;
			}
			break;
		case remove:
			try {
				server_manager.remove(req.key);
			} catch (NonExistingKeyException e) {
				ans.return_code = returnCode.NonExistingKey;
				return ans;
			}
			break;
		case set:
			/*if(! (req.data instanceof Integer) && ! (req.data instanceof String)){
				ans.return_code = returnCode.WrongDataType;
				return ans;
			}*///Ce test n'est pas necessaire, toute donnée serializable est passable
			server_manager.add(req.key, req.data);
			break;
		case get_elem_of_list_at_index:
			//TODO
			break;
		case increment:
			break;
		case list_add:
			break;
		case list_remove:
			break;
		default:
			break;
		}
		ans.return_code = returnCode.OK;
		return ans;
	}
}