package main.java.stockage_cle_valeur;

import main.java.base_de_donnees.BDD_Interface;

/**
 * TODO
 * @author bizarda
 *
 */
public class RequestHandler {

	BDD_Interface BDD;
	
	/**
	 * Constructeur par defaut
	 */
	public RequestHandler(){
		
	}
	
	/**
	 * Constructeur
	 * @param BDD la BDD a laquelle se "brancher"
	 */
	public RequestHandler(BDD_Interface BDD){
		this.BDD = BDD;
	}

	/**
	 * Ajouter une BDD
	 * @param BDD la BDD a laquelle se "brancher"
	 */
	public void addBDD(BDD_Interface BDD){
		this.BDD = BDD;
	}
	
	/**
	 * Ajouter un element dans la BDD
	 * @param cle une cle
	 * @param valeur l'element a associer a la cle
	 */
	public void add(Integer cle, Object valeur){
		System.out.println("Ajout de " + valeur.toString() + " avec la cle " + cle);
		BDD.put(cle,valeur);
	}
	
	/**
	 * Recuperer l'element associe a une cle
	 * @param cle
	 * @return l'element associe a cle
	 */
	public Object get(Integer cle){
		System.out.println("Acces a " + cle);
		return BDD.get(cle);
	}
	
}
