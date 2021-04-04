
package maven.restaurant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Tableintermediaire {
    private int id;
    private int plat_idx;
    private int facture_idx;
    private int quantite;





    public Tableintermediaire(int plat_idx, int facture_idx, int quantite) {
        this.plat_idx = plat_idx;
        this.facture_idx = facture_idx;
        this.quantite=quantite;

    }

//    public Tableintermediaire(int newId, int plat_idx, int facture_idx, int quantite) {
//        id = newId;
//        new plat_idx = plat_idx;
//        new facture_idx = facture_idx;
//        new quantite=quantite;
//    }

    @Override
    public String toString() {
        return id + ". " + plat_idx + " " + facture_idx + quantite ;
    }

    public void saveTableintermediaire(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO table_intermediaire (plat_idx, facture_idx,quantite) VALUES "
                + "('" + plat_idx + "','" + facture_idx + quantite+"')", Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = ordreSQL.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }

        ordreSQL.close();
    }

    public static List<Tableintermediaire> getTableintermediaire(Connection connection) throws SQLException {

        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from table_intermediaire");

        List<Tableintermediaire> tableintermediaireList = new ArrayList<>();

        while (resultats.next()) {
            Tableintermediaire dbTableint = new Tableintermediaire(resultats.getInt("id"),
                    resultats.getInt("plat_idx"),
                    resultats.getInt("facture_idx"));
            resultats.getInt("quantite");
            tableintermediaireList.add(dbTableint);
        }

        resultats.close();
        ordreSQL.close();

        return tableintermediaireList;
    }
}

