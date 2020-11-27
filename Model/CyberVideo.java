package Model;

import Patterns.Observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;

import Exceptions.DVDNotFoundException;
import Exceptions.DVDNotRentedException;
import Exceptions.ForbiddenGenreException;
import Exceptions.IncorrectAmountException;
import Exceptions.LocationCountExceededException;
import Exceptions.NoCardInSlotException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.PanierEmptyException;
import Exceptions.PanierFullException;

public class CyberVideo {
    private ArrayList<Film> films = new ArrayList<>();
    private ArrayList<Acteur> acteurs = new ArrayList<>();
    private ArrayList<Realisateur> realisateurs = new ArrayList<>();
    private ArrayList<Technicien> techniciens = new ArrayList<>();
    private ArrayList<CarteAbonnement> cartesAbonnements = new ArrayList<>();
    private ArrayList<CarteBancaire> cartesBancaires = new ArrayList<>();
    private ArrayList<DVD> dvds = new ArrayList<>();
    private CarteBancaire slotCarteBancaire;
    private CarteAbonnement slotCarteAbonnement;
    private Panier panier = new Panier();
    
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public CyberVideo() {
    	// Test data
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
		DVD dvdInterstellar = new DVD(4, interstellar);
		getDvds().add(dvdInterstellar);
		interstellar.addDVD(dvdInterstellar);
		films.add(interstellar);
		
		Film inception = new Film("Inception", LocalDate.of(2010, 07, 21));
		inception.addGenre(Genre.FICTION);
		inception.addActeur(acteurs.get(1));
		inception.setRealisateur(realisateurs.get(2));
		DVD dvdInception = new DVD(1, inception);
		getDvds().add(dvdInception);
		inception.addDVD(dvdInception);
		films.add(inception);
		
		Film onceUponATimeInHollywood = new Film("Once Upon a Time... in Hollywood", LocalDate.of(2019, 07, 24));
		onceUponATimeInHollywood.addGenre(Genre.COMEDIE);
		onceUponATimeInHollywood.addGenre(Genre.DRAMA);
		onceUponATimeInHollywood.setRealisateur(realisateurs.get(0));
		onceUponATimeInHollywood.addActeur(acteurs.get(0));
		onceUponATimeInHollywood.addActeur(acteurs.get(1));
		DVD onceUponATimeInHollywood1 = new DVD(2, onceUponATimeInHollywood);
		DVD onceUponATimeInHollywood2 = new DVD(3, onceUponATimeInHollywood);
		getDvds().add(onceUponATimeInHollywood2);
		getDvds().add(onceUponATimeInHollywood1);
		onceUponATimeInHollywood.addDVD(onceUponATimeInHollywood1);
		onceUponATimeInHollywood.addDVD(onceUponATimeInHollywood2);
		films.add(onceUponATimeInHollywood);
		
		for(int i = 0; i < 25; i++) {
			CarteBancaire carteB = new CarteBancaire(i, "Joe" + i, LocalDate.of(2020, 1, 8), "15 151 151 5351 51 53");	
			cartesBancaires.add(carteB);
			for(int j = 0; j < 4; j++) {
				try {
					CarteAbonnement carteA = new CarteAbonnement(carteB, LocalDate.of(2020, 1, 8), j+(j*i), "Abonnement de " + carteB.getLibelle() + " "+ j);
					carteA.ajouterSolde(15);
					carteA.ajouterGenreInterdit(Genre.COMEDIE);
					cartesAbonnements.add(carteA);
				} catch (IncorrectAmountException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

	public ArrayList<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	public ArrayList<Acteur> getActeurs() {
		return acteurs;
	}

	public ArrayList<Technicien> getTechniciens() {
		return techniciens;
	}

	public void insererFilm(Film film) {
        if (films.contains(film))
            return;

        films.add(film);
        // TODO: insere dans la BD
    }
    
    public Acteur getActeur(String nom) {
    	Acteur acteur;
		for (int i = 0; i < acteurs.size(); i++) {
			acteur = acteurs.get(i);
			if (acteur.getNom().equals(nom))
				return acteur;
		}

		return null;
	}

    public void ajouterActeur(String nom) {
    	Acteur nouvActeur = new Acteur(nom);
    	if (!acteurs.contains(nouvActeur)) {
    		acteurs.add(nouvActeur);
    		// TODO: update BD
		} else
    		throw new IllegalArgumentException("Acteur existe");

	}

	public void supprimerActeur(String nom) {
    	Acteur acteur = new Acteur(nom);
		if (acteurs.contains(acteur)) {
			acteurs.remove(acteur);
			// TODO: update BD
		} else
			throw new IllegalArgumentException("Acteur n'existe pas");
	}

	public Realisateur getRealisateur(String nom) {
		Realisateur realisateur;
		for (int i = 0; i < realisateurs.size(); i++) {
			realisateur = realisateurs.get(i);
			if (realisateur.getNom().equals(nom))
				return realisateur;
		}

		return null;
	}

	public void ajouterRealisateur(String nom) {
		Realisateur nouvRealisateur = new Realisateur(nom);
		if (!realisateurs.contains(nouvRealisateur)) {
			realisateurs.add(nouvRealisateur);
			// TODO: update BD
		} else
			throw new IllegalArgumentException("Realisateur existe");

	}

	public void supprimerRealisateur(String nom) {
		Realisateur realisateur = new Realisateur(nom);
		if (realisateurs.contains(realisateur)) {
			realisateurs.remove(realisateur);
			// TODO: update BD
		} else
			throw new IllegalArgumentException("Realisateur n'existe pas");
	}

	public void ajouterNouvFilm(Film film, int nbDvds) {
		if (!films.contains(film)) {
			films.add(film);
			
	        // Find highest code barre existing
			int max = 0;
			for(DVD dvd : dvds) {
				if(dvd.getCodeBarre() > max) {
					max = dvd.getCodeBarre();
				}
			}
			
			// Ajout des DVDs
	        for (int i = max+1; i < max + 1 + nbDvds; i++) {
	        	DVD dvd = new DVD(i, film);
	        	film.addDVD(dvd);
	        	dvds.add(dvd);
	        }
	        
			// TODO: update BD
		} else
			throw new IllegalArgumentException("Film existe");
		
		this.pcs.firePropertyChange(EventType.FILMS_UPDATE.toString(), null, null);
    	// TODO: update BD & notify observers
	}

    public void supprimerFilm(Film film, Technicien technicien) {

        if (!techniciens.contains(technicien))
            return;
        if (films.contains(film))
            return;

        films.remove(film);
		this.pcs.firePropertyChange(EventType.FILMS_UPDATE.toString(), null, null);
        // TODO: mise a jour db
    }

    public void supprimerFilm(int film) {
    	films.remove(film);
		this.pcs.firePropertyChange(EventType.FILMS_UPDATE.toString(), null, null);
		// TODO: mise a jour db

	}
    
    public void ajouterPanier(DVD dvd) throws PanierFullException {
    	panier.ajouter(dvd);
		this.pcs.firePropertyChange(EventType.PANIER_UPDATE.toString(), null, null);
    }
    
    public void retirerPanier(DVD dvd) {
    	panier.retirer(dvd);
		this.pcs.firePropertyChange(EventType.PANIER_UPDATE.toString(), null, null);
    }
    
    public ArrayList<CarteAbonnement> getCartesAbonnements() {
    	return this.cartesAbonnements;
    }
    
    public CarteBancaire getCarteSlotCarteBancaire() {
    	return this.slotCarteBancaire;
    }
    
    public void insererCarteBancaire(CarteBancaire carte) throws Exception {
    	if(carte == null) {
    		// Retirer la carte
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
    	if(carte == null) {
    		// Retirer la carte
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

	public ArrayList<DVD> getDvds() {
		return dvds;
	}	

	
	public void ajouterCarteAbonnement(CarteAbonnement ca) {
		this.cartesAbonnements.add(ca);
		this.pcs.firePropertyChange(EventType.ABONNEMENT.toString(), null, null);		
	}
	
	public DVD findDvd(int codeBarre) {
		for(DVD dvd : dvds) {
			if(dvd.getCodeBarre() == codeBarre)
				return dvd;
		}
		return null;
	}

	public void rendreDVD(int codeBarre, boolean estEndommage) throws DVDNotFoundException, DVDNotRentedException {
		// Find the DVD
		DVD d = findDvd(codeBarre);
		
		if(d == null)
			throw new DVDNotFoundException("DVD non reconnu.");
		
		Location l = d.getLocationEnCours();
		
		// Check if the found DVD has a Location on going
		if(l == null)
			throw new DVDNotRentedException("Ce DVD n'est pas loué.");
		
		l.rendreDVD(estEndommage);
		
		this.pcs.firePropertyChange(EventType.RENDU.toString(), null, null);
	}

	public void payer(Boolean withCb) throws NoCardInSlotException, LocationCountExceededException, NotEnoughMoneyException, PanierEmptyException, ForbiddenGenreException {
		if(withCb) { 
			// Paiement avec carte bancaire
			if(getCarteSlotCarteBancaire() == null)
				throw new NoCardInSlotException("Insérez une carte bancaire.");
			
			getPanier().payer(getCarteSlotCarteBancaire());
			
		} else {
			// Paiement avec carte abonnement
		if(getCarteSlotCarteAbonnement() == null)
				throw new NoCardInSlotException("Insérez une carte d'abonnement.");
			
			getPanier().payer(getCarteSlotCarteAbonnement());
		}
		
		this.pcs.firePropertyChange(EventType.PANIER_UPDATE.toString(), null, null);
		this.pcs.firePropertyChange(EventType.PAYMENT.toString(), null, null);
	}
	
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
