package Model;
import java.time.LocalDate;
import java.util.ArrayList;

import Exceptions.LocationCountExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.PanierEmptyException;
import Exceptions.PanierFullException;


public class Panier
{
	private ArrayList<DVD> dvds;//des dvds que l'on ajoute au fur et a mesure de l'utilisation de l'application
			
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

	public void ajouter(DVD dvd) throws PanierFullException
	{
		if(dvds.size()+1 > 3)
			throw new PanierFullException("Le panier est plein.");
		
		if(dvd != null && !this.dvds.contains(dvd)) {
			this.dvds.add(dvd);
		}
	}
	
	public void retirer(DVD dvd)
	{
		this.dvds.remove(dvd);
	}
	
	public ArrayList<Location> payer(Carte c) throws LocationCountExceededException, NotEnoughMoneyException, PanierEmptyException
	{
		if(getDvds().size() == 0)
			throw new PanierEmptyException("Veuillez ajouter des DVDs avant de payer.");
		
		// Check if the card can pay
		if(c.canPay(getDvds().size())) {
			ArrayList<Location> locationsTraitees = new ArrayList<>();
			// Pour chaque dvd, créer une location
			for(DVD dvd : dvds){
				Location l;
				try {
					l = new Location(dvd, LocalDate.now(), 1, c);
					locationsTraitees.add(l);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Vider le panier
			dvds.clear();
			return locationsTraitees;
		} else {
			return null;
		}
	}
}
