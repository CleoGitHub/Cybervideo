package Controller;

import Model.CyberVideo;
import Model.DVD;
import Model.Film;
import View.*;

import javax.swing.*;

import java.util.ArrayList;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Controller {
    private CyberVideo model;
    private ArrayList<JPanel> vuesPile;
    private JPanel contenuPane;

    // =================
    // Vues
    private CyberVideoGUI frame;
    private VueAccueil vueAccueil;
    private VueTechnicien vueTechnicien;
    private VuePanier vuePanier;

    public Controller() {
        this.model = new CyberVideo();
        vuesPile = new ArrayList<JPanel>();
        contenuPane = new JPanel(new BorderLayout());

        // Creations des Vues
        vueAccueil = new VueAccueil(this, model.getFilms());
        vuePanier = new VuePanier(this, model.getPanier().getDvds());
        vueTechnicien = new VueTechnicien(this);

        start();
    }
    
	public void start() {
		frame = new CyberVideoGUI(this);
		frame.setVisible(true);
        vueSuiv(vueAccueil);
	}

    // getters & setters
    public JPanel getContenuPane() {
        return contenuPane;
    }

    public VueAccueil getVueAccueil() {
        return vueAccueil;
    }

    public VueTechnicien getVueTechnicien() {
        return vueTechnicien;
    }
    
    public VuePanier getVuePanier() {
        return vuePanier;
    }

    public void setOnTop(JPanel panel) {
        contenuPane.removeAll();
        contenuPane.add(panel);
        contenuPane.revalidate();
        contenuPane.repaint();
    }

    public void vuePrec() {
        if (vuesPile.size() > 1) {
            int dernier = vuesPile.size()-1;
            JPanel precPanel = vuesPile.get(dernier-1);
            setOnTop(precPanel);
            vuesPile.remove(dernier);
            frame.pack();
        }
    }

    public void vueSuiv(JPanel panel) {
        setOnTop(panel);
        vuesPile.add(panel);
        frame.pack();
    }
    
    // TODO: actions utilisant le model
    
    public void ajouterPanier(DVD d) {
    	model.ajouterPanier(d);
    	vuePanier.addDVD(d);
    }

}
