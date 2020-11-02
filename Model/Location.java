package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class Location {
	private DVD dvdLoue;
	private LocalDate dateDebut;
	private int nbJours;
	private boolean rendu;
	private boolean endommage;
	public Carte carteLoueur;

	public Location(DVD dvdLoue, LocalDate dateDebut, int nbJours, Carte carteLoueur) {
		this.dvdLoue = dvdLoue;
		this.dateDebut = dateDebut;
		this.nbJours = nbJours;
		this.rendu = false;
		this.carteLoueur = carteLoueur;
	}

	public boolean estEndommage() {
		return endommage;
	}

	public void setEndommage(boolean endommage) {
		this.endommage = endommage;
	}

	public Carte getCarteLoueur() {
		return carteLoueur;
	}

	public void setCarteLoueur(Carte carteLoueur) {
		this.carteLoueur = carteLoueur;
	}

	public DVD getDvdLoue() {
		return dvdLoue;
	}

	public void setDvdLoue(DVD dvdLoue) {
		this.dvdLoue = dvdLoue;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}
	
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public int getDue() {
		if (this.rendu) {
			return 0;
		} else {
			int dayDiff = (int) ChronoUnit.DAYS.between(this.dateDebut.plusDays(this.nbJours), LocalDate.now());
			return dayDiff > 0 ? dayDiff * this.carteLoueur.getPrixParJour() : 0;
		}
	}
	
	public int getNbJours() {
		return nbJours;
	}
	
	public void setNbJours(int nbJours) {
		this.nbJours = nbJours;
	}
	
	public boolean estRendu() {
		return rendu;
	}
	
	public void setRendu(boolean rendu) {
		this.rendu = rendu;
	}
	
	public boolean rendre(int codeBarre, boolean endommage) throws Exception {
		if(this.dvdLoue.getCodeBarre() != codeBarre) {
			throw new Exception("Le dvd rendu n'est pas le même que le dvd loué");
		} else {
			this.carteLoueur.retirerLocation(this);
			this.endommage = endommage;
			this.rendu = true;
			return true;
		}
	}
	
	public int calculerPrix() {
		return this.carteLoueur.getPrixParJour() * this.nbJours;
	}
}
