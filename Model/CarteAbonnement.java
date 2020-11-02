package Model;
import java.time.*;

public class CarteAbonnement extends Carte {
	private static final int prixParJour = 5;
	
	private LocalDate dateIns;
	private int solde;
	
	public CarteAbonnement(LocalDate dateIns, int noCarte, String libelle) {
		super(noCarte, libelle);
		this.setDateIns(dateIns);
		this.setSolde(0);
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
}
