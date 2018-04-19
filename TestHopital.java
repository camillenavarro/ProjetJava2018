/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;
import Modele.Connexion;
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
    private static Hopital hop ;
    
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
    }
    
    public static void main(String[] args) throws IOException{
       
       // Hopital hopital = new Hopital() ;
       new TestHopital() ;
       
       
    }
    
    public @Override void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==zFen.getRetour())
        {
            zFen.accueil();
        }
        if(e.getSource()==zFen.getMAJmenu())
        {
            zFen.MAJ();
        }
        if(e.getSource()==zFen.getRecherMenu())
        {
            hop.rechercher();
        }
        if(e.getSource()==zFen.getMAJ())
        {
            //Nouveau Patient
            zFen.MAJ(); 
        }
        if(e.getSource()==zFen.getRecherche())
        {
            //Recherche
            hop.rechercher();
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
    }
}
        