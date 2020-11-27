package View;

import javax.swing.JPanel;

import Controller.Controller;
import Model.CyberVideo;
import Model.EventType;
import Model.Film;
import Patterns.Observateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class VueAccueil extends Vue {


    JPanel filmsList;
    ArrayList<Film> films;

    private Button panierBtn;


    public VueAccueil(Controller controller, CyberVideo model) {
        super(controller, model);

        this.films = model.getFilms();
        filmsList = new ItemList<Film, FilmLine>("Nos films", films, controller, Film.class, FilmLine.class);
        add(filmsList);
        
        model.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if(e.getPropertyName() == EventType.PANIER.toString() 
						|| e.getPropertyName() == EventType.RENDU.toString())
					updateFilms();
			}
        });
    }

    public void miseAJour() {
        // TODO: code mettant à jour la vue
    }

    private void setNavigationListeners() {
        NavigationListener listener = new NavigationListener(controller);
        // TODO: add listener to navigation buttons
        panierBtn.setId(NavigationListener.PANIER);
        panierBtn.addMouseListener(listener);
    }

    private void setFilms(ArrayList<Film> films) {
    	((ItemList<Film, FilmLine>) filmsList).setItems(films);
    }
    
    private void updateFilms() {
    	((ItemList<Film, FilmLine>) filmsList).drawView();
    }
}
