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
    ArrayList<String> liste = new ArrayList<>();

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

    public void docteur() {

        try {

            try {
                System.out.println("Veuillez saisir un nom :");
                String nom = sc.nextLine();
                System.out.println("Veuillez saisir un prenom : ");
                String prenom = sc.nextLine();

                liste = maconnexion.RechercheDocteur(nom, prenom);
                System.out.println(liste);

            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }

    }

    public void infirmier() {

        try {

            try {
                System.out.println("Veuillez saisir un nom :");
                String nom = sc.nextLine();
                System.out.println("Veuillez saisir un prenom : ");
                String prenom = sc.nextLine();
                
                liste = maconnexion.RechercheInfirmier(nom, prenom);
                System.out.println(liste);

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
                
                liste = maconnexion.RechercheService(nomService);
                System.out.println(liste);
                
            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }

    }
    
    public void chambre() {

        try {

            try {
                System.out.println("Veuillez saisir le numero de la chambre : ");
                String numChambre = sc.nextLine();
                System.out.println("Veuillez saisir le service de la chambre : ");
                String nomService = sc.nextLine();
                
                liste = maconnexion.RechercheChambre(numChambre, nomService);
                System.out.println(liste);
                
            } catch (SQLException ex) {
                System.out.println("SQL problem.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class problem.");
        }

    }

}
