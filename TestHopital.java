/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;
import Modele.Connexion;
import Modele.Hopital;
import Modele.*;
import Vue.ZFenetre;
import java.io.IOException;
/**
 *
 * @author Rim
 */
import java.awt.* ;
import java.awt.event.* ;
import javax.swing.* ;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestHopital implements ActionListener {
    

    //private static HopitalVue console; 
    private static AtomicBoolean push = new AtomicBoolean(false);
    private static ZFenetre zFen ;
    private static Hopital hop = new Hopital();
    
    
    public TestHopital() throws IOException{
       zFen = new ZFenetre();
       zFen.accueil() ;
       zFen.getRetour().addActionListener(this) ;
       zFen.getMAJmenu().addActionListener(this) ;
       zFen.getRecherMenu().addActionListener(this) ;
       zFen.getMAJ().addActionListener(this) ;
       zFen.getRecherche().addActionListener(this) ;
       zFen.getNouveauP().addActionListener(this) ;
       zFen.getNouvelE().addActionListener(this) ;
       zFen.getModifier().addActionListener(this) ;
       zFen.getSuppP().addActionListener(this) ;
       zFen.getSuppE().addActionListener(this) ;
       zFen.getMalades().addActionListener(this) ;
       zFen.getInfirmiers().addActionListener(this) ;
       zFen.getDocteurs().addActionListener(this) ;
       zFen.getServices().addActionListener(this) ;
       zFen.getChambres().addActionListener(this) ;
    }
    
    public static void main(String[] args) throws IOException{
       
       new TestHopital() ;
       
       
    }
    
    public @Override void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==zFen.getRetour())
        {
            zFen.accueil();
        }
        if(e.getSource()==zFen.getMAJmenu() || e.getSource()==zFen.getMAJ())
        {
            zFen.MAJ();
        }
        if(e.getSource()==zFen.getRecherMenu() || e.getSource()==zFen.getRecherche())
        {
            
            zFen.optionsRecherche();
        }
        
        if(e.getSource()==zFen.getNouveauP())
        {
            hop.ajouter("Malade");
        }
        if(e.getSource()==zFen.getNouvelE())
        {
            hop.ajouter("Employé");
        }
        if(e.getSource()==zFen.getModifier())
        {
            hop.MAJ();
        }
        if(e.getSource()==zFen.getSuppP())
        {
            hop.supprimer("Malade");
        }
        if(e.getSource()==zFen.getSuppE())
        {
            hop.ajouter("Employé");
        }
        if(e.getSource()==zFen.getMalades())
        {
            String champs[] = new String[2] ;
            champs[0] = "Nom" ;
            champs[1] = "Prénom" ;
            zFen.saisieRecherche(champs);
        }
        if(e.getSource()==zFen.getInfirmiers())
        {
          String champs[] = new String[2] ;
            champs[0] = "Nom" ;
            champs[1] = "Prénom" ;
            zFen.saisieRecherche(champs);
        }
        if(e.getSource()==zFen.getDocteurs())
        {
          String champs[] = new String[2] ;
            champs[0] = "Nom" ;
            champs[1] = "Prénom" ;
            zFen.saisieRecherche(champs);
        }
        if(e.getSource()==zFen.getServices())
        {
            String champs[] = new String[1] ;
            champs[0] = "Code Service" ;
            zFen.saisieRecherche(champs);
        }
        if(e.getSource()==zFen.getChambres())
        {
            String champs[] = new String[1] ;
            champs[0] = "Numéro de chambre" ;
            zFen.saisieRecherche(champs);
        }
    }
}
        