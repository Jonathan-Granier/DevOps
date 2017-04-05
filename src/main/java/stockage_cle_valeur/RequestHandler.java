package main.java.stockage_cle_valeur;

import main.java.base_de_donnees.BDD_Interface;

public class RequestHandler {

	BDD_Interface BDD;
	
	public RequestHandler(){
		
	}
	
	public RequestHandler(BDD_Interface BDD){
		this.BDD = BDD;
	}
	
	public void addBDD(BDD_Interface BDD){
		this.BDD = BDD;
	}
	
	public void add(Integer cle, Object valeur){
		System.out.println("Ajout de " + valeur.toString() + " avec la cle " + cle);
		BDD.put(cle,valeur);
	}
	
	public Object get(Integer cle){
		System.out.println("Acces a " + cle);
		return BDD.get(cle);
	}
	
}
