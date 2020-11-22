package View;

import Controller.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NavigationListener extends MouseAdapter {
    static final int PREC = 0;
    static final int TECHNICIEN = 1;
    static final int ACCUEIL = 2;
    static final int RENDRE = 3;
    static final int PANIER = 4;

    private Controller controller;

    public NavigationListener(Controller controller) {
        super();
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int action = ((Button)e.getSource()).getId();
        switch (action) {
            case PREC:
                controller.vuePrec();
                break;

            case TECHNICIEN:
                controller.vueSuiv(controller.getVueTechnicien());
                break;
                
            case PANIER:
                controller.vueSuiv(controller.getVuePanier());
                break;

            case ACCUEIL:
                controller.vueSuiv(controller.getVueAccueil());

            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
