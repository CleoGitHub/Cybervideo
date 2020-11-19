package View;

import Controller.Controller;
import Model.CyberVideo;
import Patterns.Observateur;

import javax.swing.*;

public class VueAccueil extends JPanel implements Observateur {
    private Controller controller;

    public VueAccueil(Controller controller) {
        super();
    }

    @Override
    public void miseAJour() {
        // TODO: code pour mis a jour la vue
    }
}
