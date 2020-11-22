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

public class VueAccueil extends Vue implements Observateur {


    private Controller controller;

    // buttons
    private Button rendreBtn;
    private Button allouerBtn;
    private Button technecien;

    public VueAccueil(Controller controller) {
        super();
        setBackground(Color.BLUE);

        this.controller = controller;

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

        // l'ajout du listners
        setNavigationListners();
    }

    public void miseAJour() {
        // TODO: code pour mis a jour la vue
    }

    private void setNavigationListners() {
        NavigationListener listner = new NavigationListener(controller);
        // TODO: add listner to navigation buttons
        technecien.setId(NavigationListener.TECHNICIEN);
        technecien.addMouseListener(listner);
    }

}
