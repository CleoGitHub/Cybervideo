package View;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Controller.Controller;
import Model.DVD;
import Model.Film;

public class AddPanierAction extends AbstractAction {

	
	private Controller c;
	private Film f;
	public AddPanierAction(Film f, Controller c) {
		super();
		this.c = c;
		this.f = f;
		putValue(NAME, "Ajouter au panier");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
