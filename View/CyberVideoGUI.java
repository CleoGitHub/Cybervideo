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

    public CyberVideoGUI(Controller controller) {
        super("CyberVideo");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.controller = controller;

        contenuPane = controller.getContenuPane();
        add(contenuPane, BorderLayout.CENTER);
        
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(navigationPanel, BorderLayout.NORTH);

        precBtn = new Button("ressources/images/back-button.png");

        navigationPanel.add(precBtn);
        // navigationPanel.setBackground(Color.gray);

        setNavigationListeners();

        pack();
    }

    private void setNavigationListeners() {
        NavigationListener listener = new NavigationListener(controller);
        // TODO: add listener to navigation buttons
        precBtn.setId(NavigationListener.PREC);
        precBtn.addMouseListener(listener);

    }
}
