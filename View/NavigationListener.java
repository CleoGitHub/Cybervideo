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
    static final int INFO_FILM = 5;
    static final int ACCOUNT = 6;
    static final int GESTION = 7;
    static final int MENU = 8;
    static final int HISTORIQUE = 9;
    static final int ABONNEMENT = 10;

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
                
            case MENU:
                controller.vueSuiv(controller.getVueBienvenu());
                break;

            case TECHNICIEN:
                controller.vueSuiv(controller.getVueTechnicien());
                break;
                
            case PANIER:
                controller.vueSuiv(controller.getVuePanier());
                break;

            case ACCUEIL:
                controller.vueSuiv(controller.getVueAccueil());
                break;
                
            case INFO_FILM:
            	controller.vueSuiv(controller.getVueInfoFilm());
                break;     
                
            case ACCOUNT:
            	controller.vueSuiv(controller.getVueMonCompte());
                break;
                
            case GESTION:
                controller.vueSuiv(controller.getVueGestionFilms());
                break;
                
            case RENDRE:
                controller.vueSuiv(controller.getVueRendreDVD());
                break;
                
            case HISTORIQUE:
                controller.vueSuiv(controller.getVueHistorique());
                break;
                
            case ABONNEMENT:
                controller.vueSuiv(controller.getVueAbonnement());
                break;
                
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
