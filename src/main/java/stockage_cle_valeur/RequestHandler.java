package main.java.stockage_cle_valeur;

import java.util.ArrayList;

import main.java.base_de_donnees.BDDInterface;
import main.java.exception.NonExistingKeyException;

/**
 * TODO
 * @author bizarda
 *
 */
public class RequestHandler {

	private BDDInterface BDD;
	private ArrayList<StorageServerInterface> servers;
	private int BDD_read_access, BDD_write_access;
	
	/**
	 * Constructeur par defaut
	 */
	public RequestHandler(){
		init();
	}
	
	/**
	 * Constructeur
	 * @param BDD la BDD a laquelle se "brancher"
	 */
	public RequestHandler(BDDInterface BDD){
		this.BDD = BDD;
		init();
	}
	
	private void init(){
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
	 * Ajouter un element dans la BDD
	 * @param cle une cle
	 * @param valeur l'element a associer a la cle
	 */
	public void add(Integer cle, Object valeur){
		System.out.println("Ajout de " + valeur.toString() + " avec la cle " + cle);
		BDD.put(cle,valeur);
		BDD_read_access ++;
	}
	
	/**
	 * Recuperer l'element associe a une cle
	 * @param cle
	 * @return l'element associe a cle
	 */
	public Object get(Integer cle){
		System.out.println("Acces a " + cle);
		Object res = null;
		try{
			res = BDD.get(cle);
			BDD_write_access ++;
		}
		catch(NonExistingKeyException e){
			e.printStackTrace();
		}
		return res;
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
}
