package Model;
import java.time.*;
import java.util.ArrayList;

import Exceptions.IncorrectAmountException;
import Exceptions.LocationCountExceededException;
import Exceptions.NotEnoughMoneyException;

public class CarteAbonnement extends Carte {
	public static final int prixParJour = 4;
	
	private CarteBancaire cb;
	private LocalDate dateIns;
	private int solde;
	private ArrayList<Genre> genresInterdits = new ArrayList<>();
	private ArrayList<Location> historique = new ArrayList<>();
	
	public CarteAbonnement(CarteBancaire cb, LocalDate dateIns, int noCarte, String libelle) {
		super(noCarte, libelle);
		this.setCb(cb);
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
	
	public void retirerSolde(int montant) throws NotEnoughMoneyException {
		if(this.solde - montant < 0)
			throw new NotEnoughMoneyException("Le montant de la carte ne peut pas être inférieur à 0.");
		
		this.solde -= montant;
	}
	
	public void ajouterSolde(int montant) throws IncorrectAmountException {
		if(montant < 10)
			throw new IncorrectAmountException("La somme à ajouter doit être de minimum 10€.");
		if(this.solde + montant < 15) {
			throw new IncorrectAmountException("Le montant final de la carte doit être de minimum 15€.");
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
	public void ajouterLocation(Location l) throws LocationCountExceededException, NotEnoughMoneyException {
		if(this.getLocationsEnCours().size() >= 3)
			throw new LocationCountExceededException("Le nombre maximum de locations en cours ne peut pas dépasser 3 pour une carte d'abonné.");		
		this.retirerSolde(l.calculerPrix());
		this.getLocationsEnCours().add(l);
	}

	@Override
	public void retirerLocation(Location l) {
		this.getLocationsEnCours().remove(l);
		this.ajouterLocationHistorique(l);
	}

	public CarteBancaire getCb() {
		return cb;
	}

	public void setCb(CarteBancaire cb) {
		this.cb = cb;
		this.cb.ajouterAbonnement(this);
	}

	@Override
	public boolean canPay(int nb) throws NotEnoughMoneyException, LocationCountExceededException {
		// Check si la carte a assez d'argent pour payer
		int montantADeduire = nb * getPrixParJour();
		if(this.solde - montantADeduire < 0)
			throw new NotEnoughMoneyException("Le montant de la carte ne peut pas être inférieur à 0.");
		
		if(this.getLocationsEnCours().size() + nb >= 3)
			throw new LocationCountExceededException("Le nombre maximum de locations en cours ne peut pas dépasser 3 pour une carte d'abonné.");	
		
		return true;
	}
	
}
