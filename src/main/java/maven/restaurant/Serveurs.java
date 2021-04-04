
package maven.restaurant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Serveurs {
    private int id;
    private String nom;
    private String prenom;

    public Serveurs(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Serveurs(int newId, String newNom, String newPrenom) {
        id = newId;
        newNom = newNom;
        newPrenom = newPrenom;
    }

    @Override
    public String toString() {
        return id + ". " + nom + " " + prenom;
    }

    public void saveServeurs(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO serveurs (nom, prenom) "
                + "VALUES ('DEFAULT," + nom + "','" + prenom+ "')", Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = ordreSQL.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }

        ordreSQL.close();
    }

    public static List<Serveurs> getServeurs(Connection connection) throws SQLException {
        // Pouvoir lister les serveurs
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from serveurs");

        List<Serveurs> serveursList = new ArrayList<>();

        while (resultats.next()) {
            Serveurs dbServeurs = new Serveurs(resultats.getInt("id"),
                    resultats.getString("nom"),
                    resultats.getString("prenom"));
            serveursList.add(dbServeurs);
        }

        resultats.close();
        ordreSQL.close();

        return serveursList;

    }
}

