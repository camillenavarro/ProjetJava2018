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
    
    public void rechercher() {
               
        Scanner sc = new Scanner(System.in);

        try {

            try {        
                System.out.println("Veuillez saisir un nom :");
                String nom = sc.nextLine();
                System.out.println("Veuillez saisir un prenom : ");
                String prenom = sc.nextLine();

                maconnexion.RecherchePersonne(nom, prenom);

            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }
        
    }
    
    public void ajouter(String table) {
        
    }
    
    public void supprimer(String table) {
        
    }

}
