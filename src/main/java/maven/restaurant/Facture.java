
package maven.restaurant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Facture {
    private int id;
    private int table_idx;
    private int serveurs_idx;


    public Facture(int table_idx, int serveurs_idx) {
        this.table_idx = table_idx;
        this.serveurs_idx = serveurs_idx;


    }

    public Facture(int newId, int table_idx, int serveurs_idx) {
        id = newId;
        table_idx = table_idx;
        serveurs_idx = serveurs_idx;

    }

    @Override
    public String toString() {
        return id + ". " + table_idx + " " + serveurs_idx  ;
    }

    public void saveFacture(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO table_intermediaire (plat_idx, facture_idx,quantite) VALUES "
                + "('" + table_idx + "','" + serveurs_idx + "')", Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = ordreSQL.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }

        ordreSQL.close();
    }

    public static List<Facture> getFacture(Connection connection) throws SQLException {

        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from facture");

        List<Facture> factureList = new ArrayList<>();

        while (resultats.next()) {
            Facture dbFacture = new Facture(resultats.getInt("id"),
                    resultats.getInt("table_idx"),
                    resultats.getInt("serveurs_idx"));
                   factureList.add(dbFacture);
        }

        resultats.close();
        ordreSQL.close();

        return factureList;
    }
}


