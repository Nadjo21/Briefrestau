package maven.restaurant;

import java.sql.*;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws SQLException {

        //ici on recupere les infos pour nous connecter à notre base de données

        String url = "jdbc:postgresql://localhost:5432/maven-restaurant";
        String user = "postgres";
        String password = "postgres";

        //on se connecte en creant l'appel a la BDD
        try {

            Connection connection = DriverManager.getConnection(url, user, password);


            //Import de la classe Scanner

            Scanner sc = new Scanner(System.in);

            //Demander au restaurateur ce qu'il souhaite faire


            System.out.println("Bonjour");
            System.out.println("Pour afficher les plats les plus vendus tapez 1");
            System.out.println("Pour afficher les tables les plus rentables tapez 2");
            System.out.println("Pour sauvegarder une nouvelle facture tapez 3");

            int choix = sc.nextInt();
            sc.nextLine();

            if (choix == 1) {
                //afficher les plats les plus vendus


                //Récuperation dans la BDD des plats et prix à partir de la requete deja testée en sql


                Statement statement = connection.createStatement();

                ResultSet listePlats = statement.executeQuery("select plat.id,\"Nom_du_plat\",SUM(prix_unitaire*quantite) as total "
                        + "from plat  join table_intermediaire on plat.id = table_intermediaire.plat_idx "
                        + "join facture on facture.id=table_intermediaire.facture_idx "
                        + "group by plat.id order by total desc");


                //affichage du resultat en parcourant nos lignes de résultats de requetes telles qu'elles appraissent dans la console SQL

                while (listePlats.next()) {

                    System.out.println("Plat" + " / " + " CA");
                    System.out.println(listePlats.getString("Nom_du_plat") + "   " + listePlats.getInt("total") + "€");

                }


                //     ResultSet.close();

                statement.close();

            }

            if (choix == 2) {

                Statement statement = connection.createStatement();

                ResultSet tablesRentables = statement.executeQuery("select SUM(quantite*prix_unitaire) as Total,table_idx from table_intermediaire "
                        + "join facture on facture.id=table_intermediaire.facture_idx "
                        + "join plat on plat.id = table_intermediaire.plat_idx "
                        + "group by table_idx "
                        + "order by Total desc");

                while (tablesRentables.next()) {
                    System.out.println("n° de table" + "/" + "CA");
                    System.out.println(tablesRentables.getInt("table_idx") + "  / " + tablesRentables.getInt("total") + " €");
                }


                // ResultSet.close();
                statement.close();

//        if (choix==3){
//
//
//
//            // j'ai besoin de récupérer id table/prenom du serveur/ nom des plats consommés/prix/quantité /
//            // et calculer le Total avant d'enregistrer la facture dans la BDD avec INSERT INTO
//
//            //ensuite inserer ces données dans la BDD
//
//            System.out.println("entrez l'Id du serveur");
//            int idServeur=
//            System.out.println("entrez le plat");


//        }


// Je ferme la connexion.
                connection.close();

            }


        } catch (SQLException exception) {
            // Ma gestion du problème
            System.out.println("Erreur de connexion à la base de données");
            exception.printStackTrace();
        }


    }
}




