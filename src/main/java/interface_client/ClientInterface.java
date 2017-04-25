package main.java.interface_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.stockage_cle_valeur.RequestHandler;

public class ClientInterface {

	private JFrame frame;
	private JPanel panel_principal;
	private JPanel panel_ajout;
	private JPanel panel_bdd;
	
	private RequestHandler rqHdl;
	
	public ClientInterface(){
		frame = new JFrame("Interface Client");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		panel_ajout = new JPanel();
		JLabel cle = new JLabel("Clé :");
		JTextField val_cle = new JTextField();
		
		JLabel donnee = new JLabel("Donnée :");
		JTextField val_donnee = new JTextField();

		JButton ajouter = new JButton("Ajouter");
		ajouter.addActionListener(new AjouterActionListener(val_cle,val_donnee));
		
		
	}
	
	private class AjouterActionListener implements ActionListener {
		private JTextField val_cle,val_donnee;
		
		public AjouterActionListener(JTextField val_cle,JTextField val_donnee){
			this.val_cle = val_cle;
			this.val_donnee = val_donnee;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!val_cle.getText().equals("") && val_donnee.equals("")){
				
			}
		}
		
	}
}
