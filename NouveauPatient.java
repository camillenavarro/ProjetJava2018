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
 *Cette class permet la cr�ation d'un nouveau Patient 
 * Elle affiche le formulaire pour un nouveau Patient 
 * @author roman
 */


public class NouveauPatient  {
    
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu Fichier = new JMenu("Fichier");
    
    private final JMenuItem menuRetour = new JMenuItem("Retour � l'accueil");
    private final JMenuItem menuMAJ = new JMenuItem("Mise � jour");
    private final JMenuItem menuRecherche = new JMenuItem("Recherche");
    private final JMenuItem Close = new JMenuItem("Fermer");

    private final ImageIcon Fond = new ImageIcon("Medecin.jpg");
    private final JFrame f = new JFrame();
      private JFormattedTextField numero = new JFormattedTextField("");
      private JTextField nom = new JTextField("");
      private JTextField prenom = new JTextField("");
      private JFormattedTextField tel = new JFormattedTextField(NumberFormat.getIntegerInstance());
      private JTextField adresse = new JTextField("");
      private JTextField mutuel = new JTextField("");
      private JFormattedTextField chambre = new JFormattedTextField(NumberFormat.getIntegerInstance());
      private JTextField service = new JTextField("");
      
      private JPanel container = new JPanel();
      
      private JLabel titre = new JLabel("Nouveau Patient");
      
      private JButton save = new JButton ("Enregistrer");
      private ArrayList<String> donnees = new ArrayList();
     
      
      private JButton retour = new JButton("Retour à l'accueil");
      private Hopital hop = new Hopital() ;
    

     /**
      * Constructeur de la class
      * Elle cr�e le formulaire pour ajouter un nouveau Patient 
      * @param zFen 
      */
  public NouveauPatient(ZFenetre zFen){
           
    
  //  container.setBackground(Color.white);
   Font police = new Font("Arial", Font.BOLD, 14);
     
   container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
    
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
    
   container.add(new JLabel ("Mutuelle : "));
   mutuel.setFont(police);
    mutuel.setPreferredSize(new Dimension(150, 30));
    mutuel.setForeground(Color.BLACK);
    container.add(mutuel);
    
//    container.add(new JLabel ("Service : "));
//    service.setFont(police);
//    service.setPreferredSize(new Dimension(150, 30));
//    service.setForeground(Color.BLACK);
//    container.add(service);
//    
//    container.add(new JLabel ("Chambre : "));
//    chambre.setFont(police);
//    chambre.setPreferredSize(new Dimension(150, 30));
//    chambre.setForeground(Color.BLACK);
//    container.add(chambre);
    
    JPanel truc = new JPanel() ;
    truc.add(container);
    truc.add(save);
    truc.add(retour);
    zFen.setContentPane(truc);
    zFen.setVisible(true);
    
    save.addActionListener(new ActionListener() {
        /**
        * Cette m�thode s'occupe de ce qui se passe quand on appuie sur le bouton "Eregistrer"
        * Elle v�rifie si le formulaire n'est pas vide
        * Et appelle la fonction nouvelle patient dans H�pital si le formulaire est plein sinon elle affiche un message d'erreur
         * @param e 
         */
        public @Override void actionPerformed(ActionEvent e) {
            donnees.clear();
//            if(!(numero.getText()).equals("") && (hop.numeroValideMalade(numero.getText())) )
//                donnees.add(numero.getText());
            if(!(nom.getText()).equals(""))
               donnees.add(nom.getText());
            if(!(prenom.getText()).equals(""))
                donnees.add(prenom.getText());
            if(!(tel.getText()).equals(""))
                donnees.add(tel.getText());
            if(!(adresse.getText()).equals(""))
                donnees.add(adresse.getText());
            if(!(mutuel.getText()).equals(""))
                donnees.add(mutuel.getText());
//            if(!(service.getText()).equals(""))
//                donnees.add(service.getText());
//            if(!(chambre.getText()).equals(""))
//                donnees.add(chambre.getText());
    
            if (donnees.size() == 5)
            {
                System.out.println("Formulaire valide");
                hop.nouveauPatient(donnees);
                zFen.accueil();
            }
            else
            {
                
                //JTextArea erreur = new JTextArea("Formulaire non valide !") ;
                System.out.println("Formulaire non valide");
            }
        }
    }
    ) ; 
    
    retour.addActionListener(new ActionListener() {
        /**
         * Cette m�thode s'occupe de ce qui se passe quand on appuie sur "Retour � l'accueil"
         * Elle appelle la fonction accueil de ZFenetre
         * @param e 
         */
        public @Override void actionPerformed(ActionEvent e) {
            zFen.accueil();
        }
    });
  }
} 
  
    
//  public JButton getSave()
//  {
//      return save ;
//  }
//  
//  public JButton getRetour()
//  {
//      return retour ;
//  }
//          
//  public ArrayList<String> getDonnees()
//  {
//      return donnees ;
//  }
//}
