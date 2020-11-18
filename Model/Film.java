package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Film {
	private String titre;
	private LocalDate date;
	private ArrayList<Genre> genres;
	private ArrayList<Acteur> acteurs;
	private Realisateur realisateur;
	private ArrayList<DVD> dvds;
	
	public Film(String titre, LocalDate date) {
		this.titre = titre;
		this.date = date;
		this.dvds = new ArrayList<DVD>();
		this.genres = new ArrayList<Genre>();
		this.acteurs = new ArrayList<Acteur>();
	}

	public String getTitre() {
		return titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public ArrayList<DVD> getDVDs() {
		return this.dvds;
	}
	
	public DVD getFirstAvailabeDVD() {
		DVD d = null;
		for(DVD dvd : dvds) {
			if(dvd.estDisponible() && !dvd.estEndommage()) {
				d = dvd;
				break;
			}
		}
		return d;
	}
	
	public void addDVD(DVD dvd) {
		this.dvds.add(dvd);
	}
	
	public void removeDVD(DVD dvd) {
		this.dvds.remove(dvd);
	}	
	
	public ArrayList<Acteur> getActeurs() {
		return this.acteurs;
	}
	
	public void addActeur(Acteur acteur) {
		this.acteurs.add(acteur);
	}
	
	public void removeActeur(Acteur acteur) {
		this.acteurs.remove(acteur);
	}
	
	public ArrayList<Genre> getGenres() {
		return this.genres;
	}
	
	public void addGenre(Genre genre) {
		this.genres.add(genre);
	}
	
	public void removeGenre(Genre genre) {
		this.genres.remove(genre);
	}

	public Realisateur getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(Realisateur realisateur) {
		this.realisateur = realisateur;
	}
	
}
