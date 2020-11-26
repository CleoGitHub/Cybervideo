package Model;

import Patterns.Observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;

public class CyberVideo {
    private ArrayList<Film> films = new ArrayList<>();
    private ArrayList<Realisateur> realisateurs = new ArrayList<>();
    private ArrayList<Technicien> techniciens = new ArrayList<>();
    private ArrayList<CarteAbonnement> cartesAbonnements = new ArrayList<>();
    private ArrayList<CarteBancaire> cartesBancaires = new ArrayList<>();
    private CarteBancaire slotCarteBancaire;
    private CarteAbonnement slotCarteAbonnement;
    private Panier panier = new Panier();
    
    public CyberVideo() {
		ArrayList<Acteur> acteurs = new ArrayList<>();
		acteurs = new ArrayList<>();

		acteurs.add(new Acteur("Brad Pitt"));
		acteurs.add(new Acteur("Leonardo Dicaprio"));
		acteurs.add(new Acteur("Vincent Cassel"));
		acteurs.add(new Acteur("Bryan Cranston"));
		acteurs.add(new Acteur("Daniel Radcliffe"));
		acteurs.add(new Acteur("Matthew McConaughey"));

		realisateurs.add(new Realisateur("Quentin Tarantino"));
		realisateurs.add(new Realisateur("Steven Spielberg"));
		realisateurs.add(new Realisateur("Christopher Nolan"));
		
		Film interstellar = new Film("Interstellar", LocalDate.of(2014, 11, 5));
		interstellar.addActeur(acteurs.get(5));
		interstellar.addGenre(Genre.FICTION);
		interstellar.addGenre(Genre.DOCUMENTAIRE);
		interstellar.setRealisateur(realisateurs.get(2));
		interstellar.addDVD(new DVD(4, interstellar));
		films.add(interstellar);
		
		Film inception = new Film("Inception", LocalDate.of(2010, 07, 21));
		inception.addGenre(Genre.FICTION);
		inception.addActeur(acteurs.get(1));
		inception.setRealisateur(realisateurs.get(2));
		inception.addDVD(new DVD(1, inception));
		films.add(inception);
		
		Film onceUponATimeInHollywood = new Film("Once Upon a Time... in Hollywood", LocalDate.of(2019, 07, 24));
		onceUponATimeInHollywood.addGenre(Genre.COMEDIE);
		onceUponATimeInHollywood.addGenre(Genre.DRAMA);
		onceUponATimeInHollywood.setRealisateur(realisateurs.get(0));
		onceUponATimeInHollywood.addActeur(acteurs.get(0));
		onceUponATimeInHollywood.addActeur(acteurs.get(1));
		onceUponATimeInHollywood.addDVD(new DVD(2, onceUponATimeInHollywood));
		onceUponATimeInHollywood.addDVD(new DVD(3, onceUponATimeInHollywood));
		films.add(onceUponATimeInHollywood);
		
		for(int i = 0; i < 25; i++) {
			CarteBancaire carteB = new CarteBancaire(i, "Joe Doe " + i, LocalDate.of(2020, 1, 8), "15 151 151 5351 51 53");	
			cartesBancaires.add(carteB);
			for(int j = 0; j < 4; j++) {
				CarteAbonnement carteA = new CarteAbonnement(carteB, LocalDate.of(2020, 1, 8), j+(j*i), "Abonnement de " + i + " n°" + j);
				cartesAbonnements.add(carteA);
			}
		}
        // TODO: load data from DB

    }

    // actions
    
    public ArrayList<Film> getFilms() {
    	return films;
    }
    
    public Panier getPanier() {
    	return panier;
    }

    void insererFilm(Film film) {
        if (films.contains(film))
            return;

        films.add(film);
        // TODO: insere dans la BD
    }

    void supprimerFilm(Film film, Technicien technicien) {

        if (!techniciens.contains(technicien))
            return;
        if (films.contains(film))
            return;

        films.remove(film);
        // TODO: mise a jour db
    }
    
    public void ajouterPanier(DVD dvd) throws Exception {
    	panier.ajouter(dvd);
    }
    
    public void retirerPanier(DVD dvd) {
    	panier.retirer(dvd);
    }
    
    public ArrayList<CarteAbonnement> getCartesAbonnements() {
    	return this.cartesAbonnements;
    }
    
    public CarteBancaire getCarteSlotCarteBancaire() {
    	return this.slotCarteBancaire;
    }
    
    public void insererCarteBancaire(CarteBancaire carte) throws Exception {
    	// Retirer la carte
    	if(carte == null) {
    		this.slotCarteBancaire = null;
    		return;    		
    	}
    	
    	if(this.slotCarteBancaire != null) {
    		throw new Exception("Une carte bancaire est déjà présente dans le slot carte bancaire");
    	} else {
    		this.slotCarteBancaire = carte;
    	}
    }
    
    public CarteAbonnement getCarteSlotCarteAbonnement() {
    	return this.slotCarteAbonnement;
    }
    
    public void insererCarteAbonnement(CarteAbonnement carte) throws Exception {
    	// Retirer la carte
    	if(carte == null) {
    		this.slotCarteAbonnement = null;
    		return;    		
    	}
    		
    	if(this.slotCarteAbonnement != null) {
    		throw new Exception("Une carte abonnement est déjà présente dans le slot carte abonnement");
    	} else {
        	if(this.slotCarteBancaire != null && !this.slotCarteBancaire.getAbonnements().contains(carte) ) {
        		throw new Exception("La carte abonnement inseré n'appartient pas a la carte bancaire présente dans le slot carte bancaire");
        	} else {
        		this.slotCarteAbonnement = carte;
        	}
    	}
    }

	public ArrayList<CarteBancaire> getCartesBancaires() {
		return cartesBancaires;
	}
}
