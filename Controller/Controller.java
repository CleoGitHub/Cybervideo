package Controller;

import Model.*;
import View.*;

import javax.swing.*;

import java.io.File;
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
    private VueBienvenue vueBienvenu;
    private VueAccueil vueAccueil;
    private VueTechnicien vueTechnicien;
    private VuePanier vuePanier;
    private VueInfoFilm vueInfoFilm;
    private VueMonCompte vueMonCompte;
    private VueCartes vueCartes;
    private VueGestionFilms vueGestionFilms;

    public Controller() {
        this.model = new CyberVideo();
        vuesPile = new ArrayList<Vue>();
        contenuPane = new JPanel(new BorderLayout());

        // Creations des Vues
        vueBienvenu = new VueBienvenue(this);
        vueAccueil = new VueAccueil(this, model.getFilms());
        vuePanier = new VuePanier(this, model.getPanier());
        vueTechnicien = new VueTechnicien(this);
        vueInfoFilm = new VueInfoFilm(this);
        vueMonCompte = new VueMonCompte(this);
        vueCartes = new VueCartes(this);
        vueGestionFilms = new VueGestionFilms(this);

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

    public VueBienvenue getVueBienvenu() {
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
    
    public VueInfoFilm getVueInfoFilm() {
        return vueInfoFilm;
    }
    
    public VueMonCompte getVueMonCompte() {
        return vueMonCompte;
    }
    
    public VueCartes getVueCartes() {
    	return vueCartes;
    }

    public VueGestionFilms getVueGestionFilms() {
        return vueGestionFilms;
    }

    // navigation between panels

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
	
	public ArrayList<Carte> getCartes() {
		return this.model.getCartes();
	}
	
	public void setFimVueInfoFilm(Film film) {
		vueInfoFilm.setFilm(film);
	}
	
	public CarteAbonnement getSlotCarteAbonnement() {
		return this.model.getCarteSlotCarteAbonnement();
	}
	
	public void insererCarteAbonnement(CarteAbonnement ca) throws Exception {
		this.model.insererCarteAbonnement(ca);
	}
	
	public CarteBancaire getSlotCarteBancaire() {
		return this.model.getCarteSlotCarteBancaire();
	}
	
	public void insererCarteBancaire(CarteBancaire cb) throws Exception {
		this.model.insererCarteBancaire(cb);
	}

	public ArrayList<Film> getFilms() {
        return model.getFilms();
    }

    public void supprimerFilm(int film) {
        model.supprimerFilm(film);
    }

    public ArrayList<String> getRealisateurs() {
        ArrayList<String> realisateurs = new ArrayList<String>();
        for (Realisateur realisateur : model.getRealisateurs())
            realisateurs.add(realisateur.getNom());
        return realisateurs;
    }

    public ArrayList<String> getActeurs() {
        ArrayList<String> acteurs = new ArrayList<String>();
        for (Acteur acteur : model.getActeurs())
            acteurs.add(acteur.getNom());
        return acteurs;
    }
}
