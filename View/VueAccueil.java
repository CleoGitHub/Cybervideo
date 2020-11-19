package View;

import Model.CyberVideo;
import Patterns.Observateur;

import javax.swing.*;

public class VueAccueil extends JPanel implements Observateur {
    private CyberVideo model;

    public VueAccueil(CyberVideo model) {
        super();
    }

    @Override
    public void miseAJour() {
        // TODO: code pour mis a jour la vue
    }
}
