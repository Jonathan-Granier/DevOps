package main.java.stockage_cle_valeur;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Random;

import main.java.exception.NonExistingKeyException;

/**
 * Serveur de stockage servant de cache au ServerManager
 * @author bizarda
 *
 */
public class StorageServer implements StorageServerInterface{

	private static final int AVG_CAPACITY = 30;
	private static final int MARGE = 10;
	
	private static int nb_servers=0;
	
	private final int ID;
	private Hashtable<Object,Object> H;
	private LinkedList<Object> keys_usage_order;
	private LinkedList<Object> values_usage_order;
	private final int capacity;
	
	/**
	 * Constructeur par défaut
	 */
	public StorageServer(){
		ID = nb_servers;
		nb_servers++;
		H = new Hashtable<Object,Object>();
		keys_usage_order = new LinkedList<Object>();
		values_usage_order = new LinkedList<Object>();
		Random r = new Random();
		capacity = ((r.nextInt()%(2*MARGE))-MARGE) + AVG_CAPACITY;
	}
	
	/**
	 * Renvoie l'ID du serveur (unique pour tout StorageServer)
	 * @return l'ID du serveur
	 */
	public int getID(){
		return ID;
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
	public void evinceLRU() {
		values_usage_order.pop();
		H.remove(keys_usage_order.pop());
	}

	public boolean contains(Object elem) {
		if(H.contains(elem)){
			int index_of_elem = values_usage_order.indexOf(elem);
			refreshAtIndex(index_of_elem);
			return true;
		}
		else
			return false;
	}

	public boolean containsKey(Object key) {
		if(H.containsKey(key)){
			int index_of_key = keys_usage_order.indexOf(key);
			refreshAtIndex(index_of_key);
			return true;
		}
		else
			return false;
	}

	public Object get(Object key) throws NonExistingKeyException{
		Object res = H.get(key);
		if(res == null)
			throw new NonExistingKeyException();
		else{
			int index_of_elem = values_usage_order.indexOf(res);
			refreshAtIndex(index_of_elem);
			return res;
		}
	}

	public void put(Object key, Object elem) {
		H.put(key, elem);
		keys_usage_order.add(key);
		values_usage_order.add(elem);
	}

	public void remove(Object key) {
		H.remove(key);
		int index_of_key = keys_usage_order.indexOf(key);
		keys_usage_order.remove(index_of_key);
		values_usage_order.remove(index_of_key);
	}
	
	private void refreshAtIndex(int index){
		keys_usage_order.add(keys_usage_order.remove(index));
		values_usage_order.add(values_usage_order.remove(index));
	}
}
