package Model;

public abstract class Carte {
	private int noCarte;
	private String libelle;
	
	public Carte(int noCarte, String libelle) {
		this.noCarte = noCarte;
		this.libelle = libelle;
	}
	
	public int getPrixParJour() {
		return -1;
	}

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
}
