package Test;

import java.time.LocalDate;
import java.util.ArrayList;

import Model.*;

public class Main {
	public static DVD getFirstAvailabeDVD(Panier p, Film f) {
		DVD d = null;
		for(DVD dvd : f.getDVDs()) {
			ArrayList<DVD> dvdsPanier = p.getDvds();
			if(dvd.estDisponible() && !dvdsPanier.contains(dvd)) {
				d = dvd;
				break;
			}
		}
		return d;
	}
	
	public static void main(String[] args) {
		ArrayList<Acteur> acteurs = new ArrayList<>();
		ArrayList<Realisateur> realisateurs = new ArrayList<>();
		ArrayList<Film> films = new ArrayList<>();
		acteurs = new ArrayList<>();
		realisateurs = new ArrayList<>();
		films = new ArrayList<>();

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
		
		
		System.out.println("================= TEST 1 =================================");
		System.out.println("== Paiement avec une carte abonnement de solde 0        ==");
		System.out.println("== Le panier contient 2 DVD.                            ==");
		System.out.println("== Résultat attendu : solde insuffisante pour les 2dvds ==");
		System.out.println("==========================================================");

		panier.ajouter(getFirstAvailabeDVD(panier, films.get(0))); // Interstellar
		panier.ajouter(getFirstAvailabeDVD(panier, films.get(1))); // Inception
		panier.ajouter(getFirstAvailabeDVD(panier, films.get(2))); // onceUponATime
		
		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		System.out.println("== Paiement ==)");
		panier.payer(ca);
		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		
		System.out.println("======================= TEST 2 =====================");
		System.out.println("== Paiement avec une carte abonnement de solde 15 ==");
		System.out.println("== Le panier contient 2 DVD.                      ==");
		System.out.println("== Résultat attendu : panier vidé et solde à 7    ==");
		System.out.println("====================================================");
		
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
		
		
		System.out.println("=================== TEST 3 =============================");
		System.out.println("== Ajouter 2 Locations à une carte abonnement         ==");
		System.out.println("== possédant déjà 2 locations                         ==");
		System.out.println("== Resultat attendu : exception levée lors de l'ajout ==");
		System.out.println("== de la seconde location à la carte                  ==");
		System.out.println("========================================================");
		
		try {
			ca.ajouterSolde(15);
		} catch (Exception e) {
			e.printStackTrace();
		}
		films.get(1).addDVD(new DVD(11, films.get(1)));
		films.get(1).addDVD(new DVD(12, films.get(1)));
		panier.ajouter(getFirstAvailabeDVD(panier, films.get(1)));
		panier.ajouter(getFirstAvailabeDVD(panier, films.get(1)));

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
