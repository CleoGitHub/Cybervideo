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
    private Button techBtn;
    private Button panierBtn;

    public CyberVideoGUI(Controller controller) {
        super("CyberVideo");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.controller = controller;

        contenuPane = controller.getContenuPane();
        add(contenuPane, BorderLayout.CENTER);
        
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(navigationPanel, BorderLayout.NORTH);

        precBtn = new Button("ressources/images/back-button.png","Précedent");
        techBtn = new Button("ressources/images/button.png", "Technicien");
        panierBtn = new Button("ressources/images/button.png", "Panier");

        navigationPanel.add(precBtn);
        navigationPanel.add(techBtn);
        navigationPanel.add(panierBtn);
        navigationPanel.setBackground(Color.gray);

        setNavigationListeners();

        pack();
    }

    private void setNavigationListeners() {
        NavigationListener listener = new NavigationListener(controller);
        // TODO: add listener to navigation buttons
        precBtn.setId(NavigationListener.PREC);
        precBtn.addMouseListener(listener);
        
        techBtn.setId(NavigationListener.TECHNICIEN);
        techBtn.addMouseListener(listener);
        
        panierBtn.setId(NavigationListener.PANIER);
        panierBtn.addMouseListener(listener);
    }
}
