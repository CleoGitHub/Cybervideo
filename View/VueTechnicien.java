package View;

import Controller.Controller;
import Patterns.Observateur;

import javax.swing.*;
import java.awt.*;

public class VueTechnicien extends JPanel implements Observateur {
    private Controller controller;

    public VueTechnicien(Controller controller) {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        setBounds(0, 0, 800, 600);
        setBackground(Color.RED);


        this.controller = controller;

    }

    @Override
    public void miseAJour() {

    }
}
