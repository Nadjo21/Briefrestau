
        package maven.restaurant;

        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.List;

        public class Plat {
    private int id;
    private String platConsomme;
    private double prixUnitaire;



    public Plat(String platConsomme, double prixUnitaire) {
        this.platConsomme = platConsomme;
        this.prixUnitaire = prixUnitaire;
    }

    public Plat(int newId, String newNomDuPlat, String newprixUnitaire) {
        id = newId;
        newNomDuPlat = newNomDuPlat;
        newprixUnitaire = newprixUnitaire;
    }

    @Override
    public String toString() {
        return id + ". " + platConsomme + " " + prixUnitaire;
    }

    public void savePlat(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO plat (\"Nom_du_plat\", prix_unitaire) "
                + "VALUES ( '" + platConsomme + "','" + prixUnitaire + "')", Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = ordreSQL.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }

        ordreSQL.close();
   }

    public static List<Plat> getPLat(Connection connection) throws SQLException {
        // Pouvoir lister des plats
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from plat");

        List<Plat> platList = new ArrayList<>();

        while (resultats.next()) {
            Plat dbPlat = new Plat(resultats.getInt("id"),
                    resultats.getString("Nom_du_plat"),
                    resultats.getString("prix_unitaire"));
           platList.add(dbPlat);
        }

        resultats.close();
        ordreSQL.close();

        return platList;
    }
}

