package Model;

public enum Genre {
	HORREUR("Horreur"),
	COMEDIE("Comédie"),
	FICTION("Fiction"),
	DOCUMENTAIRE("Documentaire"),
	DRAMA("Drama");

	public final String libelle;
	
	private Genre(String libelle) {
		this.libelle = libelle;
	}
	
	@Override
	public String toString() {
		return this.libelle;
	}
}
