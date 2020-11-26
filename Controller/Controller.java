package Controller;

import Model.*;
import View.*;

import javax.swing.*;

import Exceptions.LocationCountExceededException;
import Exceptions.NoCardInSlotException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.PanierEmptyException;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

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
        vueMonCompte = new VueMonCompte(this, model.getCartesBancaires(), model.getCartesAbonnements());
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
    	if(vuesPile.size() > 1 && panel == vuesPile.get(vuesPile.size()-1))
    		return;
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
    public void ajouterPanier(Film f) throws Exception {
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

	public void payer(Boolean withCb) throws NotEnoughMoneyException, LocationCountExceededException, NoCardInSlotException, PanierEmptyException {
		if(withCb) { 
			// Paiement avec carte bancaire
			if(model.getCarteSlotCarteBancaire() == null)
				throw new NoCardInSlotException("Insérez une carte bancaire.");
			
			model.getPanier().payer(getSlotCarteBancaire());
			
		} else {
			// Paiement avec carte abonnement
		if(model.getCarteSlotCarteAbonnement() == null)
				throw new NoCardInSlotException("Insérez une carte d'abonnement.");
			
			model.getPanier().payer(getSlotCarteAbonnement());
		}
		vuePanier.updateDVDs();
		vueTechnicien.refreshModel(); // Update DVDs available count
	}

    public void supprimerFilm(int film) {
        model.supprimerFilm(film);
    }

    public void ajouterFilm(String titre, String realisateur, String[] nomsActeurs, LocalDate date, int dvds) {
        ArrayList<Acteur> acteurs = new ArrayList<>();
        Film film = new Film(titre,date);

        // ajout du acteurs
        for (String nom : nomsActeurs) {
            Acteur acteur = model.getActeur(nom);
            film.addActeur(acteur);
        }

        // l'ajout du DVDs
        for (int i = 0; i < dvds; i++)
            film.addDVD(new DVD(i, film));

        model.ajouterNouvFilm(film);
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

    public void ajouterNouvActeur(String nom) {
        model.ajouterActeur(nom);
    }

    public void supprimerActeur(String nom) {
        model.supprimerActeur(nom);
    }

    public void ajouterNouvRealisateur(String nom) {
        model.ajouterRealisateur(nom);
    }

    public void supprimerRealisateur(String nom) {
        model.supprimerRealisateur(nom);
    }
}
