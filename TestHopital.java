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
/**
 *
 * @author Rim
 */
//import java.awt.* ;
import java.awt.event.* ;
//import javax.swing.* ;
//import java.sql.*;
//import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestHopital implements ActionListener {
    

    //private static HopitalVue console; 
    private static AtomicBoolean push = new AtomicBoolean(false);
    private static ZFenetre zFen ;
    private static Hopital hop = new Hopital();
    private static NouveauPatient np ;
    private static NPListener npl ;
    private static NewEmploye ne ;
    
    
    public TestHopital() throws IOException{
       zFen = new ZFenetre();
       zFen.accueil() ;
       zFen.getMenuRetour().addActionListener(this) ;
       zFen.getMenuMAJ().addActionListener(this) ;
       zFen.getMenuRecherche().addActionListener(this) ;
       zFen.getBoutonMAJ().addActionListener(this) ;
       zFen.getBoutonRecherche().addActionListener(this) ;
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
    
    public static void main(String[] args) throws IOException{
       
       new TestHopital() ;
       
       
    }
    
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
        
        if(e.getSource()==zFen.getNouveauP())
        {
            np = new NouveauPatient(zFen);
          //  npl = new NPListener() ;
            
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
            
        }
        if(e.getSource()==zFen.getSuppS())
        {
            
        }
        if(e.getSource()==zFen.getRechMalade())
        {
            String champs[] = new String[2] ;
            champs[0] = "Nom" ;
            champs[1] = "Prénom" ;
            zFen.saisieRecherche(champs, "Malade");
        }
        if(e.getSource()==zFen.getRechInf())
        {
          String champs[] = new String[2] ;
            champs[0] = "Nom" ;
            champs[1] = "Prénom" ;
            zFen.saisieRecherche(champs, "Infirmier");
        }
        if(e.getSource()==zFen.getRechDoc())
        {
            String champs[] = new String[2] ;
            champs[0] = "Nom" ;
            champs[1] = "Prénom" ;
            zFen.saisieRecherche(champs, "Docteur");
        }
        if(e.getSource()==zFen.getRechServ())
        {
            String champs[] = new String[1] ;
            champs[0] = "Nom du service" ;
            zFen.saisieRecherche(champs, "Service");
        }
        if(e.getSource()==zFen.getRechChambre())
        {
            String champs[] = new String[2] ;
            champs[0] = "Numéro de chambre" ;
            champs[1] = "Nom du service" ;
            zFen.saisieRecherche(champs, "Chambre");
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
        