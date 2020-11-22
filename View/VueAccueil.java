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

public class VueAccueil extends JPanel implements Observateur {


    private Controller controller;
    private ArrayList<Film> films;
    
    JPanel filmsList;


    public VueAccueil(Controller controller, ArrayList<Film> films) {
        super();
        this.controller = controller;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        setBounds(0, 0, 800, 600);
        setBackground(Color.BLUE);

        this.films = films;
        this.controller = controller;
        
        filmsList = new FilmsList(films, controller);
        add(filmsList);
    }

    public void miseAJour() {
        // TODO: code mettant à jour la vue
    }

}
