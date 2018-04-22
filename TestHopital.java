/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;
import Modele.Connexion;
import Modele.Hopital;
import Modele.*;
import Vue.*;
import java.io.IOException;

//import java.awt.* ;
import java.awt.event.* ;
import java.sql.SQLException;
//import javax.swing.* ;
//import java.sql.*;
//import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class contenant le main
 * @author Camille,Rim,Roman
 */
public class TestHopital implements ActionListener {
    

    //private static HopitalVue console; 
    private static AtomicBoolean push = new AtomicBoolean(false);
    private static ZFenetre zFen ;
    private static Hopital hop = new Hopital();
    private static NouveauPatient np ;
    private static NewEmploye ne ;
    private static AffichageRecherche aRech ; 
    
    /**
     * Constructeur de TestHopital 
     * Il ajoute les ActionListeners
     * @throws IOException 
     */
    public TestHopital() throws IOException{
       zFen = new ZFenetre();
       zFen.login() ;
       zFen.getMenuRetour().addActionListener(this) ;
       zFen.getMenuMAJ().addActionListener(this) ;
       zFen.getMenuRecherche().addActionListener(this) ;
       zFen.getMenuReport().addActionListener(this) ;
       zFen.getBoutonMAJ().addActionListener(this) ;
       zFen.getBoutonRecherche().addActionListener(this) ;
       zFen.getBoutonReport().addActionListener(this) ;
       zFen.getNouveauP().addActionListener(this) ;
       zFen.getNouvelE().addActionListener(this) ;
       zFen.getModifier().addActionListener(this) ;
       zFen.getSuppP().addActionListener(this) ;
       zFen.getSuppE().addActionListener(this) ;
       zFen.getRechMalade().addActionListener(this) ;
       zFen.getRechInf().addActionListener(this) ;
       zFen.getRechDoc().addActionListener(this) ;
       zFen.getRechServ().addActionListener(this) ;
       zFen.getRechChambre().addActionListener(this) ;
       zFen.getBoutonRetour().addActionListener(this) ;
       zFen.getModMalade().addActionListener(this) ;
       zFen.getModDocteur().addActionListener(this) ;
       zFen.getModInfirmier().addActionListener(this) ;
       zFen.getModService().addActionListener(this) ;
       zFen.getModChambre().addActionListener(this) ;
       zFen.getNouveauS().addActionListener(this) ;
       zFen.getSuppS().addActionListener(this) ;
    }
    
    /**
     * Main du programme 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException{
       
       new TestHopital() ;
       
       
    }
    
    /**
     * Methode permettant de lancer la bonne m�thode quand un bouton est appuy�
     * @param e 
     */
    public @Override void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==zFen.getMenuRetour() || e.getSource()==zFen.getBoutonRetour())
        {
            zFen.accueil();
        }
        if(e.getSource()==zFen.getMenuMAJ() || e.getSource()==zFen.getBoutonMAJ())
        {
            zFen.MAJ();
        }
        if(e.getSource()==zFen.getMenuRecherche() || e.getSource()==zFen.getBoutonRecherche())
        {
            
            zFen.optionsRecherche();
        }
        
        if(e.getSource()==zFen.getMenuReport() || e.getSource()==zFen.getBoutonReport())
        {
            try {
                Graphe graphe = new Graphe();
            } catch (SQLException ex) {
                Logger.getLogger(TestHopital.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TestHopital.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource()==zFen.getNouveauP())
        {
            np = new NouveauPatient(zFen);
        }
        if(e.getSource()==zFen.getNouvelE())
        {
            ne = new NewEmploye(zFen);
        }
        if(e.getSource()==zFen.getModifier())
        {
            zFen.modifier();
        }
        if(e.getSource()==zFen.getSuppP())
        {
            zFen.supprimer("malade");
        }
        if(e.getSource()==zFen.getSuppE())
        {
            zFen.supprimer("employe");
        }
        if(e.getSource()==zFen.getNouveauS())
        {
            zFen.nouveauSoin();
        }
        if(e.getSource()==zFen.getSuppS())
        {
            zFen.supSoin();
        }
        if(e.getSource()==zFen.getRechMalade())
        {
            String champs[] = new String[2] ;
            champs[0] = "Nom" ;
            champs[1] = "Prénom" ;
            aRech = new AffichageRecherche(champs, "Malade", zFen);
        }
        if(e.getSource()==zFen.getRechInf())
        {
          String champs[] = new String[2] ;
            champs[0] = "Nom" ;
            champs[1] = "Prénom" ;
            aRech = new AffichageRecherche(champs, "Infirmier", zFen);
        }
        if(e.getSource()==zFen.getRechDoc())
        {
            String champs[] = new String[2] ;
            champs[0] = "Nom" ;
            champs[1] = "Prénom" ;
            aRech = new AffichageRecherche(champs, "Docteur", zFen);
        }
        if(e.getSource()==zFen.getRechServ())
        {
            String champs[] = new String[1] ;
            champs[0] = "Nom du service" ;
            aRech = new AffichageRecherche(champs, "Service", zFen);
        }
        if(e.getSource()==zFen.getRechChambre())
        {
            String champs[] = new String[2] ;
            champs[0] = "Numéro de chambre" ;
            champs[1] = "Nom du service" ;
            aRech = new AffichageRecherche(champs, "Chambre", zFen);
        }
        if(e.getSource()==zFen.getModMalade())
        {
            
        }
        if(e.getSource()==zFen.getModInfirmier())
        {
            
        }
        if(e.getSource()==zFen.getModDocteur())
        {
            
        }
        if(e.getSource()==zFen.getModService())
        {
            
        }
        if(e.getSource()==zFen.getModChambre())
        {
            
        }
        
    }
}
        
