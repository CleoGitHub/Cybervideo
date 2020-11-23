package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;

import Controller.Controller;
import Model.Acteur;
import Model.DVD;
import Model.Film;
import Model.Genre;
import Model.Panier;

public class VueInfoFilm extends Vue {

	private Controller controller;
	private JPanel dvdsList;
	private Film film;
	
    public VueInfoFilm(Controller controller) {
        super();
        this.controller = controller;
        
        setNavigationListeners();
    }
    

    private void setNavigationListeners() {
        NavigationListener listner = new NavigationListener(controller);
    }
    
    public void setFilm(Film film) {
    	this.removeAll();
    	this.film = film;
    	
    	JPanel panel = new JPanel();
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	
    	JLabel titre = new JLabel("<html><h1>" + film.getTitre() + "</h1></html>");
    	panel.add(titre);
    	
    	JLabel date = new JLabel("<html><h4>Date de Sortie: " + film.getDate() + "</h1></html>");
    	panel.add(date);
    	
		JLabel genreLabel = new JLabel("<html><p>Genres: </p></html>");
		panel.add(genreLabel);
    	for(Genre genre : film.getGenres()) {
    		genreLabel = new JLabel("<html><p>" + genre + "</p></html>");
    		panel.add(genreLabel);
    	}
		genreLabel = new JLabel("<html><p><br></p></html>");
		panel.add(genreLabel);

		JLabel acteurLabel = new JLabel("<html><p>Acteurs: </p></html>");
		panel.add(acteurLabel);
    	for(Acteur acteur : film.getActeurs()) {
    		acteurLabel = new JLabel("<html><p>" + acteur.getNom() + "</p></html>");
    		panel.add(acteurLabel);
    	}
		acteurLabel = new JLabel("<html><p><br></p></html>");
		panel.add(acteurLabel);  	
    	
    	this.add(panel);
    	
    	setVisible(true);
    }
}
