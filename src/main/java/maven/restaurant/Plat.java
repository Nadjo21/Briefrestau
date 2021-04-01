//package maven.restaurant;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Plat {
//
//    private int id;
//    private String nomDuPlat;
//    private int prixUnitaire;
//
//    public Plat(String nomDuPlat, int prixUnitaire) {
//        this.nomDuPlat = "nomDuPlat";
//        this.prixUnitaire = prixUnitaire;
//    }
//
//    //je crée ma fonction a appeller ensuite dans le Main pour recuperer la requete SQL
//    //affichant les plats les plus vendus
//
//    public static List<> afficherlesplatslesplusrentables()
//
//        (Connection connection) throws SQLException {
//        // je me connecte a la base et je recupere le détail de la requête crée et testée precedemment
//        Statement ordreSQL = connection.createStatement();
//        ResultSet resultats = ordreSQL.executeQuery("SELECT plat.id,Nom_du_plat,SUM(prix_unitaire*quantite) from plat");
//
//
//
//        List<Plat> PlatsPlusrentables = new ArrayList<>();
//
//
//
//        resultats.close();
//        ordreSQL.close();
//
//    }
//
