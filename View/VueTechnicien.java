package View;

import Controller.Controller;
import Patterns.Observateur;

import javax.swing.*;
import java.awt.*;

public class VueTechnicien extends Vue implements Observateur {
    private Controller controller;

    public VueTechnicien(Controller controller) {
        super();
        setBackground(Color.RED);


        this.controller = controller;

    }

    @Override
    public void miseAJour() {

    }
}
