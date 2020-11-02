package Model;

public enum Genre {
	HORREUR("Horreur"),
	COMEDIE("Comédie"),
	FICTION("Fiction"),
	DOCUMENTAIRE("Documentaire");
	
	public final String libelle;
	
	private Genre(String libelle) {
		this.libelle = libelle;
	}
}
