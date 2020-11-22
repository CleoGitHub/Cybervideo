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
    private ArrayList<Carte> abonnees = new ArrayList<>();
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
		interstellar.setRealisateur(realisateurs.get(2));
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
    
    public void ajouterPanier(DVD dvd) {
    	panier.ajouter(dvd);
    }
    
    public void retirerPanier(DVD dvd) {
    	panier.retirer(dvd);
    }

    // TODO: actions de client

}
