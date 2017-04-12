package main.java.stockage_cle_valeur;

import main.java.commande_structure.Answer;
import main.java.commande_structure.Request;
import main.java.exception.NonExistingKeyException;

/**
 * Classe d'interpretation des requetes
 * @author bizarda
 *
 */
public class RequestHandler {
	
	private ServerManager server_manager;
	
	/**
	 * Constructeur par défaut
	 */
	public RequestHandler(){	
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
	public Answer handleRequest(Request req){
		Answer res = new Answer();
		res.reqNumber = req.reqNumber;
		res.data = null;
		switch(req.op_code){
		case get:
			try {
				res.data = server_manager.get(req.key);
			} catch (NonExistingKeyException e) {
				res.return_code = Answer.returnCode.NonExistingKey;
				return res;
			}
			break;
		case remove:
			try {
				server_manager.remove(req.key);
			} catch (NonExistingKeyException e) {
				res.return_code = Answer.returnCode.NonExistingKey;
				return res;
			}
			break;
		case setInt:
		case setObject:
		case setString:
			server_manager.add(req.key, req.data);
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
		res.return_code = Answer.returnCode.OK;
		return res;
	}
}