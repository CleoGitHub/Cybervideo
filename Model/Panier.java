package Model;

public class Panier
{
  private ArrayList<Location> dvds;//des dvds que l'on ajoute au fur et a mesure de l'utilisation de l'application	 
	private Carte carte;		
			
	public Panier()
	{
		this.dvds = new ArrayList<Location>();
		 
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
		double Prixtotal = 0;
		for(int i=0; i<getDvds().size();i++ )
		{
			Location dvd = dvds.get(i);
			c.getLocationsEnCours().add(dvd);
			Prixtotal = Prixtotal + dvd.calculerPrix();			
		}
		
		//juger le type de carte, carte bancaire ou carte abonnement?????
		if (c instanceof CarteAbonnement ==true)
		{
			 
		}
		
		return c.getLocationsEnCours();
		
		
		
		 
	}
}
