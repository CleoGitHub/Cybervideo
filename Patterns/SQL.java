package Patterns;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;
import Model.*;

public class SQL {
    static Connection connect;

//lecture
    public static ArrayList<String> films(){
        ArrayList<String> buf = new ArrayList<>();
        try{
            Statement req = connect.createStatement();
            ResultSet resultat = req.executeQuery("select distinct titre " +
                    "from LesFilms ");
            while (resultat.next()){
                buf.add(resultat.getString("titre"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return buf;
    }
    public static ArrayList<String> acteurs(){
        ArrayList<String> buf = new ArrayList<>();
        try{
            Statement req = connect.createStatement();
            ResultSet resultat = req.executeQuery("select distinct nomActeur " +
                    "from LesFilmActeurs ");
            while (resultat.next()){
                buf.add(resultat.getString("nomActeur"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return buf;
    }
    public static ArrayList<String> filmsSousActeur(String acteur){
        ArrayList<String> buf = new ArrayList<>();
        try{
            Statement req = connect.createStatement();
            ResultSet resultat = req.executeQuery("select distinct nomFilm " +
                    "from LesFilmActeurs " +
                    "where nomActeur='"+acteur+"'");
            while (resultat.next()){
                buf.add(resultat.getString("nomFilm"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return buf;
    }
    public static ArrayList<String> filmsSousGenre(String genre){
        ArrayList<String> buf = new ArrayList<String>();
        try{
            Statement req = connect.createStatement();
            ResultSet resultat = req.executeQuery("select distinct nomFilm " +
                    "from LesFilmGenres " +
                    "where nomGenre='"+genre+"'");
            while (resultat.next()){
                buf.add(resultat.getString("nomFilm"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return buf;
    }
    public static ArrayList<Genre> genreInterdit(String carteAbonnement){
        ArrayList<Genre> buf = new ArrayList<Genre>();
        try{
            Statement req = connect.createStatement();
            ResultSet resultat = req.executeQuery("select distinct genreInterdit " +
                    "from LesInterdits" +
                    " where carteAbonnement='"+carteAbonnement+"'");
            while (resultat.next()){
                String g = resultat.getString("genreInterdit");
                for(Genre G : Genre.values()){
                    if (G.toString() ==g){
                        buf.add(G);
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return buf;
    }
    public static ArrayList<String> filmsSousRealisateur(String nomRealisateur){
        ArrayList<String> buf = new ArrayList<>();
        try{
            Statement req = connect.createStatement();
            ResultSet resultat = req.executeQuery("select distinct titre " +
                    "from LesFilms " +
                    "where realisateur='"+nomRealisateur+"'");
            while (resultat.next()){
                buf.add(resultat.getString("titre"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return buf;
    }
    
    //ecriture
//film et DVD
    public static void putFilm(Film f){
        String titre = f.getTitre();
        Date sqldate = Date.valueOf(f.getDate());
        String realisateur = f.getRealisateur().getNom();
        try{
            Statement req= connect.prepareStatement("insert into LesFilms values(?,?,?)");
            ((PreparedStatement) req).setString(1,titre);
            ((PreparedStatement) req).setDate(2,sqldate);
            ((PreparedStatement) req).setString(3,realisateur);
            ((PreparedStatement) req).execute();

            Statement req1 = connect.createStatement();
            for(Acteur acteur:f.getActeurs()){
                req = connect.prepareStatement("insert into LesFilmActeurs values (?,?)");
                ((PreparedStatement) req).setString(1,titre);
                ((PreparedStatement) req).setString(2,acteur.getNom());
                ((PreparedStatement) req).execute();
            }
            for(Genre genre:f.getGenres()){
                req = connect.prepareStatement("insert into LesFilmGenres values (?,?)");
                ((PreparedStatement) req).setString(1,titre);
                ((PreparedStatement) req).setString(2,genre.toString());
                ((PreparedStatement) req).execute();
            }
            for(DVD dvd:f.getDVDs()){
                putDVD(dvd);
            }
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void putDVD(DVD  dvd){
        String endommage;
        int code = dvd.getCodeBarre();
        String titre = dvd.getFilm().getTitre();
        if(dvd.estEndommage()){
            endommage="oui";
        }
        else{
            endommage="non";
        }
        try{
            //Statement req1 = connect.createStatement();
            Statement req = connect.prepareStatement("insert into LesDVDs values(?,?,?)");
            ((PreparedStatement) req).setInt(1,code);
            ((PreparedStatement) req).setString(2,endommage);
            ((PreparedStatement) req).setString(3,titre);
            ((PreparedStatement) req).execute();
            //req1.executeUpdate("insert into LesDVDs values ("+code+","+endommage+","+titre+")");
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void removeDVD(int dvdCode){
        try{
            Statement req = connect.createStatement();
            req.executeQuery("delete from LesDVDs where codeBarre="+dvdCode);
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void removeFilm(String filmTitre){
        try{
            Statement req = connect.prepareStatement("delete from LesFilmActeurs where nomFilm=?");
            ((PreparedStatement) req).setString(1,filmTitre);
            ((PreparedStatement) req).execute();
            req = connect.prepareStatement("delete from LesFilmGenres where nomFilm=?");
            ((PreparedStatement) req).setString(1,filmTitre);
            ((PreparedStatement) req).execute();
            req = connect.prepareStatement("delete from LesFilms where titre=?");
            ((PreparedStatement) req).setString(1,filmTitre);
            ((PreparedStatement) req).execute();

            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void changeDVDSituation(DVD dvd){
        removeDVD(dvd.getCodeBarre());
        putDVD(dvd);
    }
//carte , abonnement et interdits
    public static void createCarteBancaire(CarteBancaire carte){
        try{
            Statement req= connect.prepareStatement("insert into LesCarteBancaires values(?,?,?,?)");
            ((PreparedStatement) req).setInt(1,carte.getNoCarte());
            ((PreparedStatement) req).setString(2,carte.getLibelle());
            ((PreparedStatement) req).setString(3,carte.getCode());
            ((PreparedStatement) req).setDate(4,Date.valueOf(carte.getDateExp()));
            ((PreparedStatement) req).execute();
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void removeCarteBancaire(String carteLibelle){
        try{
            Statement req = connect.prepareStatement("delete from LesCarteBancaires where libelle=?");
            ((PreparedStatement) req).setString(1,carteLibelle);
            ((PreparedStatement) req).execute();
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void rechargeAbonnement(String abonnementLibelle,int montant){
        try{
            Statement req = connect.prepareStatement("update LesCarteAbonnements set solde=solde+? where(libelle=?)");
            ((PreparedStatement) req).setInt(1,montant);
            ((PreparedStatement) req).setString(2,abonnementLibelle);
            ((PreparedStatement) req).execute();
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void creditAbonnement(String abonnementLibelle,int montant){
        montant = 0-montant;
        rechargeAbonnement(abonnementLibelle,montant);
    }
    public static void createAbonnement(CarteAbonnement abonnement,String cbLibelle){
        try{
            Statement req = connect.prepareStatement("insert into LesCarteAbonnements values(?,?,?,?,?)");
            ((PreparedStatement) req).setInt(1,abonnement.getNoCarte());
            ((PreparedStatement) req).setString(2,abonnement.getLibelle());
            ((PreparedStatement) req).setDate(3,Date.valueOf(abonnement.getDateIns()));
            ((PreparedStatement) req).setInt(4,abonnement.getSolde());
            ((PreparedStatement) req).setString(5,cbLibelle);
            ((PreparedStatement) req).execute();
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void createInterdit(String abonnement,String genre){
        try{
            Statement req = connect.prepareStatement("insert into LesInterdits values(?,?)");
            ((PreparedStatement) req).setString(1,abonnement);
            ((PreparedStatement) req).setString(2,genre);
            ((PreparedStatement) req).execute();
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void removeInterdit(String abonnement,String genre){
        try{
            Statement req = connect.prepareStatement("delete from LesInterdits where carteAbonnement=? and genreInterdit=?");
            ((PreparedStatement) req).setString(1,abonnement);
            ((PreparedStatement) req).setString(2,genre);
            ((PreparedStatement) req).execute();
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void removeAbonnement(String abonnementLibelle){
        try{
            Statement req = connect.prepareStatement("delete from LesCarteAbonnements where libelle=?");
            ((PreparedStatement) req).setString(1,abonnementLibelle);
            ((PreparedStatement) req).execute();
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
//location
    public static void nbjourPlusUn(){
        try{
            Statement req = connect.prepareStatement("update LesLocations set nbJours=nbJours+1 where rendu='non'");
            ((PreparedStatement) req).execute();
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void createLocation(Location l){
        try{
            Statement req = connect.prepareStatement("insert into LesLocations values(?,?,?,?,?,?)");
            ((PreparedStatement) req).setInt(1,l.getDvdLoue().getCodeBarre());
            ((PreparedStatement) req).setDate(2,Date.valueOf(l.getDateDebut()));
            ((PreparedStatement) req).setInt(3,l.getNbJours());
            if(l.estRendu()){
                ((PreparedStatement) req).setString(4,"oui");
            }
            else {
                ((PreparedStatement) req).setString(4,"non");
            }
            if(l.estEndommage()){
                ((PreparedStatement) req).setString(5,"oui");
            }
            else {
                ((PreparedStatement) req).setString(5,"non");
            }
            ((PreparedStatement) req).setString(6,l.getCarteLoueur().getLibelle());
            ((PreparedStatement) req).execute();
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void removeLocation(Location l){
        try{
            Statement req = connect.prepareStatement("delete from LesLocations where DVD=? and dateDebut=? and carteLoueur=?");
            ((PreparedStatement) req).setInt(1,l.getDvdLoue().getCodeBarre());
            ((PreparedStatement) req).setDate(2,Date.valueOf(l.getDateDebut()));
            ((PreparedStatement) req).setString(3,l.getCarteLoueur().getLibelle());
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void changeLocation(Location l){
        removeLocation(l);
        createLocation(l);
    }
//technicien
    public static void createTechnicien(Technicien technicien){
        try{
            Statement req = connect.prepareStatement("insert into LesTechniciens values(?,?)");
            ((PreparedStatement) req).setString(1,technicien.getNom());
            ((PreparedStatement) req).setString(2,technicien.getPassword());
            ((PreparedStatement) req).execute();
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void removeTechnicien(String nom){
        try{
            Statement req = connect.prepareStatement("delete from LesTechniciens where nom=?");
            ((PreparedStatement) req).setString(1,nom);
            ((PreparedStatement) req).execute();
            req.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void changeTechnicien(Technicien technicien){
        removeTechnicien(technicien.getNom());
        createTechnicien(technicien);
    }
    
    public void connect(){
        connect = DBConnection.getInstance();
        System.out.println("connected:");
    }
}
