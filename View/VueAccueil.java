package View;

import javax.swing.JPanel;

import Controller.Controller;
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
import java.util.ArrayList;

public class VueAccueil extends Vue implements Observateur {


    private Controller controller; 
    JPanel filmsList;
    ArrayList<Film> films;

    private Button panierBtn;


    public VueAccueil(Controller controller, ArrayList<Film> films) {
        super();
        this.controller = controller;

        this.films = films;
        filmsList = new ItemList<Film, FilmLine>("Nos films", films, controller, Film.class, FilmLine.class);
        add(filmsList);
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

    public void setFilms(ArrayList<Film> films) {
    	((ItemList<Film, FilmLine>) filmsList).setItems(films);
    }
    
    public void updateFilms() {
    	((ItemList<Film, FilmLine>) filmsList).updateView();
    }
}
