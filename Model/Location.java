package Model;

import java.time.LocalDate;
import java.util.ArrayList;

import Exceptions.LocationCountExceededException;
import Exceptions.NotEnoughMoneyException;

import java.time.temporal.ChronoUnit;

public class Location {
	private DVD dvdLoue;
	private LocalDate dateDebut;
	private int nbJours;
	private boolean rendu;
	public Carte carteLoueur;

	public Location(DVD dvdLoue, LocalDate dateDebut, int nbJours, Carte carteLoueur) throws LocationCountExceededException, NotEnoughMoneyException {
		this.dvdLoue = dvdLoue;
		this.dateDebut = dateDebut;
		this.nbJours = nbJours;
		this.rendu = false;
		this.carteLoueur = carteLoueur;
		
		dvdLoue.setLocationEnCours(this);
		carteLoueur.ajouterLocation(this);
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
	
	public void rendreDVD(boolean endommage) {
		this.carteLoueur.retirerLocation(this);
		this.dvdLoue.setLocationEnCours(null);
		this.dvdLoue.setEndommage(endommage);
		this.rendu = true;
	}
	
	public int calculerPrix() {
		return this.carteLoueur.getPrixParJour() * this.nbJours;
	}
}
