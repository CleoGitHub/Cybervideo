package Model;

public enum EventType {
	PANIER("panier"),
	FILMS("films"),
	PAYMENT("payment"),
	RENDU("rendu");
	
	public final String nomEvent;
	
	private EventType(String nomEvent) {
		this.nomEvent = nomEvent;
	}
	
	@Override
	public String toString() {
		return this.nomEvent;
	}
}
