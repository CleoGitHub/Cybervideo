package Model;
import java.util.ArrayList;


public class Panier
{
  private ArrayList<Location> dvds;//des dvds que l'on ajoute au fur et a mesure de l'utilisation de l'application	 
	private Carte carte;		
			
	public Panier()
	{
		this.dvds = new ArrayList<>();
	}
			
	public ArrayList<Location> getDvds() 
	{
		return dvds;
	}
	
	public void setDvds(ArrayList<Location> dvds) 
	{
		this.dvds = dvds;
	}

	public void ajouter(Location dvd)
	{
		this.dvds.add(dvd);
	}
	
	public void retirer(Location dvd)
	{
		this.dvds.remove(dvd);
	}
	
	public ArrayList<Location> payer(Carte c)
	{
		this.carte = c;
		ArrayList<Location> locationsTraitees = new ArrayList<>();
		for(int i=0; i<getDvds().size();i++ )
		{
			Location dvd = dvds.get(i);
			try {
				c.ajouterLocation(dvd);
				locationsTraitees.add(dvd);
				retirer(dvd);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return locationsTraitees;
	}
}
