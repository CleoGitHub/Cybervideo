package Test;

import java.time.LocalDate;
import java.util.ArrayList;

import Model.*;

public class Main {

	public static void main(String[] args) {
		ArrayList<Acteur> acteurs = new ArrayList<>();
		ArrayList<Realisateur> realisateurs = new ArrayList<>();
		ArrayList<Film> films = new ArrayList<>();

		acteurs.add(new Acteur("Brad Pitt"));
		acteurs.add(new Acteur("Leonardo Dicaprio"));
		acteurs.add(new Acteur("Vincent Cassel"));
		acteurs.add(new Acteur("Bryan Cranston"));
		acteurs.add(new Acteur("Daniel Radcliffe"));
		acteurs.add(new Acteur("Matthew McConaughey"));

		realisateurs.add(new Realisateur("Quentin Tarantino"));
		realisateurs.add(new Realisateur("Steven Spielberg"));
		realisateurs.add(new Realisateur("Christopher Nolan"));
		
		Film interstellar = new Film("Interstellar", LocalDate.of(2014, 11, 5));
		interstellar.addActeur(acteurs.get(5));
		interstellar.addGenre(Genre.FICTION);
		interstellar.setRealisateur(realisateurs.get(2));
		films.add(interstellar);
		
		Film inception = new Film("Inception", LocalDate.of(2010, 07, 21));
		inception.addGenre(Genre.FICTION);
		inception.addActeur(acteurs.get(1));
		inception.setRealisateur(realisateurs.get(2));
		inception.addDVD(new DVD(1, inception));
		films.add(inception);
		
		Film onceUponATimeInHollywood = new Film("Once Upon a Time... in Hollywood", LocalDate.of(2019, 07, 24));
		onceUponATimeInHollywood.addGenre(Genre.COMEDIE);
		onceUponATimeInHollywood.addGenre(Genre.DRAMA);
		onceUponATimeInHollywood.setRealisateur(realisateurs.get(0));
		onceUponATimeInHollywood.addActeur(acteurs.get(0));
		onceUponATimeInHollywood.addActeur(acteurs.get(1));
		onceUponATimeInHollywood.addDVD(new DVD(2, onceUponATimeInHollywood));
		onceUponATimeInHollywood.addDVD(new DVD(3, onceUponATimeInHollywood));
		films.add(onceUponATimeInHollywood);
		
		CarteBancaire cb = new CarteBancaire(1, "Carte bancaire de Jean", LocalDate.of(2022, 04, 21), "4978111122223333");
		CarteAbonnement ca = new CarteAbonnement(LocalDate.now(), 2, "Abonnement de Jean");
		cb.ajouterAbonnement(ca);
		
		Panier panier = new Panier();
		
		
		System.out.println("============== TEST 1 =============");
		System.out.println("Paiement avec une carte abonnement de solde 0");
		System.out.println("Le panier contient 2 DVD.");
		System.out.println("===================================");

		panier.ajouter(inception.getFirstAvailabeDVD());
		panier.ajouter(onceUponATimeInHollywood.getFirstAvailabeDVD());
		panier.ajouter(interstellar.getFirstAvailabeDVD());
		
		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		System.out.println("Paiement");
		panier.payer(ca);
		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		
		System.out.println("============== TEST 2 =============");
		System.out.println("Paiement avec une carte abonnement de solde 15");
		System.out.println("Le panier contient 2 DVD.");
		System.out.println("===================================");
		
		try {
			ca.ajouterSolde(15);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		System.out.println("=== Paiement ===");
		ArrayList<Location> ls = panier.payer(ca);
		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		
		
		System.out.println("============== TEST 2 =============");
		System.out.println("Ajouter 2 Locations à une carte abonnement");
		System.out.println("possédant déjà 2 locations");
		System.out.println("===================================");
		
		try {
			ca.ajouterSolde(15);
		} catch (Exception e) {
			e.printStackTrace();
		}
		inception.addDVD(new DVD(11, inception));
		inception.addDVD(new DVD(12, inception));
		panier.ajouter(inception.getFirstAvailabeDVD());
		panier.ajouter(inception.getFirstAvailabeDVD());

		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		System.out.println("Nombre de DVDs loués de ca : " + ca.getLocationsEnCours().size());
		System.out.println("=== Paiement ===");
		panier.payer(ca);
		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		System.out.println("Nombre de DVDs loués de ca : " + ca.getLocationsEnCours().size());
	}

}
