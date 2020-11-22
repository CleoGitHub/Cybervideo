package View;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Controller.Controller;
import Model.DVD;

public class AddPanierAction extends AbstractAction {

	
	private Controller c;
	private DVD d;
	public AddPanierAction(DVD d, Controller c) {
		super();
		this.c = c;
		this.d = d;
		putValue(NAME, "Ajouter au panier");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		c.ajouterPanier(d);
	}
}
