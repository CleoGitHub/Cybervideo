package Model;

import java.util.ArrayList;

public class Technicien {
	private String nom;
	private String password;
	private ArrayList<Location> listeLocations;
	
	public Technicien(String nom, String password) {
		this.nom = nom;
		this.password = password;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<Location> getListeLocations() {
		return listeLocations;
	}
	
	public void setListeLocations(ArrayList<Location> listeLocations) {
		this.listeLocations = listeLocations;
	}	
}
