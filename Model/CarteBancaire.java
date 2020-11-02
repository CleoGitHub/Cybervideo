package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CarteBancaire extends Carte {
	private static final int prixParJour = 5;
	
	private ArrayList<CarteAbonnement> abonnements = new ArrayList<CarteAbonnement>();
	private LocalDate dateExp;
	private String code;
	
	public CarteBancaire(int noCarte, String libelle, LocalDate dateExp, String code) {
		super(noCarte, libelle);
		this.setDateExp(dateExp);
		this.setCode(code);
	}
	
	@Override
	public int getPrixParJour() {
		return prixParJour;
	}
	
	public ArrayList<CarteAbonnement> getAbonnement() {
		return abonnements;
	}

	public void ajouterAbonnement(CarteAbonnement abonnement) {
		this.abonnements.add(abonnement);
	}

	public void supprimerAbonnement(CarteAbonnement a) {
		this.abonnements.remove(a);
	}
	
	public void supprimerAbonnement(int i) {
		this.abonnements.remove(i);
	}
	
	public LocalDate getDateExp() {
		return dateExp;
	}

	private void setDateExp(LocalDate dateExp) {
		this.dateExp = dateExp;
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}

	@Override
	public void ajouterLocation(Location l) throws Exception {
		if(this.getLocationsEnCours().size() >= 1)
			throw new Exception("Le nombre maximum de locations en cours ne peut pas dépasser 1 pour une carte bancaire.");
		this.getLocationsEnCours().add(l);
	}

	@Override
	public void retirerLocation(Location l) {
		this.getLocationsEnCours().remove(l);
	}
}
