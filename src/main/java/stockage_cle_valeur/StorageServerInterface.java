package main.java.stockage_cle_valeur;

import java.io.Serializable;

import main.java.exception.NonExistingKeyException;

/**
 * Interface pour les serveurs de stockage servant de cache au ServerManager pour la BDD
 * @author bizarda
 *
 */
public interface StorageServerInterface {
	
	public int getID();
	
	public int getCapacity();
	public boolean isFull();
	public void evinceLRU();
	
	public boolean contains(Serializable elem);
	public boolean containsKey(Object key);
	public Serializable get(Object key) throws NonExistingKeyException;
	public void put(Object key, Serializable elem);
	public void remove(Object key) throws NonExistingKeyException;
}
