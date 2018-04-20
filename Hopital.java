package Modele;

/**
 *
 * @author Rim
 */
import Controleur.*;
import Vue.*;

import java.util.Scanner;
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
    Scanner sc = new Scanner(System.in);
    Connexion maconnexion;

    public Hopital() {

        try {

            try {
                maconnexion = new Connexion(NAME, LOGIN, PASSWORD);

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
    
    public void malade() {

        try {

            try {        
                System.out.println("Veuillez saisir un nom :");
                String nom = sc.nextLine();
                System.out.println("Veuillez saisir un prenom : ");
                String prenom = sc.nextLine();

                maconnexion.RechercheMalade(nom, prenom);
                
            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }
        
    }
    
    public void employe() {
            
        try {

            try {        
                System.out.println("Veuillez saisir un nom :");
                String nom = sc.nextLine();
                System.out.println("Veuillez saisir un prenom : ");
                String prenom = sc.nextLine();

                maconnexion.RechercheEmploye(nom, prenom);

            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }
        
    }
        
    public void service() {
            
        try {

            try {                        
                System.out.println("Veuillez saisir le nom du service : ");
                String nomService = sc.nextLine();
                maconnexion.RechercheService(nomService);            

            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }
        
    }
    
}
