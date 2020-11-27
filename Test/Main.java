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
		CarteAbonnement ca = new CarteAbonnement(cb, LocalDate.now(), 2, "Abonnement de Jean");
		
		Panier panier = new Panier();
		
		
		System.out.println("================= TEST 1 - SOLDE INSUFFISANT ==============");
		System.out.println("== Paiement avec une carte abonnement de solde 0        ==");
		System.out.println("== Le panier contient 2 DVD.                            ==");
		System.out.println("== Résultat attendu : solde insuffisant pour les 2dvds ===");
		System.out.println("==========================================================");

		try {
			panier.ajouter(getFirstAvailabeDVD(panier, films.get(0))); // Interstellar
			panier.ajouter(getFirstAvailabeDVD(panier, films.get(1))); // Inception
			panier.ajouter(getFirstAvailabeDVD(panier, films.get(2))); // onceUponATime
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		System.out.println("== Paiement ==)");
		try {
			panier.payer(ca);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		
		System.out.println("============= TEST 2 - PAIEMENT SUCCESS ============");
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
		try {
			ArrayList<Location> ls = panier.payer(ca);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		System.out.println("DVDs en cours pour la carte d'abonnement"+ca.getLocationsEnCours());
		
		System.out.println("============== TEST 3 - TROP DE LOCATIONS ==============");
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
		DVD dvd11 = new DVD(11, films.get(1));
		films.get(1).addDVD(dvd11); // Inception
		films.get(1).addDVD(new DVD(12, films.get(1))); // Inception
		try {
			panier.ajouter(getFirstAvailabeDVD(panier, films.get(1)));
			panier.ajouter(getFirstAvailabeDVD(panier, films.get(1)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		System.out.println("Nombre de DVDs loués de ca : " + ca.getLocationsEnCours().size());
		System.out.println("=== Paiement ===");
		try {
			panier.payer(ca);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DVDs dans le panier :"+panier.getDvds());
		System.out.println("Solde de la carte abonnement ca :" + ca.getSolde());
		System.out.println("Nombre de DVDs loués de ca : " + ca.getLocationsEnCours().size());
		
		System.out.println("=================== TEST 4 - RENDU =====================");
		System.out.println("== Rendu du DVD possédant le code barre #11           ==");
		System.out.println("== Resultat attendu : -ca n'a plus la location de #11 ==");
		System.out.println("== - l'historique de ca a la location de #11          ==");
		System.out.println("== - le DVD #11 n'a plus de location en cours         ==");
		System.out.println("========================================================");
		
		// Location de #11
		Location l = ca.getLocationsEnCours().get(0);
		
		System.out.println("DVDs en cours pour la carte d'abonnement "+ca.getLocationsEnCours());
		System.out.println("Historique de location pour la carte d'abonnement "+ca.getHistorique());
		System.out.println("Location en cours de #11 : " + l.getDvdLoue().getLocationEnCours());
		System.out.println("==== RENDU DE #11 ===");
		l.rendreDVD(false); // Pas endommagé
		System.out.println("DVDs en cours pour la carte d'abonnement "+ca.getLocationsEnCours());
		System.out.println("Historique de location pour la carte d'abonnement "+ca.getHistorique());
		System.out.println("Location en cours de #11 : " + l.getDvdLoue().getLocationEnCours());
		
		
		System.out.println("=================== TEST 5 - HISTORIQUE =================");
		System.out.println("== Historique de la CB qui a la CA comme abonnement   ==");
		System.out.println("== Resultat attendu : - l'historique de CA s'affiche  ==");
		System.out.println("========================================================");
		
		System.out.println("Historique de la carte bancaire : " + cb.getHistorique());
	}

}
