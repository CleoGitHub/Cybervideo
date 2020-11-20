package View;

import javax.swing.JPanel;

import Controller.Controller;
import Patterns.Observateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VueAccueil extends JPanel implements Observateur {


    private Controller controller;

    // buttons
    private Button technecienBtn;

    public VueAccueil(Controller controller) {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        setBounds(0, 0, 800, 600);
        setBackground(Color.BLUE);

        this.controller = controller;

        technecienBtn = new Button("ressources/images/button.png", "Technicien");
        add(technecienBtn, BorderLayout.CENTER);

        // l'ajout du listners
        setNavigationListners();
    }

    public void miseAJour() {
        // TODO: code pour mis a jour la vue
    }

    private void setNavigationListners() {
        NavigationListener listner = new NavigationListener(controller);
        // TODO: add listner to navigation buttons
        technecienBtn.setId(NavigationListener.TECHNICIEN);
        technecienBtn.addMouseListener(listner);
    }

}
