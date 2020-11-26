package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import Controller.Controller;
import Model.DVD;
import Model.Film;
import Model.Genre;
import Model.Location;

public class LocationLine extends JPanel {
	Location loc;
	Controller c;
	private Button info;
	private JPanel buttonsPanel;
	
	public LocationLine(Location loc, Controller c) {
		super(new BorderLayout());
		
		this.c = c;
		this.loc = loc;
		
		this.setBackground(Color.white);
		setBorder(new EmptyBorder(5,5,5,5));
		
		JPanel dates = new JPanel();
		dates.setLayout(new BorderLayout());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LL yyyy");

		JLabel debut = new JLabel("Début location: " + loc.getDateDebut().format(formatter));
		JLabel fin = new JLabel("Nombre de jours: " + loc.getNbJours());
		
		dates.add(debut, BorderLayout.NORTH);
		dates.add(fin, BorderLayout.SOUTH);
		add(dates, BorderLayout.WEST);
		
		JLabel film = new JLabel(loc.getDvdLoue().getFilm().getTitre());
		String genres = "";
		for(Genre genre: loc.getDvdLoue().getFilm().getGenres())
			genres += genre.libelle + " ";
		JLabel labelGenre = new JLabel(genres);
		
		JPanel infoFilm = new JPanel();
		infoFilm.setLayout(new BorderLayout());
		
		infoFilm.add(film,BorderLayout.NORTH);
		infoFilm.add(labelGenre,BorderLayout.SOUTH);
		add(infoFilm, BorderLayout.EAST);
		
		JLabel status = new JLabel("status: " + (loc.estRendu() ? "RENDU" : "A RENDRE"));
		JPanel statusPanel = new JPanel();
		
		statusPanel.add(status);
		
		add(statusPanel, BorderLayout.CENTER);
	}
}
