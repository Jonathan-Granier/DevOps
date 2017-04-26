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
	
	public void clear();
	
	public boolean contains(Serializable elem);
	public boolean containsKey(String key);
	public Serializable get(String key) throws NonExistingKeyException;
	public boolean put(String key, Serializable elem);
	public void remove(String key) throws NonExistingKeyException;
}
