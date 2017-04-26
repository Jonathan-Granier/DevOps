package main.java.stockage_cle_valeur;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.LinkedList;

import main.java.exception.NonExistingKeyException;

/**
 * Serveur de stockage servant de cache au ServerManager
 * @author bizarda
 *
 */
public class StorageServer implements StorageServerInterface{

	private static final int CAPACITY = 30;
	
	private static int nb_servers=0;
	
	private final int ID;
	private Hashtable<String,Serializable> H;
	private LinkedList<String> keys_usage_order;
	private LinkedList<Serializable> values_usage_order;
	private final int capacity;
	
	/**
	 * Constructeur par défaut
	 */
	public StorageServer(){
		ID = nb_servers;
		nb_servers++;
		H = new Hashtable<String,Serializable>();
		keys_usage_order = new LinkedList<String>();
		values_usage_order = new LinkedList<Serializable>();
		capacity = CAPACITY;
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
		H.remove(keys_usage_order.pop(),values_usage_order.pop());
	}

	/**
	 * Vide le serveur
	 */
	public void clear() {
		H.clear();
		keys_usage_order.clear();
		values_usage_order.clear();
	}

	public boolean contains(Serializable elem) {
		if(H.contains(elem)){
			int index_of_elem = values_usage_order.indexOf(elem);
			refreshAtIndex(index_of_elem);
			return true;
		}
		else
			return false;
	}

	public boolean containsKey(String key) {
		if(H.containsKey(key)){
			int index_of_key = keys_usage_order.indexOf(key);
			refreshAtIndex(index_of_key);
			return true;
		}
		else
			return false;
	}

	public Serializable get(String key) throws NonExistingKeyException{
		Serializable res = H.get(key);
		if(res == null)
			throw new NonExistingKeyException();
		else{
			int index_of_elem = values_usage_order.indexOf(res);
			refreshAtIndex(index_of_elem);
			return res;
		}
	}

	/**
	 * Renvoie vrai ssi tout s'est bien passé
	 */
	public boolean put(String key, Serializable elem){
		if(!this.isFull()){
			H.put(key, elem);
			keys_usage_order.add(key);
			values_usage_order.add(elem);
			return true;
		}
		else
			return false;
	}

	public void remove(String key) throws NonExistingKeyException{
		H.remove(key);
		int index_of_key = keys_usage_order.indexOf(key);
		if(index_of_key == -1)
			throw new NonExistingKeyException();
		keys_usage_order.remove(index_of_key);
		values_usage_order.remove(index_of_key);
	}
	
	private void refreshAtIndex(int index){
		keys_usage_order.add(keys_usage_order.remove(index));
		values_usage_order.add(values_usage_order.remove(index));
	}
}
