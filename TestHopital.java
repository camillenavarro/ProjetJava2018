/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;
import Modele.Connexion;
import Modele.*;
import Vue.*;
import java.io.IOException;
/**
 *
 * @author Rim
 */
import java.sql.*;
import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;


public class TestHopital {
    

    //private static HopitalVue console;   
    public static void main(String[] args) throws IOException {
            
    Hopital hopital = new Hopital();
    hopital.service();
    //HopitalVue zFen = new HopitalVue();
 
    }
}
        
/*    private void remplirTables() {
        maconnexion.ajouterTable("chambre");
        maconnexion.ajouterTable("docteur");
        maconnexion.ajouterTable("employe");
        maconnexion.ajouterTable("hospitalisation");
        maconnexion.ajouterTable("infirmier");
        maconnexion.ajouterTable("malade");
        maconnexion.ajouterTable("service");
        maconnexion.ajouterTable("soigne");
        
   }
    
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
    
    public void afficherTables() {
        for (String table : maconnexion.tables) {
            listeDeTables.add(table);
        }
    }
    
    public void afficherRequetes() {
        for (String requete : maconnexion.requetes) {
            listeDeRequetes.add(requete);
        }
    }

        public ArrayList<String> afficherRes(String requeteSelectionnee) throws SQLException {
        ArrayList<String> liste = null;
        try {

            // recupérér les résultats de la requete selectionnee
            liste = maconnexion.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            //fenetreRes.setText("");
            //for (String liste1 : liste) {
            //    fenetreRes.append(liste1);
            //}
        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
        }
        return liste;
    }
        
   

}*/
