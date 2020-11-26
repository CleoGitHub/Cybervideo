package Model;

import java.util.Objects;

public class Realisateur {
	private String nom;
	
	public Realisateur(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Realisateur)) return false;
		Realisateur that = (Realisateur) o;
		return Objects.equals(nom, that.nom);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}
}
