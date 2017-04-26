package main.java.stockage_cle_valeur;

import java.io.Serializable;
import java.util.ArrayList;

import main.java.base_de_donnees.BDDInterface;
import main.java.exception.BDDNotFoundException;
import main.java.exception.NonExistingKeyException;

/**
 * Classe centrale de gestion des serveurs de cache devant la BDD
 * @author bizarda
 *
 */
public class ServerManager {

	// TODO gerer plusieurs serveurs
	
	private BDDInterface BDD;
	private ArrayList<StorageServerInterface> servers;
	private int BDD_read_access, BDD_write_access;
	
	/**
	 * Constructeur par defaut
	 */
	public ServerManager(){
		init();
	}
	
	/**
	 * Constructeur
	 * @param BDD la BDD a laquelle se "brancher"
	 */
	public ServerManager(BDDInterface BDD){
		init();
		this.BDD = BDD;
	}
	
	private void init(){
		BDD = null;
		servers = new ArrayList<StorageServerInterface>();
		BDD_read_access = 0;
		BDD_write_access = 0;
	}

	/**
	 * Change la BDD
	 * @param BDD la nouvelle BDD a laquelle se "brancher"
	 */
	public void changeBDD(BDDInterface BDD){
		this.BDD = BDD;
	}
	
	/**
	 * Ajoute un serveur de stockage
	 * @param server le nouveau serveur a prendre en compte
	 */
	public void addServer(StorageServerInterface server){
		servers.add(server);
	}
	
	/**
	 * Vide tous les serveurs et les retire
	 */
	public void clearServers(){
		for(StorageServerInterface server : servers)
			server.clear();
		servers.clear();
	}
	
	/**
	 * Ajouter un element dans la BDD
	 * @param cle une cle
	 * @param valeur l'element a associer a la cle
	 */
	public void add(String cle, Serializable valeur) throws BDDNotFoundException{
		checkBDD();
		if(!servers.isEmpty()){
			if(servers.get(0).isFull())
				servers.get(0).evinceLRU();
			servers.get(0).put(cle,valeur);
		}
		BDD.put(cle,valeur);
		BDD_write_access ++;
	}

	/**
	 * Recuperer l'element associe a une cle
	 * @param cle
	 * @return l'element associe a cle
	 */
	public Serializable get(String cle) throws NonExistingKeyException, BDDNotFoundException{
		checkBDD();
		Serializable res = null;
		try {
			res = servers.get(0).get(cle);
			return res;
		}
		catch (Exception ignored) {
			// Might be NonExistingKeyException or NullPointerException, in both cases search the BDD
			res = BDD.get(cle);
			BDD_read_access ++;
			return res;
		}
	}
	
	/**
	 * Renvoie vrai ssi l'element est dans la BDD
	 * @param valeur l'element a tester
	 * @return vrai ssi valeur est dans la BDD
	 */
	public boolean contains(Serializable valeur) throws BDDNotFoundException{
		checkBDD();
		if(!servers.isEmpty() && servers.get(0).contains(valeur)){
			return true;
		}
		else{
			BDD_read_access ++;
			return BDD.contains(valeur);
		}
	}
	
	/**
	 * Renvoie vrai ssi la cle est dans la BDD
	 * @param cle la cle a tester
	 * @return vrai ssi cle est dans la BDD
	 */
	public boolean containsKey(String cle) throws BDDNotFoundException{
		checkBDD();
		if(!servers.isEmpty() && servers.get(0).containsKey(cle)){
			return true;
		}
		else{
			BDD_read_access ++;
			return BDD.containsKey(cle);
		}
	}
	
	public void remove(String key) throws NonExistingKeyException, BDDNotFoundException {
		checkBDD();
		if(!BDD.containsKey(key))
			throw new NonExistingKeyException();
		else{
			if(!servers.isEmpty()){
				if(servers.get(0).containsKey(key)){
					servers.get(0).remove(key);
				}
			}
			BDD_write_access ++;
			BDD.remove(key);
		}
	}

	/**
	 * Renvoie le nombre d'acces en lecture a la BDD
	 * @return le nombre d'acces en lecture a la BDD
	 */
	public int getBDDReadAccess(){
		return BDD_read_access;
	}

	/**
	 * Renvoie le nombre d'acces en ecriture a la BDD
	 * @return le nombre d'acces en ecriture a la BDD
	 */
	public int getBDDWriteAccess(){
		return BDD_write_access;
	}
	
	private void checkBDD() throws BDDNotFoundException {
		if(BDD == null)
			throw new BDDNotFoundException();
	}
}
