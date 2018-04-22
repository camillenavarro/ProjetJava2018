package Vue;

/**
 *
 * @author Rim
 */
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

public class ZFenetre extends JFrame {
    
    
     private final JTextField Login = new JTextField(null);
      private final JTextField MDP = new JTextField(null);
      private final JButton envoie = new JButton("Envoyer");

    ArrayList<String> liste = new ArrayList<>();
    private Hopital hop;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu Fichier = new JMenu("Fichier");

    private JMenuItem menuRetour = new JMenuItem("Retour à l'accueil");
    private JMenuItem menuMAJ = new JMenuItem("Mise à jour");
    private JMenuItem menuRecherche = new JMenuItem("Recherche");
    private JMenuItem menuReport = new JMenuItem("Reporting");
    private JMenuItem Close = new JMenuItem("Fermer");

    private ImageIcon Fond = new ImageIcon("Medecin.jpg");

    private JButton boutonMAJ = new JButton("Mise à Jour");
    private JButton boutonRech = new JButton("Rechercher");
    private JButton boutonRep = new JButton("Reporting");

    private JButton boutonRetour = new JButton("Retour à l'accueil");
    private JButton nouveauP = new JButton("Nouveau patient");
    private JButton nouvelE = new JButton("Nouvel employé");
    private JButton nouveauS = new JButton("Nouveau soin");
    private JButton modifier = new JButton("Modifier une donnée");
    private JButton suppP = new JButton("Supprimer un patient");
    private JButton suppE = new JButton("Supprimer un employé");
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
    
     private class BoutonListener implements ActionListener {
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

    class ImagePanel extends JComponent {

        private Image image;

        public ImagePanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

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

        //L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite
        //Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier
        this.menuBar.add(Fichier);

        this.setJMenuBar(menuBar);
    }
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
        saisie.add(new JLabel("Prénom"));
        saisie.add(prenom);
        
        pan.add(saisie) ;
        pan.add(submit) ;
        pan.add(boutonRetour) ;
        this.setContentPane(pan);
        this.setVisible(true);
        
        submit.addActionListener(new ActionListener() {
            @Override
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

    public JMenuItem getMenuRetour() {
        return menuRetour;
    }

    public JButton getBoutonRetour() {
        return boutonRetour;
    }

    public JMenuItem getMenuMAJ() {
        return menuMAJ;
    }

    public JMenuItem getMenuRecherche() {
        return menuRecherche;
    }
    
    public JMenuItem getMenuReport() {
        return menuReport;
    }

    public JButton getBoutonMAJ() {
        return boutonMAJ;
    }

    public JButton getBoutonRecherche() {
        return boutonRech;
    }
    
    public JButton getBoutonReport() {
        return boutonRep;
    }

    public JButton getNouveauP() {
        return nouveauP;
    }

    public JButton getNouvelE() {
        return nouvelE;
    }

    public JButton getModifier() {
        return modifier;
    }

    public JButton getSuppP() {
        return suppP;
    }

    public JButton getSuppE() {
        return suppE;
    }

    public JButton getNouveauS() {
        return nouveauS;
    }

    public JButton getSuppS() {
        return suppS;
    }

    public JButton getSubmit() {
        return submit;
    }

    public JButton getRechMalade() {
        return rechMalade;
    }

    public JButton getRechInf() {
        return rechInf;
    }

    public JButton getRechDoc() {
        return rechDoc;
    }

    public JButton getRechServ() {
        return rechServ;
    }

    public JButton getRechChambre() {
        return rechChambre;
    }

    public JButton getModMalade() {
        return modMalade;
    }

    public JButton getModInfirmier() {
        return modInfirmier;
    }

    public JButton getModDocteur() {
        return modDocteur;
    }

    public JButton getModService() {
        return modService;
    }

    public JButton getModChambre() {
        return modChambre;
    }

}
