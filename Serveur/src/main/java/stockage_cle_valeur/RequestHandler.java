package main.java.stockage_cle_valeur;

import java.io.Serializable;
import java.util.ArrayList;

import main.java.commande_structure.Answer;
import main.java.commande_structure.Answer.returnCode;
import main.java.commande_structure.Request;
import main.java.exception.BDDNotFoundException;
import main.java.exception.NonExistingKeyException;
import main.java.exception.ServerMgrNotFoundException;

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
	@SuppressWarnings("unchecked")
	public Answer handleRequest(Request req) throws BDDNotFoundException, ServerMgrNotFoundException{
		System.out.println("Traitment de la requete n" + req.reqNumber);
		if(server_manager == null)
			throw new ServerMgrNotFoundException();
		Answer ans = new Answer();
		ans.reqNumber = req.reqNumber;
		ans.data = null;
		Object theoretical_list;
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
			server_manager.add(req.key, req.data);
			break;
		case get_elem_of_list_at_index:
			try {
				theoretical_list = server_manager.get(req.key);
			} catch (NonExistingKeyException e) {
				ans.return_code = returnCode.NonExistingKey;
				return ans;
			}
			if(theoretical_list instanceof ArrayList){
				int i;
				if(req.data instanceof String)
					i = Integer.parseInt((String)req.data);
				else
					i = (Integer)req.data;
				ans.data = ((ArrayList<Serializable>) theoretical_list).get(i);
			}
			else{
				ans.return_code = returnCode.WrongDataType;
				return ans;
			}
			break;
		case increment:
			Object theoretical_int;
			try {
				theoretical_int = server_manager.get(req.key);
			} catch (NonExistingKeyException e) {
				ans.return_code = returnCode.NonExistingKey;
				return ans;
			}
			if(theoretical_int instanceof Integer){
				int i;
				if(req.data instanceof String)
					i = Integer.parseInt((String)req.data);
				else
					i = (Integer)req.data;
				server_manager.add(req.key,(Integer)theoretical_int + i);
			}
			else{
				ans.return_code = returnCode.WrongDataType;
				return ans;
			}
			break;
		case list_add:
			try {
				theoretical_list = server_manager.get(req.key);
			} catch (NonExistingKeyException e) {
				theoretical_list = new ArrayList<Serializable>();
			}
			if(theoretical_list instanceof ArrayList){
				((ArrayList<Serializable>) theoretical_list).add(req.data);
				server_manager.add(req.key, (Serializable) theoretical_list);
			}
			else{
				ans.return_code = returnCode.WrongDataType;
				return ans;
			}
			break;
		case list_remove:
			try {
				theoretical_list = server_manager.get(req.key);
			} catch (NonExistingKeyException e) {
				ans.return_code = returnCode.NonExistingKey;
				return ans;
			}
			if(theoretical_list instanceof ArrayList){
				int i;
				if(req.data instanceof String)
					i = Integer.parseInt((String)req.data);
				else
					i = (Integer)req.data;
				((ArrayList<Serializable>) theoretical_list).remove(i);
				server_manager.add(req.key, (Serializable) theoretical_list);
			}
			else{
				ans.return_code = returnCode.WrongDataType;
				return ans;
			}
			break;
		default:
			break;
		}
		ans.return_code = returnCode.OK;
		return ans;
	}
}