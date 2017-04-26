package main.java.base_de_donnees;

import java.io.Serializable;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map.Entry;

import main.java.exception.NonExistingKeyException;

import java.util.Set;

/**
 * Implementation de BDD_Interface, en Hashtable
 * @author bizarda
 *
 */
public class BaseDeDonnees implements BDDInterface {

	private Hashtable<Object,Serializable> H;

	public BaseDeDonnees(){
		H = new Hashtable<Object,Serializable>();
	}

	public void put(Object cle, Serializable valeur) {
		H.put(cle,valeur);
	}

	public Serializable get(Object cle) throws NonExistingKeyException {
		Serializable res = H.get(cle);
		if(res == null)
			throw new NonExistingKeyException();
		else
			return res;
	}

	public boolean contains(Serializable valeur) {
		return H.contains(valeur);
	}

	public boolean containsKey(Object cle) {
		return H.containsKey(cle);
	}

	public boolean isEmpty() {
		return H.isEmpty();
	}

	public void clear() {
		H.clear();
	}

	public Enumeration<Object> keys() {
		return H.keys();
	}

	public Enumeration<Serializable> elements() {
		return H.elements();
	}

	public Set<Object> keySet() {
		return H.keySet();
	}

	public Set<Entry<Object, Serializable>> entrySet() {
		return H.entrySet();
	}
	
	public Collection<Serializable> values(){
		return H.values();
	}

	public void remove(Object cle) throws NonExistingKeyException {
		Object res = H.remove(cle);
		if(res == null)
			throw new NonExistingKeyException();
	}

}
