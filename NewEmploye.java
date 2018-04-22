/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.*;
import Modele.*;
import java.io.IOException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

/**
 * Classe permettant de générer un nouvel Employé
 * @author roman
 */
public class NewEmploye {
    
    
      private JTextField nom = new JTextField("");
      private JTextField prenom = new JTextField("");
      private JTextField adresse = new JTextField("");
     
      
      private JFormattedTextField salaire = new JFormattedTextField(NumberFormat.getIntegerInstance());
      private JFormattedTextField tel = new JFormattedTextField(NumberFormat.getIntegerInstance());
      
      private JRadioButton doc = new JRadioButton("Docteur");
      private JRadioButton inf = new JRadioButton("Infirmier");
      
      private ButtonGroup bg = new ButtonGroup();
      
      private JComboBox spe = new JComboBox();
      private JLabel Spe = new JLabel("SpÃ©cialisation");
      
      private JComboBox service = new JComboBox();
      private JLabel Service = new JLabel("Service");
      
      private JComboBox rot = new JComboBox();
      private JLabel Rot = new JLabel("Rotation");
      
      private JPanel container = new JPanel();
      
      private JButton save = new JButton ("Enregistrer");
      
      private ArrayList<String> donnees = new ArrayList();
     
      
      private JButton retour = new JButton("Retour à  l'accueil");
      private Hopital hop = new Hopital() ;
      
      /**
       * Constructeur de la classe 
       * Elle crée le formulaire pour ajouter un nouvel employé 
       * @param zFen 
       */
      public NewEmploye(ZFenetre zFen)
      {
          
            
  //  container.setBackground(Color.white);
   Font police = new Font("Arial", Font.BOLD, 14);
    container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
   container.add(new JLabel ("Nouvel EmployÃ©"));  
   
    container.add(new JLabel ("Nom : ")); 
    nom.setFont(police);
    nom.setPreferredSize(new Dimension(150, 30));
    nom.setForeground(Color.BLACK);
    container.add(nom);
    
    container.add(new JLabel ("Prenom : "));
    prenom.setFont(police);
    prenom.setPreferredSize(new Dimension(150, 30));
    prenom.setForeground(Color.BLACK);
    container.add(prenom);
    
    container.add(new JLabel ("Tel : "));  
    tel.setFont(police);
    tel.setPreferredSize(new Dimension(150, 30));
    tel.setForeground(Color.BLACK);
    container.add(tel);
    
    container.add(new JLabel ("Adresse : "));  
    adresse.setFont(police);
    adresse.setPreferredSize(new Dimension(150, 30));
    adresse.setForeground(Color.BLACK);
    container.add(adresse);
    
        bg.add(doc);
        bg.add(inf);
      //  doc.setSelected(true);
    doc.addActionListener(new StateListener());
    inf.addActionListener(new StateListener());
     container.add(doc);
     container.add(inf);
     
    container.add(Spe);
    container.add(spe);
    spe.setPreferredSize(new Dimension(100, 20));
    spe.addItem("Anesthesiste");
    spe.addItem("Cardiologue");
    spe.addItem("Generaliste");
    spe.addItem("Orthopediste");
    
    container.add(new JLabel ("Code Service : "));
    service.setFont(police);
    service.setPreferredSize(new Dimension(150, 30));
    service.setForeground(Color.BLACK);
    container.add(service);
    
    container.add(Rot);
    container.add(rot);
    
    rot.setPreferredSize(new Dimension(100, 20));
    rot.addItem("JOUR");
    rot.addItem("NUIT");
    
    container.add(Service);
    container.add(service);
    
    service.setPreferredSize(new Dimension(100, 20));
    service.addItem("REA");
    service.addItem("CAR");
    service.addItem("CHG");
    
    container.add(new JLabel ("Salaire : "));
    salaire.setFont(police);
    salaire.setPreferredSize(new Dimension(150, 30));
    salaire.setForeground(Color.BLACK);
    container.add(salaire);
    
    
    
    container.add(save);
    JPanel truc = new JPanel() ;
    truc.add(container);
    truc.add(save);
    truc.add(retour);
    zFen.setContentPane(truc);
    zFen.setVisible(true);
    
    save.addActionListener(new ActionListener() {
        /**
         * Cette méthode vérifie si le formulaire est vide après avoir appuyé sur le bouton "Enregistrer"
         * S'il n'est pas vide, on appelle la fonction nouvel employé d'Hôpital
         * Sinon on affiche un message d'erreur 
         * @param e 
         */
        public @Override void actionPerformed(ActionEvent e) {
            donnees.clear();
            if(!(nom.getText()).equals(""))
               donnees.add(nom.getText());
            if(!(prenom.getText()).equals(""))
                donnees.add(prenom.getText());
            if(!(tel.getText()).equals(""))
                donnees.add(tel.getText());
            if(!(adresse.getText()).equals(""))
                donnees.add(adresse.getText());
            
    
            if(doc.isSelected() == true)
            {
                donnees.add("doc");
                donnees.add(spe.getSelectedItem().toString());
                if (donnees.size() == 6)
                {
                    System.out.println("Formulaire valide");
                    hop.nouvelEmploye(donnees);
                    zFen.accueil();
                }
                else
                {
                    //JTextArea erreur = new JTextArea("Formulaire non valide !") ;
                    System.out.println("Formulaire non valide");
                }
                
            }
            else {
                if(inf.isSelected() == true)
                {
                    donnees.add("inf");
                    donnees.add(service.getSelectedItem().toString());
                    donnees.add(rot.getSelectedItem().toString());
                    if(!(salaire.getText()).equals(""))
                        donnees.add(salaire.getText());
                    if (donnees.size() == 8)
                    {
                        System.out.println("Formulaire valide");
                        hop.nouvelEmploye(donnees);
                        zFen.accueil();
                    }
                    else
                    {
                        //JTextArea erreur = new JTextArea("Formulaire non valide !") ;
                        System.out.println("Formulaire non valide");
                    }
                }
                else
                {
                    //JTextArea erreur = new JTextArea("Formulaire non valide !") ;
                    System.out.println("Formulaire non valide");
                }
            }
            
        }
    }
    ) ; 
    
    retour.addActionListener(new ActionListener() {
        /**
         * Lance l'accueil après avoir appuyé sur le bouton "Retour à  l'accueil"
         * @param e 
         */
        public @Override void actionPerformed(ActionEvent e) {
            zFen.accueil();
        }
    });
 }
        /**
         * Cette méthode permet de savoir ce qu’il se passe quand on choisit Docteur ou Infirmier 
         * Si on choisit un docteur, on ne peut pas rentrer le salaire ou la rotation
         * Si on choisit un infirmier, on ne peut pas choisir la spécialisation 
        */
    private class StateListener implements ActionListener {

        public @Override void actionPerformed(ActionEvent ae) {
            
            if(ae.getSource()==doc)
            {
                rot.setEnabled(false); /// un docteur ne peut pas avoir de rotation
                salaire.setEnabled(false); /// on ne choisit pas le salaire d'un docteur 
                service.setEnabled(false) ; /// on ne choisit pas le service d'un docteur
                spe.setEnabled(true);  /// on peut choisir la spÃ© d'un docteur 
            }
            if(ae.getSource() == inf)
            {
                rot.setEnabled(true); /// un infirmier a une rotation
                salaire.setEnabled(true); /// on choisit le salaire d'un infirmier
                service.setEnabled(true) ; ///on choisit le service d'un infirmier
                spe.setEnabled(false);  /// les infirmiers n'ont pas de spÃ©
            }
            
        }
    }

    
      
  
    
}
