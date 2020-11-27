package Model;

import java.util.Objects;

public class Acteur extends Personne {
	
	public Acteur(String nom) {
		super(nom);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Acteur)) return false;
		Acteur that = (Acteur) o;
		return Objects.equals(getNom(), that.getNom());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNom());
	}
}
