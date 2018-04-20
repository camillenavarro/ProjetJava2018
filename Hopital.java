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
    
    public void rechercher(String nom, String prenom) {
        
       

        try {

            try { 
                
                
                
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
    
    private void remplirTables() {
        maconnexion.ajouterTable("chambre");
        maconnexion.ajouterTable("docteur");
        maconnexion.ajouterTable("employe");
        maconnexion.ajouterTable("hospitalisation");
        maconnexion.ajouterTable("infirmier");
        maconnexion.ajouterTable("malade");
        maconnexion.ajouterTable("service");
        maconnexion.ajouterTable("soigne");
    }

    /**
     * Méthode privée qui initialise la liste des requetes de selection
     */
    private void remplirRequetes() {
       //R1
        maconnexion.ajouterRequete("SELECT nom, prenom FROM `malade` WHERE mutuelle = \"MAAF\"");
        //R2
        maconnexion.ajouterRequete("SELECT e.nom, e.prenom FROM infirmier i, employe e WHERE i.numero = e.numero AND i.rotation = \"NUIT\"");
        //R3
        maconnexion.ajouterRequete("SELECT s.nom, s.batiment, d.specialite, e.nom, e.prenom FROM service s, docteur d, employe e WHERE s.directeur = d.numero AND d.numero = e.numero");
        //R4
        maconnexion.ajouterRequete("SELECT c.no_chambre, h.lit, s.nom, m.nom, m.prenom, m.mutuelle FROM chambre c, malade m, hospitalisation h, service s WHERE c.no_chambre = h.no_chambre AND c.code_service = s.code AND m.numero = h.no_malade AND m.mutuelle LIKE 'MN%' AND s.batiment = 'B'");
        //R5
        maconnexion.ajouterRequete("SELECT AVG(i.salaire), i.code_service FROM infirmier i GROUP BY i.code_service");
        //R6
        maconnexion.ajouterRequete("SELECT c.code_service, AVG(c.nb_lits) FROM chambre c, service s WHERE s.code = c.code_service AND s.batiment = 'A' GROUP BY c.code_service");
        //R7
        maconnexion.ajouterRequete("SELECT m.nom, m.prenom, COUNT(s.no_docteur), COUNT(DISTINCT d.specialite) FROM soigne s, malade m, docteur d WHERE s.no_malade = m.numero AND s.no_docteur = d.numero GROUP BY s.no_malade HAVING COUNT(s.no_docteur) > 3");
        //R8
        maconnexion.ajouterRequete("SELECT s.nom, COUNT(DISTINCT i.numero) / COUNT(DISTINCT h.no_malade) FROM service s, infirmier i, hospitalisation h WHERE s.code = i.code_service AND h.code_service = s.code GROUP BY s.code");
        //R9
        maconnexion.ajouterRequete("SELECT e.nom, e.prenom FROM employe e, soigne s WHERE e.numero = s.no_docteur GROUP BY s.no_docteur HAVING COUNT(s.no_malade) > 1");
        //R10
        maconnexion.ajouterRequete("SELECT e.nom, e.prenom FROM employe e, soigne s WHERE e.numero = s.no_docteur GROUP BY s.no_docteur HAVING COUNT(s.no_malade) < 1");  
    }

    /**
     * Méthode privée qui initialise la liste des requetes de MAJ
     */
    private void remplirRequetesMaj() {
        // Requêtes d'insertion
        maconnexion.ajouterRequeteMaj("INSERT INTO malade (nom,prenom,loc) VALUES (50,'ECE','Paris');");

        // Requêtes de modification
        maconnexion.ajouterRequeteMaj("UPDATE Dept SET loc='Eiffel' WHERE loc='Paris';");

        // Requêtes de suppression
        maconnexion.ajouterRequeteMaj("DELETE FROM Dept WHERE loc='Eiffel';");

    }

    /**
     *
     * Afficher les tables
     */
    public void afficherTables() {
        for (String table : maconnexion.tables) {
            System.out.println(table);
        }
    }

    /**
     *
     * Afficher les lignes de la table sélectionnée
     */
    public void afficherLignes(String nomTable) {
        try {
            ArrayList<String> liste;

            // effacer les résultats
            //fenetreLignes.removeAll();

            // recupérér les résultats de la table selectionnee
            liste = maconnexion.remplirChampsTable(nomTable);

            // afficher les champs de la table selectionnee 
            //fenetreLignes.setText("");
            for (String liste1 : liste) {
                System.out.println(liste1);
            }

            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select * from " + nomTable + ";";
            liste = maconnexion.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            for (String liste1 : liste) {
                System.out.println(liste1);
            }

        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            //fenetreRes.setText("");
            //fenetreRes.append("Echec table SQL");
            e.printStackTrace();

        }
    }

    /**
     *
     * Afficher les requetes de selection et de MAJ dans la fenetre
     */
    public void afficherRequetes() {
        for (String requete : maconnexion.requetes) {
            System.out.println(requete);
        }
    }

    /**
     *
     * Afficher et retourner les résultats de la requete sélectionnée
     *
     * @param requeteSelectionnee
     */
    public ArrayList<String> afficherRes(String requeteSelectionnee) throws SQLException {
        ArrayList<String> liste = null;
        try {

            // effacer les résultats
            //fenetreRes.removeAll();

            // recupérér les résultats de la requete selectionnee
            liste = maconnexion.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            //fenetreRes.setText("");
            for (String liste1 : liste) {
                System.out.println(liste1);
            }
        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            //fenetreRes.setText("");
            //fenetreRes.append("Echec requete SQL");
        }
        return liste;
    }

}
