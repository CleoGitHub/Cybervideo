package Model;
import java.time.*;
import java.util.ArrayList;

public class CarteAbonnement extends Carte {
	private static final int prixParJour = 4;
	
	private LocalDate dateIns;
	private int solde;
	private ArrayList<Genre> genresInterdits = new ArrayList<Genre>();
	private ArrayList<Location> historique = new ArrayList<Location>();
	
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
	
	public void retirerSolde(int montant) throws Exception {
		if(this.solde - montant < 0)
			throw new Exception("Le montant de la carte ne peut pas être inférieur à 0.");
		
		this.solde -= montant;
	}
	
	public void ajouterSolde(int montant) throws Exception {
		if(montant < 10)
			throw new Exception("La somme à ajouter doit être de minimum 10€.");
		if(this.solde + montant < 15) {
			throw new Exception("Le montant final de la carte doit être de minimum 15€.");
		}
		this.solde += montant;
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
	
	public ArrayList<Location> getHistorique() {
		return historique;
	}

	public void ajouterLocationHistorique(Location l) {
		historique.add(l);
	}
	
	public void supprimerLocationHistorique(Location l) {
		historique.remove(l);
	}
	
	public void supprimerLocationHistorique(int i) {
		historique.remove(i);
	}

	@Override
	public void ajouterLocation(Location l) throws Exception {
		if(this.getLocationsEnCours().size() >= 3)
			throw new Exception("Le nombre maximum de locations en cours ne peut pas dépasser 3 pour une carte d'abonné.");
		this.getLocationsEnCours().add(l);
	}

	@Override
	public void retirerLocation(Location l) {
		this.getLocationsEnCours().remove(l);
	}
}
