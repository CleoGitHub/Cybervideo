package View;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import Controller.Controller;
import Model.DVD;
import Model.Film;

public class FilmLine extends JPanel {
	Film film;
	Controller c;
	
	public FilmLine(Film film, Controller c) {
		super(new BorderLayout());
		this.c = c;
		this.film = film;
		
		JLabel titreFilm = new JLabel(film.getTitre());
		add(titreFilm, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		add(buttonsPanel, BorderLayout.EAST);
		
		// Bouton Ajouter au panier
		DVD d;
		if((d = film.getFirstAvailabeDVD()) != null) {
			JButton addPanier = new JButton("Ajouter au panier");
			buttonsPanel.add(addPanier);
			addPanier.setAction(new AddPanierAction(d, c));
		}
		
		// Bouton Info
		JButton info = new JButton("+ d'infos");
		buttonsPanel.add(info);
	}
}
