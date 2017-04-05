package serveur.java.cache_BDD;

import java.util.Hashtable;

public class Cache_BDD implements Cache_BDD_Interface{
	Hashtable<Object,Object> H;

	public Cache_BDD(){
		H = new Hashtable<Object,Object>();
	}

	public void put(Object cle, Object valeur) {
		H.put(cle,valeur);
	}

	public Object get(Object cle) {
		return H.get(cle);
	}
}
