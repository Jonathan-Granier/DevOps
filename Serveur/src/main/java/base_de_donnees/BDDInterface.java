package main.java.base_de_donnees;

import java.io.Serializable;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map.Entry;

import main.java.exception.NonExistingKeyException;

import java.util.Set;

/**
 * Classe d'interface de BDD, a ajouter au ServerManager
 * @author bizarda
 * Interface similaire à Hashtable<Object,Object>
 */
public interface BDDInterface {

	public void put(Object cle, Serializable valeur);
	public Serializable get(Object cle) throws NonExistingKeyException;
	
	public boolean contains(Serializable valeur);
	public boolean containsKey(Object cle);

	public boolean isEmpty();
	public void clear();

	public Enumeration<Object> keys();
	public Enumeration<Serializable> elements();
	
	public Set<Object> keySet();
	public Set<Entry<Object, Serializable>> entrySet();
	
	public Collection<Serializable> values();

	public void remove(Object cle) throws NonExistingKeyException;
}
