
package maven.restaurant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Tables {
    private int id;
    private int nbre_de_convives;


    public Tables(int nbre_de_convives) {
        this.nbre_de_convives = nbre_de_convives;

    }

    public Tables(int newId, int newnbre_de_convives) {
        id = newId;
        nbre_de_convives= newnbre_de_convives;

    }

    @Override
    public String toString() {
        return id + ". " + "nbreDeConvives" + " "  ;
    }

    public void saveTable(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO tables (nbre_de_convives) VALUES "
                + "('DEFAULT," + "nbreDeConvives"+ "')", Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = ordreSQL.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }

        ordreSQL.close();
    }

    public static List<Tables> getTables(Connection connection) throws SQLException {

        Statement ordreSQL = connection.createStatement();

        ResultSet resultats = ordreSQL.executeQuery("SELECT * from tables");

        List<Tables> tablesList = new ArrayList<>();

        while (resultats.next()) {
            Tables dbTables = new Tables(resultats.getInt("id"),
                    resultats.getInt("nbre_de_convives"));
                    Tables.add(dbTables);
        }

        resultats.close();
        ordreSQL.close();

        return tablesList;
    }
}

