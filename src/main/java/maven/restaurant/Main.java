package maven.restaurant;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws SQLException {

        //ici on recupere les infos pour nous connecter à notre base de données

        String url = "jdbc:postgresql://localhost:5432/maven-restaurant";
        String user = "postgres";
        String password = "postgres";

        //on se connecte en creant l'appel a la BDD


        Connection connection = DriverManager.getConnection(url, user, password);


        //Import de la classe Scanner

        Scanner sc=new Scanner(System.in);

        //Demander au restaurateur ce qu'il souhaite faire

        System.out.println("Bonjour");
        System.out.println("Pour afficher les plats les plus vendus tapez 1");
        System.out.println("Pour afficher les tables les plus rentables tapez 2");
        System.out.println("Pour sauvegarder une nouvelle facture tapez 3");
        int choix=sc.nextInt();
        sc.nextLine();

        if (choix==1) {
            //afficher les plats les plus vendus


            //Récuperation dans la BDD des plats et prix à partir de la requete deja testée en sql

            Statement statement = connection.createStatement();

            ResultSet listePlats = statement.executeQuery("select plat.id,\"Nom_du_plat\",SUM(prix_unitaire*quantite) as total "
                    +"from plat  join table_intermediaire on plat.id = table_intermediaire.plat_idx "
                    +"join facture on facture.id=table_intermediaire.facture_idx "
                    +"group by plat.id order by total desc");


                //affichage du resultat en parcourant nos colonnes

            while (listePlats.next()) {

                        listePlats.getInt(3);
                        listePlats.getString("Nom_du_plat");
                        listePlats.getInt("Total");


                System.out.println(listePlats);
            }

        }

if (choix==2) {

            Statement statement = connection.createStatement();

            ResultSet tablesRentables = statement.executeQuery("SELECT * from serveurs");

            while (tablesRentables.next()) {


                System.out.println(tablesRentables.getString(""));




//lister les meilleures tables par chiffre d'affaire


            resultSet.close();
            statement.close();

        }


// Je ferme la connexion.
            connection.close();




        }
    }

