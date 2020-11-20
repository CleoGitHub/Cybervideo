package View;

import Controller.Controller;
import Model.CyberVideo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CyberVideoGUI extends JFrame {

    static final int PREC = 0;

    private Controller controller;
    private JPanel contenuPane;
    private Button precBtn;

    public CyberVideoGUI(Controller controller) {
        super("CyberVideo");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.controller = controller;

        contenuPane = controller.getContenuPane();
        add(contenuPane, BorderLayout.CENTER);

        precBtn = new Button("ressources/images/button.png", "précedent");
        add(precBtn, BorderLayout.NORTH);
        setNavigationListners();

        pack();
    }

    private void setNavigationListners() {
        NavigationListener listener = new NavigationListener(controller);
        // TODO: add listener to navigation buttons
        precBtn.setId(NavigationListener.PREC);
        precBtn.addMouseListener(listener);
    }


}
