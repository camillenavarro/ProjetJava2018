/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame; 
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.util.*;
import java.sql.*;
import Modele.Connexion;

/**
 *Creation du cammenbert des spÃ©cialisation des Docteurs

 * @author roman
 */
public class Graphe {
     static final String NAME = "hopital";
    static final String LOGIN = "root";
    static final String PASSWORD = "";
    Connexion maconnexion= new Connexion(NAME, LOGIN, PASSWORD);
   /**
    * Constructeur de Graphe
    * Il fait un camembert Ã© 6 parts qui évolue avec la base de données
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
     public Graphe() throws SQLException, ClassNotFoundException{
          DefaultPieDataset dataset = new DefaultPieDataset(); 
    dataset.setValue("Cardiologue", maconnexion.NbdeCardio()); 
    dataset.setValue("Anesthesiste", maconnexion.NbdeAn());
    dataset.setValue("Pneumologue", maconnexion.NbdePneu());
    dataset.setValue("Traumatologue", maconnexion.NbdeTrauma());
    dataset.setValue("Radiologue", maconnexion.NbdeRad());
    dataset.setValue("Orthopediste", maconnexion.NbdeOrth());
    JFreeChart chart = ChartFactory.createPieChart( "Spécialisation des Docteurs", dataset, true,true, false);
    ChartFrame frame = new ChartFrame("First", chart);
frame.pack();
frame.setVisible(true);
     }
      
       ArrayList<String> liste = new ArrayList<>();
       
       
  
}
