package Model;
import java.time.*;
import java.util.ArrayList;

public class CarteAbonnement extends Carte {
	private static final int prixParJour = 4;
	
	private LocalDate dateIns;
	private int solde;
	private ArrayList<Genre> genresInterdits = new ArrayList<Genre>();
	private ArrayList<Location> locations = new ArrayList<Location>();
	
	public CarteAbonnement(LocalDate dateIns, int noCarte, String libelle) {
		super(noCarte, libelle);
		this.setDateIns(dateIns);
		this.setSolde(0);
	}
	
	public CarteAbonnement(LocalDate dateIns, int noCarte, String libelle, ArrayList<Genre> genreInterdits) {
		super(noCarte, libelle);
		this.setDateIns(dateIns);
		this.setSolde(0);
		this.genresInterdits = genreInterdits;
	}
	
	@Override
	public int getPrixParJour() {
		return prixParJour;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public LocalDate getDateIns() {
		return dateIns;
	}

	public void setDateIns(LocalDate dateIns) {
		this.dateIns = dateIns;
	}

	public ArrayList<Genre> getGenresInterdits() {
		return genresInterdits;
	}

	public void ajouterGenreInterdit(Genre g) {
		genresInterdits.add(g);
	}

	public void supprimerGenreInterdit(Genre g) {
		genresInterdits.remove(g);
	}
	
	public void supprimerGenreInterdit(int i) {
		genresInterdits.remove(i);
	}
	
	public ArrayList<Location> getLocations() {
		return locations;
	}

	public void ajouterLocation(Location l) {
		locations.add(l);
	}
	
	public void supprimerLocation(Location l) {
		locations.remove(l);
	}
	
	public void supprimerLocation(int i) {
		locations.remove(i);
	}
}
