package View;

import Controller.Controller;
import Model.CyberVideo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CyberVideoGUI extends JFrame {

    static final int PREC = 0;

    private CyberVideo model;
    private Controller controller;
    private JPanel contenuPane;
    private Button precBtn;

    public CyberVideoGUI() {
        super("Dlub");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        model = new CyberVideo();
        controller = new Controller(model);

        contenuPane = controller.getContenuPane();
        add(contenuPane, BorderLayout.CENTER);

        precBtn = new Button("ressources/images/button.png", "précedent");
        add(precBtn, BorderLayout.NORTH);
        setNavigationListners();

        pack();
    }

    private void setNavigationListners() {
        NavigationListner listner = new NavigationListner(controller);
        // TODO: add listner to navigation buttons
        precBtn.setId(NavigationListner.PREC);
        precBtn.addMouseListener(listner);
    }

}
