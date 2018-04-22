package Vue;

import Modele.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class qui crée toutes les fenetres que l'utilisateur verra
 * @author Camille,Rim,Roman
 */
public class ZFenetre extends JFrame {
    
    
     private final JTextField Login = new JTextField(null);
      private final JTextField MDP = new JTextField(null);
      private final JButton envoie = new JButton("Envoyer");

    ArrayList<String> liste = new ArrayList<>();
    private Hopital hop;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu Fichier = new JMenu("Fichier");

    private JMenuItem menuRetour = new JMenuItem("Retour Ã  l'accueil");
    private JMenuItem menuMAJ = new JMenuItem("Mise Ã  jour");
    private JMenuItem menuRecherche = new JMenuItem("Recherche");
    private JMenuItem menuReport = new JMenuItem("Reporting");
    private JMenuItem Close = new JMenuItem("Fermer");

    private ImageIcon Fond = new ImageIcon("Medecin.jpg");

    private JButton boutonMAJ = new JButton("Mise Ã  Jour");
    private JButton boutonRech = new JButton("Rechercher");
    private JButton boutonRep = new JButton("Reporting");

    private JButton boutonRetour = new JButton("Retour Ã  l'accueil");
    private JButton nouveauP = new JButton("Nouveau patient");
    private JButton nouvelE = new JButton("Nouvel employÃ©");
    private JButton nouveauS = new JButton("Nouveau soin");
    private JButton modifier = new JButton("Modifier une donnÃ©e");
    private JButton suppP = new JButton("Supprimer un patient");
    private JButton suppE = new JButton("Supprimer un employÃ©");
    private JButton suppS = new JButton("Supprimer un soin");
  

    private JButton rechMalade = new JButton("Rechercher un patient");
    private JButton rechInf = new JButton("Rechercher un infirmier");
    private JButton rechDoc = new JButton("Rechercher un docteur");
    private JButton rechServ = new JButton("Rechercher un service");
    private JButton rechChambre = new JButton("Rechercher une chambre");
   

    private JButton modMalade = new JButton("Modifier un patient");
    private JButton modInfirmier = new JButton("Modifier un infirmier");
    private JButton modDocteur = new JButton("Modifier un docteur");
    private JButton modService = new JButton("Modifier un service");
    private JButton modChambre = new JButton("Modifier une chambre");
   

   

    private JFrame f = new JFrame();
    private JLabel image = new JLabel(Fond);

    private JPanel saisie = new JPanel();
    private JButton submit = new JButton("Valider");

    private JTable table;
    
    /**
     * Methode qui se lance qaund on appuie sur un bouton
     */
     private class BoutonListener implements ActionListener {
         /**
          * Methode qui lance le bon programme pour un bouton
          * @param ae 
          */
        public void actionPerformed(ActionEvent ae) {
          
            if(ae.getSource() == envoie)
            {
                
                hop = new Hopital(Login.getText(),MDP.getText());
                if (hop.maconnexion.h == true)
                {
                accueil();}
                
            }

        }
    }

     /**
      * Class qui s'occupe de l'image
      */
    class ImagePanel extends JComponent {

        private Image image;
        /**
         * Constructeur pour ImagePanel
         * @param image 
         */
        public ImagePanel(Image image) {
            this.image = image;
        }

        @Override
        /**
         * Methode pour positionner l'image
         */
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    /**
     * Constructeur de ZFenetre qui est la fenetre de base des autres fenetres
     */
    public ZFenetre() {
        this.setSize(866, 648);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(new ImagePanel(new ImageIcon("Medecin.jpg").getImage()));
        //On initialise nos menus   
        this.Fichier.add(menuRetour);
        this.Fichier.add(menuMAJ);
        this.Fichier.add(menuRecherche);
        this.Fichier.add(menuReport);
        f.setContentPane(new JLabel(new ImageIcon("Medecin.jpg")));

        this.Fichier.addSeparator();

        Close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(1);
            }
        });
        this.Fichier.add(Close);

        //L'ordre d'ajout va dÃ©terminer l'ordre d'apparition dans le menu de gauche Ã  droite
        //Le premier ajoutÃ© sera tout Ã  gauche de la barre de menu et inversement pour le dernier
        this.menuBar.add(Fichier);

        this.setJMenuBar(menuBar);
    }
    
    /**
     * Methode qui crée le login
     * L'utilisateur doit rentrer le Login et le mot de passe pour rentre dans la bonne base de données
     */
 public void login()
    {
        JPanel pan = new JPanel() ;
        Fichier.setEnabled(false);
        Font police = new Font("Arial", Font.BOLD, 14);
        pan.add(new JLabel ("Login : "));
        Login.setFont(police);
        Login.setPreferredSize(new Dimension(150, 30));
        Login.setForeground(Color.BLACK);
        pan.add(Login);
        pan.add(new JLabel ("Mot de Passe : "));
        MDP.setFont(police);
        MDP.setPreferredSize(new Dimension(150, 30));
        MDP.setForeground(Color.BLACK);
        pan.add(MDP);
    
        envoie.addActionListener(new BoutonListener());
    
        pan.add(envoie);
        this.setContentPane(pan);
        this.setVisible(true); 
        
        
    }
 
 /**
  * Méthode qui crée l'acceuil
  * L'utilisateur à accès aux diférents boutons pour utuliser les diférentes méthodes
  */
    public void accueil() {
        
        JPanel pan = new JPanel();
        Fichier.setEnabled(true);
        pan.add(boutonMAJ);
        pan.add(boutonRech);
        pan.add(boutonRep);
        pan.add(image);
        this.setContentPane(pan);

        this.setVisible(true);
    }

    /**
     * Méthode qui crée les Mise à jour pour la base de données
     * 
     */
    public void MAJ() {
        Box line1 = Box.createHorizontalBox();
        Box line2 = Box.createHorizontalBox();
        Box line3 = Box.createHorizontalBox();

        line1.add(nouveauP);
        line1.add(nouvelE);
        line1.add(nouveauS);
        line1.add(modifier);
        line2.add(suppP);
        line2.add(suppE);
        line2.add(suppS);
        line3.add(boutonRetour);
        
        JPanel pan = new JPanel() ;
        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
        pan.add(line1);
        pan.add(line2);
        pan.add(line3);
        this.setContentPane(pan);
        this.setVisible(true);
    }

    /**
     * Methode qui crée la fenetre recherche 
     */
    public void optionsRecherche() {
        
        JPanel pan = new JPanel();
        pan.add(rechMalade);
        pan.add(rechInf);
        pan.add(rechDoc);
        pan.add(rechServ);
        pan.add(rechChambre);
        pan.add(boutonRetour);
        this.setContentPane(pan);
        this.setVisible(true);
    }

    
    /**
     * Méthode qui crée la fenetre modifier 
     */
    public void modifier() {
        JPanel pan = new JPanel() ;
        pan.add(modMalade);
        pan.add(modInfirmier);
        pan.add(modDocteur);
        pan.add(modService);
        pan.add(modChambre);
        pan.add(boutonRetour);
        this.setContentPane(pan);
        this.setVisible(true);
    }
    
    /**
     * Méthode qui crée la fenetre supprimer
     * @param table 
     */
    public void supprimer(String table)
    {
        JPanel pan = new JPanel() ;
        saisie.removeAll();
        
        JTextField nom = new JTextField("");
        nom.setPreferredSize(new Dimension(200, 30));
        nom.setForeground(Color.black);
        saisie.add(new JLabel("Nom"));
        saisie.add(nom);
        
        JTextField prenom = new JTextField("");
        prenom.setPreferredSize(new Dimension(200, 30));
        prenom.setForeground(Color.black);
        saisie.add(new JLabel("PrÃ©nom"));
        saisie.add(prenom);
        
        pan.add(saisie) ;
        pan.add(submit) ;
        pan.add(boutonRetour) ;
        this.setContentPane(pan);
        this.setVisible(true);
        
        submit.addActionListener(new ActionListener() {
            @Override
            /**
             * Methode qui lance la suppression apres l'appuie du bouton 
             */
            public void actionPerformed(ActionEvent arg0) {
                try {
                    hop.suppression(nom.getText(), prenom.getText(), table) ;
                } catch (SQLException ex) {
                    Logger.getLogger(ZFenetre.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ZFenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
                accueil();
            }
        });
    }
    
    /**
     * Méthode qui saisit les noms et prénoms d'un docteur et d'un malade pour les ajouter dans la table soigne
     */
    public void nouveauSoin()
    {
        JPanel container = new JPanel();
        JPanel pan = new JPanel();
        
        JTextField nomDocteur = new JTextField() ;
        JTextField prenomDocteur = new JTextField() ;
        
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.add(new JLabel("Nom du docteur : "));
        
        nomDocteur.setPreferredSize(new Dimension(150, 30));
        nomDocteur.setForeground(Color.BLACK);
        container.add(nomDocteur);

        container.add(new JLabel("Prénom du docteur : "));
       
        prenomDocteur.setPreferredSize(new Dimension(150, 30));
        prenomDocteur.setForeground(Color.BLACK);
        container.add(prenomDocteur);
        
        JTextField nomMalade = new JTextField() ;
        JTextField prenomMalade = new JTextField() ;
        
        
        container.add(new JLabel("Nom du malade : "));
        
        nomMalade.setPreferredSize(new Dimension(150, 30));
        nomMalade.setForeground(Color.BLACK);
        container.add(nomMalade);

        container.add(new JLabel("Prénom du malade : "));
       
        prenomMalade.setPreferredSize(new Dimension(150, 30));
        prenomMalade.setForeground(Color.BLACK);
        container.add(prenomMalade);
        
        pan.add(container) ;
        pan.add(submit) ;
        
        this.setContentPane(pan) ;
        this.setVisible(true) ;
        
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(hop.soin(nomDocteur.getText(), prenomDocteur.getText(), nomMalade.getText(), prenomMalade.getText()))
                    accueil();
                else
                    JOptionPane.showMessageDialog(null, "Le docteur ou le malade n'existe pas dans nos répertoires.");
            }
        });
    }
    
    /**
     * Methode qui affiche un message d'erreur 
     * @param champ 
     */
    public void messageErreur(String champ) 
    {
        JOptionPane.showMessageDialog(null,"Le champ " + champ + " n'est pas valide.");
        
    }
    /**
     * Methode qui revient à l'acceuil
     * @return menuRetour
     */
    public JMenuItem getMenuRetour() {
        return menuRetour;
    }

    /**
     * Methode qui revient à l'acceuil
     * @return boutonRetour
     */
    public JButton getBoutonRetour() {
        return boutonRetour;
    }

    /**
     * Methode qui revient à la MAJ
     * @return menuMAJ
     */
    public JMenuItem getMenuMAJ() {
        return menuMAJ;
    }
    
    /**
     * Méthode qui revient à la Recherche
     * @return menuRecherche
     */
    public JMenuItem getMenuRecherche() {
        return menuRecherche;
    }
    
    /**
     * Méthode qui revient à au reporting
     * @return menuReport
     */
    public JMenuItem getMenuReport() {
        return menuReport;
    }
    
    /**
     * Methode qui revient à la MAJ
     * @return boutonMAJ
     */
    public JButton getBoutonMAJ() {
        return boutonMAJ;
    }

    /**
     * Methode qui revient à la Recherche
     * @return boutonRech
     */
    public JButton getBoutonRecherche() {
        return boutonRech;
    }
    
     /**
     * Methode qui revient au reporting
     * @return boutonRep
     */
    public JButton getBoutonReport() {
        return boutonRep;
    }
    
     /**
     * Methode qui revient au Nouveau Patient
     * @return nouveaup
     */
    public JButton getNouveauP() {
        return nouveauP;
    }
    
     /**
     * Methode qui revient à la NouvelE
     * @return nouvelE
     */
    public JButton getNouvelE() {
        return nouvelE;
    }
    
     /**
     * Methode qui revient à la modification
     * @return modifier
     */
    public JButton getModifier() {
        return modifier;
    }
    
     /**
     * Methode qui revient à la Supression Patient
     * @return suppP
     */
    public JButton getSuppP() {
        return suppP;
    }
    
     /**
     * Methode qui revient à la Supression Employer
     * @return suppE
     */
    public JButton getSuppE() {
        return suppE;
    }
    
     /**
     * Methode qui revient au Service
     * @return nouveauS
     */
    public JButton getNouveauS() {
        return nouveauS;
    }
    
     /**
     * Methode qui revient à la Supression Service
     * @return suppS
     */
    public JButton getSuppS() {
        return suppS;
    }

       
     /**
     * Methode qui lance le bouton validation
     * @return submit
     */
    public JButton getSubmit() {
        return submit;
    }
    
     /**
     * Methode qui revient à la Recherche Malade
     * @return rechMalade
     */
    public JButton getRechMalade() {
        return rechMalade;
    }
    
     /**
     * Methode qui revient à la Recherche Infirmier
     * @return rechInf
     */
    public JButton getRechInf() {
        return rechInf;
    }
    
     /**
     * Methode qui revient à la Recherche Malade
     * @return rechMalade
     */
    public JButton getRechDoc() {
        return rechDoc;
    }
    
     /**
     * Methode qui revient à la Recherche Service
     * @return rechServ
     */
    public JButton getRechServ() {
        return rechServ;
    }
    
     /**
     * Methode qui revient à la Recherche Chambre
     * @return rechChambre
     */
    public JButton getRechChambre() {
        return rechChambre;
    }
    
     /**
     * Methode qui revient à la Modification Malade
     * @return modMalade
     */
    public JButton getModMalade() {
        return modMalade;
    }
    
     /**
     * Methode qui revient à la modification Employé
     * @return mdEmploye
     */
    public JButton getModEmploye() {
        return modEmploye;
    }


}
