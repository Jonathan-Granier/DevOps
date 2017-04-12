package main.java.main;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.exception.NonExistingKeyException;
import main.java.stockage_cle_valeur.RequestHandler;

public class Main {

	public static void main(String[] args) {
		RequestHandler RqHdl = new RequestHandler();
		RqHdl.changeBDD(new BaseDeDonnees());
		RqHdl.add(42, 23);
		try {
			System.out.println("Bonjour, ça doit faire 23 : " + RqHdl.get(42));
		} catch (NonExistingKeyException e) {
			e.printStackTrace();
		}
	}
}
