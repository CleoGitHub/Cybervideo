package Model;

import java.util.Objects;

public class Acteur {
	private String nom;
	
	public Acteur(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Acteur)) return false;
		Acteur acteur = (Acteur) o;
		return Objects.equals(nom, acteur.nom);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}
}
