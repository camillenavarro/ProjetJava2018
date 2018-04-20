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
    
    public void MAJ() {
        
        //maconnexion.executeUpdate(requeteMAJ);
    }
    
    public void malade(String nom, String prenom) {

        try {

            try {        
              
                liste = maconnexion.RechercheMalade(nom, prenom);
                //liste.forEach((_item) -> {
                System.out.println(liste);
                //});
                
            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }
        
    }
    
    public void docteur(String nom, String prenom) {

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

    }

    public void infirmier(String nom, String prenom) {

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

    }
        
    public void service(String nomService) {
            
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
        
    }

}
