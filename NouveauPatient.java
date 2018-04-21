package Vue;


import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roman
 */


public class NouveauPatient extends JFrame  {
    
      private JTextField nom = new JTextField("");
      private JTextField prenom = new JTextField("");
      private JFormattedTextField tel = new JFormattedTextField(NumberFormat.getIntegerInstance());;
      private JTextField adresse = new JTextField("");
      private JTextField mutuel = new JTextField("");
      
      private JPanel container = new JPanel();
      
      private JLabel titre = new JLabel("Nouveau Patient");
      
      private JButton save = new JButton ("Enregistrer");
      
  /* public static void main(String[] args) throws IOException{
       
   NouveauPatient a = new NouveauPatient();
   
   }*/
     
  public NouveauPatient()throws IOException{
            
            
    this.setSize(1000, 1500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setLocationRelativeTo(null);
  //  container.setBackground(Color.white);
   Font police = new Font("Arial", Font.BOLD, 14);
     
   //container.setLayout(new BoxLayout(titre,BoxLayout.Y_AXIS));
     
    container.add(new JLabel ("Nouveau Patient")); 
    
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
    
   container.add(new JLabel ("Mutuel : "));
   mutuel.setFont(police);
    mutuel.setPreferredSize(new Dimension(150, 30));
    mutuel.setForeground(Color.BLACK);
    container.add(mutuel);
    
    save.addActionListener(new boutonListener());
    container.add(save);
    
    this.setContentPane(container);
    this.setVisible(true);
        }

    private  class boutonListener implements ActionListener {

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
