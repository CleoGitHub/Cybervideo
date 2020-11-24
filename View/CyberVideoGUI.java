package View;

import Controller.Controller;
import Model.CyberVideo;
import View.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CyberVideoGUI extends JFrame {

    static final int PREC = 0;

    private Controller controller;
    private JPanel contenuPane;
    private Button precBtn;
    private Button panierBtn;
    private Button loginBtn;
    private Button cartes;

    public CyberVideoGUI(Controller controller) {
        super("CyberVideo");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.controller = controller;

        contenuPane = controller.getContenuPane();
        add(contenuPane, BorderLayout.CENTER);
        
        JPanel navigationPanel = new JPanel(new BorderLayout());
        JPanel rightNavigationPanel = new JPanel();
        add(navigationPanel, BorderLayout.NORTH);

        precBtn = new Button("ressources/images/back-button.png");
        panierBtn = new Button("ressources/images/button-thick.png", "Panier");
        loginBtn = new Button("ressources/images/button-thick.png", "Mon Compte");
        cartes = new Button("ressources/images/button-thick.png", "Cartes");

        rightNavigationPanel.add(panierBtn);
        rightNavigationPanel.add(loginBtn);
        rightNavigationPanel.add(cartes);
        navigationPanel.add(precBtn, BorderLayout.WEST);
        navigationPanel.add(rightNavigationPanel, BorderLayout.EAST);
        
        // navigationPanel.setBackground(Color.gray);

        setNavigationListeners();

        pack();
    }

    private void setNavigationListeners() {
        NavigationListener listener = new NavigationListener(controller);
        // TODO: add listener to navigation buttons
        precBtn.setId(NavigationListener.PREC);
        precBtn.addMouseListener(listener);
        panierBtn.setId(NavigationListener.PANIER);
        panierBtn.addMouseListener(listener);
        loginBtn.setId(NavigationListener.ACCOUNT);
        loginBtn.addMouseListener(listener);
        cartes.setId(NavigationListener.CARTES);
        cartes.addMouseListener(listener);
    }
}
