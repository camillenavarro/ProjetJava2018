/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Hopital;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author camille, rim, roman
 */


public class Hospitalisation {
    
    private JPanel container = new JPanel();
        private JPanel pan = new JPanel();

        private JTextField malade = new JTextField();
        private JTextField service = new JTextField();
        private JTextField chambre = new JTextField();
        private JTextField lit = new JTextField();
        
        private JTextField nomMalade = new JTextField();
        private JTextField prenomMalade = new JTextField();
        
        private JButton submit = new JButton("Valider") ;
        private Hopital hop = new Hopital() ;
    
        /**
         * Constructeur par défaut
         */
    public Hospitalisation(){}
    
    /**
     * Méthode pour ajouter une hospitalisation
     * @param zFen 
     */
    public void ajout(ZFenetre zFen) 
    {
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.add(new JLabel("Numero du malade"));

        malade.setPreferredSize(new Dimension(150, 30));
        malade.setForeground(Color.BLACK);
        container.add(malade);

        container.add(new JLabel("Code service"));

        service.setPreferredSize(new Dimension(150, 30));
        service.setForeground(Color.BLACK);
        container.add(service);

        container.add(new JLabel("Numero de chambre"));

        chambre.setPreferredSize(new Dimension(150, 30));
        chambre.setForeground(Color.BLACK);
        container.add(chambre);

        container.add(new JLabel("Lit"));

        lit.setPreferredSize(new Dimension(150, 30));
        lit.setForeground(Color.BLACK);
        container.add(lit);

        pan.add(container);
        pan.add(submit);
        pan.add(zFen.getBoutonRetour());

        zFen.setContentPane(pan);
        zFen.setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
              if(malade.getText() != "" && service.getText() != "" && chambre.getText() != "" && lit.getText() != "")
              {
                hop.hospi(malade.getText(), service.getText(), chambre.getText(), lit.getText()) ;
                zFen.accueil();
                
              }
              else
                  JOptionPane.showMessageDialog(null, "Le formulaire n'est pas valide");
                
            }
        });
    }
    
    /**
     * Méthode pour supprimer une hospitalisation
     * @param zFen 
     */
    public void suppression(ZFenetre zFen)
    {
        container.add(new JLabel("Nom du malade"));

        nomMalade.setPreferredSize(new Dimension(150, 30));
        nomMalade.setForeground(Color.BLACK);
        container.add(nomMalade);
        
        container.add(new JLabel("Prenom du malade"));

        prenomMalade.setPreferredSize(new Dimension(150, 30));
        prenomMalade.setForeground(Color.BLACK);
        container.add(prenomMalade);
        
        pan.add(container) ;
        pan.add(submit) ;
        pan.add(zFen.getBoutonRetour());
        
        zFen.setContentPane(pan);
        zFen.setVisible(true);
        
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
              if(nomMalade.getText() != "" && prenomMalade.getText() != "")
              {
                  try {
                      System.out.println("formulaire ok");
                      hop.suppression(nomMalade.getText(), prenomMalade.getText(), "hospitalisation") ;                
                      zFen.accueil();
                  } catch (SQLException ex) {
                      System.out.println("SQL problem");
                  } catch (ClassNotFoundException ex) {
                      System.out.println("SQL problem");
                  }
              }
              else
                  JOptionPane.showMessageDialog(null, "Le formulaire n'est pas valide");
                
            }
        });
                
    }
}
