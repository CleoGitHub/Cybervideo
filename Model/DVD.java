package Model;

public class DVD {
	private int codeBarre;
	private boolean endommage;
	private Location locationEnCours;
	
	public DVD(int codeBarre) {
		this.codeBarre = codeBarre;
		this.endommage = false;
		this.locationEnCours = null;
	}
	
	public int getCodeBarre() {
		return codeBarre;
	}
	
	public void setCodeBarre(int codeBarre) {
		this.codeBarre = codeBarre;
	}
	
	public boolean estEndommage() {
		return endommage;
	}
	
	public void setEndommage(boolean endommage) {
		this.endommage = endommage;
	}
	
	public Location getLocationEnCours() {
		return locationEnCours;
	}
	
	public void setLocationEnCours(Location locationEnCours) {
		this.locationEnCours = locationEnCours;
	}
	
	public boolean estDisponible() {
		return this.locationEnCours == null;
	}
	
	
	
}
