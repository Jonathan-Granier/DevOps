package main.java.stockage_cle_valeur;

import java.util.Hashtable;
import java.util.Random;

import main.java.exception.NonExistingKeyException;

/**
 * Serveur de stockage servant de cache au RequestHandler
 * @author bizarda
 *
 */
public class StorageServer implements StorageServerInterface{

	private static final int AVG_CAPACITY = 30;
	private static final int MARGE = 10;
	
	private static int nb_servers=0;
	
	private final int ID;
	private Hashtable<Object,Object> H;
	private final int capacity;
	
	/**
	 * Constructeur par défaut
	 */
	public StorageServer(){
		ID = nb_servers;
		nb_servers++;
		H = new Hashtable<Object,Object>();
		Random r = new Random();
		capacity = ((r.nextInt()%(2*MARGE))-MARGE) + AVG_CAPACITY;
	}

	/**
	 * Renvoie la capacite de stockage du serveur
	 * @return la capacite de stockage du serveur
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Indique si le serveur a atteint sa limite de stockage
	 * @return true ssi le serveur est plein
	 */
	public boolean isFull() {
		return H.size()==capacity;
	}

	/**
	 * Supprime l'element le moins recemment utilise
	 * @return false ssi le serveur etait vide
	 */
	public boolean evinceLRU() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean contains(Object elem) {
		return H.contains(elem);
	}

	public boolean containsKey(Object key) {
		return H.containsKey(key);
	}

	public Object get(Object key) throws NonExistingKeyException{
		Object res = H.get(key);
		if(res == null)
			throw new NonExistingKeyException();
		else
			return res;
	}

	public void put(Object key, Object elem) {
		H.put(key, elem);
	}
}
