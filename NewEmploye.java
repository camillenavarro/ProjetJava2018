/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author roman
 */
public class NewEmploye extends JFrame {
    
    
      private JTextField nom = new JTextField("");
      private JTextField prenom = new JTextField("");
      private JTextField adresse = new JTextField("");
      private JTextField mutuel = new JTextField("");
      
      private JFormattedTextField salaire = new JFormattedTextField(NumberFormat.getIntegerInstance());
      private JFormattedTextField tel = new JFormattedTextField(NumberFormat.getIntegerInstance());
      
      private JRadioButton doc = new JRadioButton("Docteur");
      private JRadioButton inf = new JRadioButton("Infirmier");
      
      private ButtonGroup bg = new ButtonGroup();
      
      private JComboBox spe = new JComboBox();
      private JLabel Spe = new JLabel("Spécialisation");
      
      private JComboBox rot = new JComboBox();
      private JLabel Rot = new JLabel("Rotation");
      
      private JPanel container = new JPanel();
      
      private JButton save = new JButton ("Enregistrer");
      
      public NewEmploye()
      {
          
   this.setSize(1000, 1500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setLocationRelativeTo(null);
  //  container.setBackground(Color.white);
   Font police = new Font("Arial", Font.BOLD, 14);
    
   container.add(new JLabel ("Nouvel Employé"));  
   
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
        doc.setSelected(true);
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
    
    container.add(Rot);
    container.add(rot);
    
    rot.setPreferredSize(new Dimension(100, 20));
    rot.addItem("JOUR");
    rot.addItem("NUIT");
    
    container.add(new JLabel ("Salaire : "));
    salaire.setFont(police);
    salaire.setPreferredSize(new Dimension(150, 30));
    salaire.setForeground(Color.BLACK);
    container.add(salaire);
    
    save.addActionListener(new boutonListener());
    container.add(save);
    
    
    this.setContentPane(container);
    this.setVisible(true);  
      }

    private class StateListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            
            if(ae.getSource()==doc)
            {
                rot.setEnabled(false); /// un docteur ne peut pas avoir de rotation
                salaire.setEnabled(false); /// on ne choisie pas le salaire d'un docteur 
                spe.setEnabled(true);  /// on peut chosir la spé d'un docteur 
            }
            if(ae.getSource() == inf)
            {
                rot.setEnabled(true); /// un infirmier à une rotation
                salaire.setEnabled(true); /// on choisie le salaire d'un infirmié
                spe.setEnabled(false);  /// les infirmier n'ont pas de spé
            }
            
        }
    }

    private class boutonListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
           if(ae.getSource() == save)
            {
               
              try {
                  new ZFenetre();
              } catch (IOException ex) {
                  Logger.getLogger(NouveauPatient.class.getName()).log(Level.SEVERE, null, ex);
              }
           
        }
            
        }
    }
      
  
    
}
