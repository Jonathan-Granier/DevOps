package main.java.stockage_cle_valeur;

import main.java.exception.NonExistingKeyException;

/**
 * Interface pour les serveurs de stockage servant de cache au RequestHandler pour la BDD
 * @author bizarda
 *
 */
public interface StorageServerInterface {
	
	public int getID();
	
	public int getCapacity();
	public boolean isFull();
	public void evinceLRU();
	
	public boolean contains(Object elem);
	public boolean containsKey(Object key);
	public Object get(Object key) throws NonExistingKeyException;
	public void put(Object key, Object elem);
	public void remove(Object key);
}
