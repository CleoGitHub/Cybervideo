package Model;

public class Personne {
	private String nom;
	
	public Personne(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String toString() {
		return getNom();
	}
}
