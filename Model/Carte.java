package Model;

import java.util.ArrayList;

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
