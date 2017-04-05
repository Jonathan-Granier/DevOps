package main.java.base_de_donnees;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Set;

public interface BDD_Interface {

	public void put(Object cle, Object valeur);
	public Object get(Object cle);
	
	public boolean contains(Object valeur);
	public boolean containsKey(Object cle);

	public boolean isEmpty();
	public void clear();

	public Enumeration<Object> keys();
	public Enumeration<Object> elements();
	
	public Set<Object> keySet();
	public Set<Entry<Object, Object>> entrySet();
	
	public Collection<Object> values();

	public void remove(Object cle);
	//public boolean remove(Object cle, Object valeur);
}
