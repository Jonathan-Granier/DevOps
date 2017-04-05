package main.java.stockage_cle_valeur;

import java.util.Hashtable;

public class RequestHandler {

	Hashtable<Integer,Object> H;
	
	public RequestHandler(){
		H = new Hashtable<Integer,Object>();
	}
	
	public void add(Integer cle, Object valeur){
		System.out.println("Ajout de " + valeur.toString() + " avec la cle " + cle);
	}
	
	public Object get(Integer cle){
		System.out.println("Acces a " + cle);
		return null;
	}
}
