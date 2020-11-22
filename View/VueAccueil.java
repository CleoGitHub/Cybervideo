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

    // buttons
    private Button rendreBtn;
    private Button allouerBtn;
    private Button technecien;

    public VueAccueil(Controller controller, ArrayList<Film> films) {
        super();
        this.controller = controller;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        setBounds(0, 0, 800, 600);

        this.controller = controller;
        
        this.films = films;
        filmsList = new ItemList<Film, FilmLine>("Nos films", films, controller, Film.class, FilmLine.class);
        add(filmsList);
    }

    public void miseAJour() {
        // TODO: code mettant à jour la vue

        technecien = new Button();
        technecien.setText("Autre ?");

        allouerBtn = new Button("ressources/images/button.png", "Allouer un DVD");
        rendreBtn = new Button("ressources/images/button.png", "Rendre un DVD");

        // buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(allouerBtn);
        buttonsPanel.add(rendreBtn);

        JPanel bienvenuPanel = new JPanel(new BorderLayout());
        bienvenuPanel.add(buttonsPanel);
        bienvenuPanel.add(technecien, BorderLayout.SOUTH);

        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.add(bienvenuPanel);
        // l'ajout du panel
        add(containerPanel);

        // l'ajout du listeners
        setNavigationListeners();
    }

    private void setNavigationListeners() {
        NavigationListener listner = new NavigationListener(controller);
        // TODO: add listener to navigation buttons
        technecien.setId(NavigationListener.TECHNICIEN);
        technecien.addMouseListener(listner);
    }

    public void setFilms(ArrayList<Film> films) {
    	this.films = films;
    	updateFilms();
    }
    
    public void updateFilms() {
    	remove(filmsList);
        filmsList = new ItemList<Film, FilmLine>("Nos films", films, controller, Film.class, FilmLine.class);
        add(filmsList);
        revalidate();
        repaint();
    }
}
