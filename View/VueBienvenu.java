package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class VueBienvenu extends Vue {
    private Controller controller;

    // buttons
    private Button rendreBtn;
    private Button allouerBtn;
    private Button technecien;



    public VueBienvenu(Controller controller) {
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

        // l'ajout du listeners
        setNavigationListeners();
    }

    private void setNavigationListeners() {
        NavigationListener listner = new NavigationListener(controller);
        // TODO: add listener to navigation buttons
        technecien.setId(NavigationListener.TECHNICIEN);
        technecien.addMouseListener(listner);
        allouerBtn.setId(NavigationListener.ACCUEIL);
        allouerBtn.addMouseListener(listner);
        rendreBtn.setId(NavigationListener.RENDRE);
        rendreBtn.addMouseListener(listner);
    }



}
