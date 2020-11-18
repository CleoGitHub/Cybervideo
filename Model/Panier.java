package Model;
import java.time.LocalDate;
import java.util.ArrayList;


public class Panier
{
	
	private ArrayList<DVD> dvds;//des dvds que l'on ajoute au fur et a mesure de l'utilisation de l'application	 
	private Carte carte;		
			
	public Panier()
	{
		this.dvds = new ArrayList<>();
	}
			
	public ArrayList<DVD> getDvds() 
	{
		return dvds;
	}
	
	public void setDvds(ArrayList<DVD> dvds) 
	{
		this.dvds = dvds;
	}

	public void ajouter(DVD dvd)
	{
		if(dvd != null)
			this.dvds.add(dvd);
	}
	
	public void retirer(DVD dvd)
	{
		this.dvds.remove(dvd);
	}
	
	public ArrayList<Location> payer(Carte c)
	{
		this.carte = c;
		ArrayList<Location> locationsTraitees = new ArrayList<>();
		ArrayList<DVD> dvdsRestants = (ArrayList<DVD>) dvds.clone();
		for(DVD dvd : dvds){
			try {
				Location l = new Location(dvd, LocalDate.now(), 1, c);
				locationsTraitees.add(l);
				dvdsRestants.remove(dvd);
			} catch (Exception e) {
				System.out.println("ERREUR : " + e.getMessage());
			}
		}
		setDvds(dvdsRestants);
		return locationsTraitees;
	}
}
