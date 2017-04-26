package main.java.main;

import java.io.IOException;

import main.java.base_de_donnees.BaseDeDonnees;
import main.java.interfaceserveur.Serveur;
import main.java.stockage_cle_valeur.ServerManager;
import main.java.stockage_cle_valeur.StorageServer;

public class Main {

	public static void main(String[] args) {
		ServerManager svMgr = new ServerManager();
		svMgr.changeBDD(new BaseDeDonnees());
		svMgr.addServer(new StorageServer());
		
		Serveur serveur = new Serveur(svMgr);
		try {
			serveur.StartServeur();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		try {
			svMgr.add(42, 23);
			System.out.println("Bonjour, ca doit faire 23 : " + svMgr.get(42));
		}
		catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}
