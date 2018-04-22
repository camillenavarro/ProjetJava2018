package Modele;

/**
 *
 * @author Rim
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

public class Hopital {

    static final String NAME = "hopital";
    static final String LOGIN = "root";
    static final String PASSWORD = "";
    Connexion maconnexion;
    ArrayList<String> liste = new ArrayList<>();

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

    public void AjoutMalade(String ArrayList) throws SQLException {
        
        //ajout malade
        int num = maconnexion.IDgenerator("malade");
        String insert = "INSERT INTO malade (numero,nom,prenom,adresse,tel,mutuelle) VALUES (" + num + "," + liste.get(0) + "," + liste.get(1) + "," + liste.get(2) + "," + liste.get(3) + "," + liste.get(4) + ")";
        //System.out.println(num);
        maconnexion.executeUpdate(insert);         
    }

    
    public void MAJupdate() throws SQLException {
        String update = "UPDATE Dept SET" + "WHERE loc='Paris'";
        maconnexion.executeUpdate(update);
    }

    public void MAJdelete() throws SQLException {
        String delete = "DELETE FROM Dept WHERE loc='Eiffel'";
        maconnexion.executeUpdate(delete);
    }

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
    
    public void nouveauPatient(ArrayList<String> donnees)
    {
        

            try {
               
                int num = maconnexion.IDgenerator("malade");
                String insert = "INSERT INTO malade (numero, nom, prenom, tel, adresse, mutuelle) VALUES ('" + num + "','"+ donnees.get(0) + "','" + donnees.get(1) + "','" + donnees.get(2) + "','" + donnees.get(3) + "','" + donnees.get(4) + "')" ;
                
                
                
    //            String insert2 = "INSERT INTO hospitalisation  VALUES ('" + donnees.get(0) + "','" + donnees.get(6) + "','" + donnees.get(7) + "','"  ;
                
                maconnexion.executeUpdate(insert);
            //    maconnexion.executeUpdate(insert2);
                
             } catch (SQLException ex) {
                 
                 System.out.println("SQL problem.");
             }
 
         
    }
    
    public void nouvelEmploye(ArrayList<String> donnees)
    {
        

            try {
               
                int num = maconnexion.IDgenerator("employe");
                String insert = "INSERT INTO employe (numero, nom, prenom, tel, adresse) VALUES ('" + num + "','"+ donnees.get(0) + "','" + donnees.get(1) + "','" + donnees.get(2) + "','" + donnees.get(3) + "')" ;
                String insert2 ;
                
                if((donnees.get(4)).equals("doc"))
                
                    insert2 = "INSERT INTO docteur (numero, specialite) VALUES ('" + num + "','" + donnees.get(5) + "')" ;
                
                else
                
                    insert2 = "INSERT INTO infirmier (numero, code_service, rotation, salaire) VALUES ('" + num + "','" + donnees.get(5) + "','" + donnees.get(6) + "','" + donnees.get(7) +"')" ;
                
                
                maconnexion.executeUpdate(insert);
                maconnexion.executeUpdate(insert2);
                
             } catch (SQLException ex) {
                 
                 System.out.println("SQL problem.");
             }
 
         
    }
    
    public void suppression(String nom, String prenom, String table) 
    {
       

            try {
                String query = "DELETE FROM " + table + " WHERE nom = '" + nom + "' AND prenom = '" + prenom + "'";
                maconnexion.executeUpdate(query);
                
            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

    }

}
