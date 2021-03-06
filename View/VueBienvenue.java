package View;

import Controller.Controller;
import Model.CyberVideo;

import javax.swing.*;
import java.awt.*;

public class VueBienvenue extends Vue {
    // buttons
    private Button rendreBtn;
    private Button allouerBtn;
    private Button technecien;

    public VueBienvenue(Controller controller, CyberVideo model) {
        super(controller, model);

        technecien = new Button();
        technecien.setText("Autre ?");

        allouerBtn = new Button("ressources/images/button.png", "Louer un DVD");
        rendreBtn = new Button("ressources/images/button.png", "Rendre un DVD");
        Button logo = new Button("ressources/images/logo.png");

        // logo panel
        JPanel logoPanel = new JPanel(new GridBagLayout());
        logoPanel.add(logo);

        // buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(allouerBtn);
        buttonsPanel.add(rendreBtn);

        JPanel bienvenuPanel = new JPanel(new BorderLayout());
        bienvenuPanel.add(buttonsPanel);
        bienvenuPanel.add(technecien, BorderLayout.SOUTH);
        bienvenuPanel.add(logoPanel, BorderLayout.NORTH);

        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.add(bienvenuPanel);
        // l'ajout du panel
        add(containerPanel);

        // l'ajout du listeners
        setNavigationListeners();
    }

    private void setNavigationListeners() {
        NavigationListener listner = new NavigationListener(getController());
        // TODO: add listener to navigation buttons
        technecien.setId(NavigationListener.TECHNICIEN);
        technecien.addMouseListener(listner);
        allouerBtn.setId(NavigationListener.ACCUEIL);
        allouerBtn.addMouseListener(listner);
        rendreBtn.setId(NavigationListener.RENDRE);
        rendreBtn.addMouseListener(listner);
    }



}
