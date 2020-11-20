package View;

import Controller.Controller;
import Model.CyberVideo;
import Patterns.Observateur;

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

    @Override
    public void miseAJour() {
        // TODO: code pour mis a jour la vue
    }

    private void setNavigationListners() {
        NavigationListner listner = new NavigationListner(controller);
        // TODO: add listner to navigation buttons
        technecienBtn.setId(NavigationListner.TECHNICIEN);
        technecienBtn.addMouseListener(listner);
    }

}
