package main.java.main;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.exception.NonExistingKeyException;
import main.java.stockage_cle_valeur.ServerManager;

public class Main {

	public static void main(String[] args) {
		ServerManager svMgr = new ServerManager();
		svMgr.changeBDD(new BaseDeDonnees());
		svMgr.add(42, 23);
		try {
			System.out.println("Bonjour, ça doit faire 23 : " + svMgr.get(42));
		} catch (NonExistingKeyException e) {
			e.printStackTrace();
		}
	}
}
