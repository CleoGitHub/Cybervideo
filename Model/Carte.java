package Model;

import java.util.ArrayList;

import Exceptions.LocationCountExceededException;
import Exceptions.NotEnoughMoneyException;

public abstract class Carte {
	private int noCarte;
	private String libelle;
	
	private ArrayList<Location> locationsEnCours = new ArrayList<>();
	
	public Carte(int noCarte, String libelle) {
		this.noCarte = noCarte;
		this.libelle = libelle;
	}
	
	public abstract int getPrixParJour();
	
	public abstract void ajouterLocation(Location l) throws Exception;
	public abstract void retirerLocation(Location l);
	public abstract boolean canPay(int nb) throws NotEnoughMoneyException, LocationCountExceededException ;
	
	public int getNoCarte() {
		return noCarte;
	}

	public void setNoCarte(int noCarte) {
		this.noCarte = noCarte;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ArrayList<Location> getLocationsEnCours() {
		return locationsEnCours;
	}
}
