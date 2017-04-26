package main.java.stockage_cle_valeur;

import java.io.Serializable;

import main.java.exception.NonExistingKeyException;
import main.java.exception.ServerFullException;

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
	public boolean containsKey(String key);
	public Serializable get(String key) throws NonExistingKeyException;
	public void put(String key, Serializable elem) throws ServerFullException;
	public void remove(String key) throws NonExistingKeyException;
}
