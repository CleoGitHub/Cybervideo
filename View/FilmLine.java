package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
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
		
		this.setBackground(Color.white);
		setBorder(new EmptyBorder(5,5,5,5));
		
		JLabel titreFilm = new JLabel(film.getTitre());
		add(titreFilm, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
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
