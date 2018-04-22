package Modele;

/**
 *
 * @author Camille,Rim,Roman
 */
import Controleur.*;
import Vue.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe permettant l'utilisation et la connection avec la base de données
 * @author Camille,Rim,Roman
 */
public class Hopital {

    static final String NAME = "hopital";
    static String LOGIN = "root";
    static String PASSWORD = "";
    public Connexion maconnexion;
    ArrayList<String> liste = new ArrayList<>();

    /**
     * Constructeurs par defaut d'Hopital 
     * Elle fait la connection avec la base de données
     */
    public Hopital() {

        try {

            try {
                //Connection connection = null;
                //Statement statement = null;
                //ResultSet resultSet = null;

                maconnexion = new Connexion(NAME, LOGIN, PASSWORD);
                //maconnexion.remplirChampsTable("chambre");

            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }

        //finally {
        //try {
        //    resultSet.close();
        //    statement.close();
        //    connection.close();
        //} catch (SQLException ex) {
        //}
    }
    /**
     * Constructeur suchargé d'Hopital 
     * Elle permet la connectiona avec une base de données choisie
     * @param a
     * @param b 
     */
    public Hopital(String a, String b) {
        LOGIN = a;
        PASSWORD = b;
        try {

            try {
                //Connection connection = null;
                //Statement statement = null;
                //ResultSet resultSet = null;

                maconnexion = new Connexion(NAME, LOGIN, PASSWORD);
                //maconnexion.remplirChampsTable("chambre");

            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }

        //finally {
        //try {
        //    resultSet.close();
        //    statement.close();
        //    connection.close();
        //} catch (SQLException ex) {
        //}
    }


    
    /**
     * Méthode permettant de recherche une personne malade 
     * @param nom
     * @param prenom
     * @return Arraylist d'informations sur les personnes malades
     */
    public ArrayList malade(String nom, String prenom) {

        try {
            try {

                liste = maconnexion.RechercheMalade(nom, prenom);
                System.out.println(liste);

            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }
        return liste;

    }

    /**
     * Méthode permettant de rechercher un docteur
     * @param nom
     * @param prenom
     * @return Arraylist d'informations sur les docteurs
     */
    public ArrayList docteur(String nom, String prenom) {

        try {

            try {

                liste = maconnexion.RechercheDocteur(nom, prenom);
                System.out.println(liste);

            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }
        return liste;
    }
    
    /**
     * Méthode permettant de rechercher un infirmier
     * @param nom
     * @param prenom
     * @return Arraylist d'information sur les infirmiers
     */
    public ArrayList infirmier(String nom, String prenom) {

        try {

            try {

                liste = maconnexion.RechercheInfirmier(nom, prenom);
                System.out.println(liste);

            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }
        return liste;
    }

    /**
     * Méthode permettant de recherche un service
     * @param nomService
     * @return Arraylist d'informations sur les services
     */
    public ArrayList service(String nomService) {

        try {

            try {

                liste = maconnexion.RechercheService(nomService);
                System.out.println(liste);

            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }
        return liste;
    }

    /**
     * Methode permettant de rechercher une chambre
     * @param numChambre
     * @param nomService
     * @return Arraylist d'information sur les services 
     */
    public ArrayList chambre(String numChambre, String nomService) {

        try {

            try {
                liste = maconnexion.RechercheChambre(numChambre, nomService);
                System.out.println(liste);

            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }

        return liste;
    }

    /**
     * Méthode permettant d'ajouter un patient à la base de données
     * @param donnees 
     */
    public void nouveauPatient(ArrayList<String> donnees) {
        try {
            int num = maconnexion.IDgenerator("malade");
            String insert = "INSERT INTO malade (numero, nom, prenom, tel, adresse, mutuelle) VALUES ('" + num + "','" + donnees.get(0) + "','" + donnees.get(1) + "','" + donnees.get(2) + "','" + donnees.get(3) + "','" + donnees.get(4) + "')";
            //            String insert2 = "INSERT INTO hospitalisation  VALUES ('" + donnees.get(0) + "','" + donnees.get(6) + "','" + donnees.get(7) + "','"  ;

            maconnexion.executeUpdate(insert);
            //    maconnexion.executeUpdate(insert2);

        } catch (SQLException ex) {

            System.out.println("SQL problem.");
        }

    }
    /**
     * Méthode permettant d'ajouter un employé (docteur,infirmier) à base de données
     * @param donnees 
     */
    public void nouvelEmploye(ArrayList<String> donnees) {

        try {

            int num = maconnexion.IDgenerator("employe");
            String insert = "INSERT INTO employe (numero, nom, prenom, tel, adresse) VALUES ('" + num + "','" + donnees.get(0) + "','" + donnees.get(1) + "','" + donnees.get(2) + "','" + donnees.get(3) + "')";
            String insert2;

            if ((donnees.get(4)).equals("doc")) {
                insert2 = "INSERT INTO docteur (numero, specialite) VALUES ('" + num + "','" + donnees.get(5) + "')";
            } else {
                insert2 = "INSERT INTO infirmier (numero, code_service, rotation, salaire) VALUES ('" + num + "','" + donnees.get(5) + "','" + donnees.get(6) + "','" + donnees.get(7) + "')";
            }

            maconnexion.executeUpdate(insert);
            maconnexion.executeUpdate(insert2);

        } catch (SQLException ex) {

            System.out.println("SQL problem.");
        }

    }
    /**
     * Méthode permttant de supprimer un docteur, un infirmier ou un malade
     * @param nom
     * @param prenom
     * @param table
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void suppression(String nom, String prenom, String table) throws SQLException, ClassNotFoundException {
        try {
            String query2, query3;
            String query = "DELETE FROM " + table + " WHERE nom = '" + nom + "' AND prenom = '" + prenom + "'";

            String numero = maconnexion.getNumero(nom, prenom, table);
            System.out.println("Le numero est " + numero);
            maconnexion.executeUpdate(query);

            if (table.equals("employe")) {
                query2 = "DELETE FROM docteur WHERE numero = '" + numero + "'";
                query3 = "DELETE FROM infirmier WHERE numero = '" + numero + "'";
                maconnexion.executeUpdate(query2);
                maconnexion.executeUpdate(query3);
            }

        } catch (SQLException ex) {
            System.out.println("SQL problem.");
        }

    }

    /**
     * Méthode permmettant de modifier les informations d'un malade
     * @param listeMAJ
     * @throws SQLException 
     * @return boolean true si le malade existe, false sinon
     */
    public Boolean updateMalade(ArrayList<String> listeMAJ) throws SQLException {

        String nom = listeMAJ.get(0);
        String prenom = listeMAJ.get(1);
        
        ArrayList<String> verif = new ArrayList() ;
        try {
            verif = maconnexion.RechercheMalade(nom, prenom);
        } catch (ClassNotFoundException ex) {
            System.out.println("SQL problem");
        }
        
        if(verif.size() > 1)
        {
            String tel = listeMAJ.get(2);
            String adresse = listeMAJ.get(3);
            String mutuelle = listeMAJ.get(4);
            String update = "", set = "";
            
            if (!adresse.equals("")) {
                if(set.equals(""))
                    set += "adresse = '" + adresse + "' " ;
                else
                    set += ", adresse = '" + adresse + "' " ;
            }

            if (!tel.equals("")) {
                if(set.equals(""))
                    set += "tel = '" + tel + "' " ;
                else
                    set += ", tel = '" + tel + "' " ;
            }

            if (!mutuelle.equals("")) {
                if(set.equals(""))
                    set += "mutuelle = '" + mutuelle + "' " ;
                else
                    set += ", mutuelle = '" + mutuelle + "' " ;
            }
            update = "UPDATE malade SET " + set + "WHERE nom ='" + nom + "' AND prenom='" + prenom + "'";
                
            maconnexion.executeUpdate(update);
            return true ;
        }
        else
            return false ;       
    }
    /**
     * Méthode permettant de modifier les informations d'un employé
     * @param listeMAJ
     * @throws SQLException 
     * @return boolean true si l'employé existe, false sinon
     */
    public Boolean updateEmploye(ArrayList<String> listeMAJ) throws SQLException {

        String nom = listeMAJ.get(0);
        String prenom = listeMAJ.get(1);
        String profession = listeMAJ.get(4) ;
        
        ArrayList<String> verif = new ArrayList() ;
        try {
            if(profession.equals("doc"))
                verif = maconnexion.RechercheDocteur(nom, prenom);
            else
                verif = maconnexion.RechercheInfirmier(nom, prenom);
        } catch (ClassNotFoundException ex) {
            System.out.println("SQL problem");
        }
        
        if(verif.size() > 1)
        {
            String adresse = listeMAJ.get(2);
            String tel = listeMAJ.get(3);
            String update = "", set = "";

            if (!adresse.equals("")) {
                if(set.equals(""))
                    set += "adresse = '" + adresse + "' " ;
                else
                    set += ", adresse = '" + adresse + "' " ;
            }

            if (!tel.equals("")) {
                if(set.equals(""))
                    set += "tel = '" + tel + "' " ;
                else
                    set += ", tel = '" + tel + "' " ;
            }
            update = "UPDATE employe SET " + set + "WHERE nom ='" + nom + "' AND prenom='" + prenom + "'";
            maconnexion.executeUpdate(update);
            
            if(profession.equals("inf"))
            {
                try {
                    String num = maconnexion.getNumero(nom, prenom, "Employe") ; 
                    updateInfirmier(listeMAJ, num) ;
                } catch (ClassNotFoundException ex) {
                    System.out.println("SQL problem");
                }
            }
            
            return true ;
        }
        return false ;
    }
     /**
      * Méthode permettant de modifier les informations d'un infirmier 
      * @param listeMAJ, numero
      * @throws SQLException 
      */
    public void updateInfirmier(ArrayList<String> listeMAJ, String numero) throws SQLException {

        String rotation = listeMAJ.get(5);
        String salaire = listeMAJ.get(6);
        String update = "", set = "rotation = '" + rotation + "' ";
        
        
        if (!salaire.equals("")) {
            set += ", salaire = '" + salaire + "' " ;
        }
        
        update = "UPDATE infirmier SET " + set + "WHERE numero ='" + numero + "'";
        
        maconnexion.executeUpdate(update);
        
    }
}
