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
    private ArrayList<Vue> vuesPile;
    private JPanel contenuPane;

    // =================
    // Vues
    private CyberVideoGUI frame;
    private VueBienvenu vueBienvenu;
    private VueAccueil vueAccueil;
    private VueTechnicien vueTechnicien;
    private VuePanier vuePanier;

    public Controller() {
        this.model = new CyberVideo();
        vuesPile = new ArrayList<Vue>();
        contenuPane = new JPanel(new BorderLayout());

        // Creations des Vues
        vueBienvenu = new VueBienvenu(this);
        vueAccueil = new VueAccueil(this, model.getFilms());
        vuePanier = new VuePanier(this, model.getPanier());
        vueTechnicien = new VueTechnicien(this);

        start();
    }
    
	public void start() {
		frame = new CyberVideoGUI(this);
		frame.setVisible(true);
        vueSuiv(vueBienvenu);
	}

    // getters & setters
    public JPanel getContenuPane() {
        return contenuPane;
    }

    public VueBienvenu getVueBienvenu() {
        return vueBienvenu;
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

    public void setOnTop(Vue panel) {
        contenuPane.removeAll();
        contenuPane.add(panel);
        contenuPane.revalidate();
        contenuPane.repaint();
        contenuPane.updateUI();
    }

    public void vuePrec() {
        if (vuesPile.size() > 1) {
            int dernier = vuesPile.size()-1;
            Vue precPanel = vuesPile.get(dernier-1);
            setOnTop(precPanel);
            vuesPile.remove(dernier);
            frame.pack();
        }
    }

    public void vueSuiv(Vue panel) {
        setOnTop(panel);
        vuesPile.add(panel);
        frame.pack();
    }

    public void setVue(Vue panel) {
        vuesPile.clear();
        vuesPile.add(vueAccueil);
        setOnTop(panel);
    }

    // TODO: actions utilisant le model
    public void ajouterPanier(Film f) {
    	model.ajouterPanier(getFirstAvailabeDVD(f));
    	vuePanier.updateDVDs();
    }
    
    public void retirerPanier(DVD d) {
    	model.retirerPanier(d);
    	vueAccueil.updateFilms();
    }
    
	public DVD getFirstAvailabeDVD(Film f) {
		DVD d = null;
		for(DVD dvd : f.getDVDs()) {
			ArrayList<DVD> dvdsPanier = model.getPanier().getDvds();
			if(dvd.estDisponible() && !dvdsPanier.contains(dvd)) {
				d = dvd;
				break;
			}
		}
		return d;
	}

}
