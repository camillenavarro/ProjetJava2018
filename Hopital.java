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

                Connexion maconnexion = new Connexion(NAME, LOGIN, PASSWORD);
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
    
    public void ajouter(String table) {
        
    }
    
    public void supprimer(String table) {
        
    }

    public void MAJ() {
        
        //maconnexion.executeUpdate(requeteMAJ);
        
    }
    
    public void Rechercher() {
        
    }

}
