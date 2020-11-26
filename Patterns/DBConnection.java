package Pattern;

import java.sql.*;
public class DBConnection {
    private String url = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
    private String user = "xxx";
    private String passwd = "xxx";


    private static Connection connect;

    private DBConnection(){
        try {  connect = DriverManager.getConnection(url, user, passwd); }
        catch (SQLException e) { e.printStackTrace(); }
    }
    public static Connection getInstance(){
        if(connect == null){
            new DBConnection();
            System.out.println("INSTANCIATION DE LA CONNEXION SQL ! ");
        }
        else { System.out.println("CONNEXION SQL EXISTANTE ! ");  }
        return connect;
    }
    public static void disconnect(){
        try {
            connect.close();
            System.out.println("disconnected!");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}

