package Model;

import java.util.Objects;

public class Realisateur extends Personne {
	
	public Realisateur(String nom) {
		super(nom);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Realisateur)) return false;
		Realisateur that = (Realisateur) o;
		return Objects.equals(getNom(), that.getNom());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNom());
	}
}
