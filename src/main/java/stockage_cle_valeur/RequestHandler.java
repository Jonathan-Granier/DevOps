package main.java.stockage_cle_valeur;

import main.java.commande_structure.Answer;
import main.java.commande_structure.Answer.returnCode;
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
		System.out.println("Traitment de la requête n°" + req.reqNumber);
		Answer ans = new Answer();
		ans.reqNumber = req.reqNumber;
		ans.data = null;
		switch(req.op_code){
		case get:
			try {
				ans.data = server_manager.get(req.key);
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
		case setInt:
			if(! (req.data instanceof Integer)){
				ans.return_code = returnCode.WrongDataType;
				return ans;
			}
			server_manager.add(req.key, req.data);
			break;
		case setString:
			if(! (req.data instanceof String)){
				ans.return_code = returnCode.WrongDataType;
				return ans;
			}
			server_manager.add(req.key, req.data);
			break;
		case setObject:
			if((req.data instanceof Integer) || (req.data instanceof String)){
				ans.return_code = returnCode.WrongDataType;
				return ans;
			}
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
		ans.return_code = returnCode.OK;
		return ans;
	}
}