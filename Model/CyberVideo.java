package Model;

import Patterns.Observable;

import java.util.ArrayList;

public class CyberVideo extends Observable {
    private ArrayList<Film> films;
    private ArrayList<Realisateur> realisateurs;
    private ArrayList<Technicien> techniciens;
    private ArrayList<Carte> abonnees;
    private Panier panier;


    public CyberVideo() {
        // TODO: load data from DB

    }

    // actions

    void insererFilm(Film film) {
        if (films.contains(film))
            return;

        films.add(film);
        // TODO: insere dans la BD
        metAJour();
    }

    void supprimerFilm(Film film, Technicien technicien) {

        if (!techniciens.contains(technicien))
            return;
        if (films.contains(film))
            return;

        films.remove(film);
        // TODO: mise a jour db
        metAJour();
    }

    // TODO: actions de client

}
