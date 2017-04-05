package main.java.base_de_donnees;

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
public class BaseDeDonnees implements BDD_Interface {

	Hashtable<Object,Object> H;

	public BaseDeDonnees(){
		H = new Hashtable<Object,Object>();
	}

	public void put(Object cle, Object valeur) {
		H.put(cle,valeur);
	}

	public Object get(Object cle) throws NonExistingKeyException {
		return H.get(cle);
	}

	public boolean contains(Object valeur) {
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

	public Enumeration<Object> elements() {
		return H.elements();
	}

	public Set<Object> keySet() {
		return H.keySet();
	}

	public Set<Entry<Object, Object>> entrySet() {
		return H.entrySet();
	}
	
	public Collection<Object> values(){
		return H.values();
	}

	public void remove(Object cle) throws NonExistingKeyException {
		H.remove(cle);
	}

	public boolean remove(Object cle, Object valeur) throws NonExistingKeyException {
		return H.remove(cle,valeur);
	}

}
