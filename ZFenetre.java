package Vue;

/**
 *
 * @author Rim
 */
import Modele.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;
import java.io.IOException;
//import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;


public class ZFenetre extends JFrame {
    private final Hopital hop = new Hopital();
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu Fichier = new JMenu("Fichier");
    
    private final JMenuItem menuRetour = new JMenuItem("Retour à l'accueil");
    private final JMenuItem menuMAJ = new JMenuItem("Mise à jour");
    private final JMenuItem menuRecherche = new JMenuItem("Recherche");
    private final JMenuItem Close = new JMenuItem("Fermer");

    private final ImageIcon Fond = new ImageIcon("Medecin.jpg");

    private final JButton boutonMAJ = new JButton("Mise à Jour");
    private final JButton boutonRech = new JButton("Rechercher");
    private final JPanel pan1 = new JPanel();
   
    private final JButton boutonRetour = new JButton("Retour à l'accueil") ;
    private final JButton nouveauP = new JButton("Nouveau patient") ;
    private final JButton nouvelE = new JButton("Nouvel employé") ;
    private final JButton nouveauS = new JButton("Nouveau soin") ;
    private final JButton modifier = new JButton("Modifier une donnée") ;
    private final JButton suppP = new JButton("Supprimer un patient") ;
    private final JButton suppE = new JButton("Supprimer un employé") ;
    private final JButton suppS = new JButton("Supprimer un soin") ;
    private final JPanel pan2 = new JPanel();
    
    private final JButton rechMalade = new JButton("Rechercher un patient") ;
    private final JButton rechInf = new JButton("Rechercher un infirmier") ;
    private final JButton rechDoc = new JButton("Rechercher un docteur") ;
    private final JButton rechServ = new JButton("Rechercher un service") ;
    private final JButton rechChambre = new JButton("Rechercher une chambre") ;
    private final JPanel pan3 = new JPanel();
    
    private final JButton modMalade = new JButton("Modifier un patient") ;
    private final JButton modInfirmier = new JButton("Modifier un infirmier") ;
    private final JButton modDocteur = new JButton("Modifier un docteur") ;
    private final JButton modService = new JButton("Modifier un service") ;
    private final JButton modChambre = new JButton("Modifier une chambre") ;
    private final JPanel pan4 = new JPanel() ;
    
    private final JPanel pan5 = new JPanel() ;
   
    private final JFrame f = new JFrame();
    private final JLabel image = new JLabel(Fond);
    
    private final JPanel saisie = new JPanel() ;
    private final JButton submit = new JButton("Valider") ;
    
    
   
    class ImagePanel extends JComponent {
        private final Image image;
        public ImagePanel(Image image) {
            this.image = image;
        }
        
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    public ZFenetre() throws IOException 
    {
        this.setSize(866,648);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(new ImagePanel(new ImageIcon("Medecin.jpg").getImage()));
        //On initialise nos menus   
        this.Fichier.add(menuRetour);
        this.Fichier.add(menuMAJ);
        this.Fichier.add(menuRecherche);
        f.setContentPane(new JLabel(new ImageIcon("Medecin.jpg")));
        
        this.Fichier.addSeparator();
    
        Close.addActionListener(new ActionListener(){
            public @Override void actionPerformed(ActionEvent arg0) {
                System.exit(1);
            }        
        });
        this.Fichier.add(Close); 
    
        //L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite
        //Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier
        this.menuBar.add(Fichier);
   
        this.setJMenuBar(menuBar);
    }
  
    public void accueil() 
    {
        pan1.add(boutonMAJ);
        pan1.add(boutonRech) ;
        pan1.add(image);
        this.setContentPane(pan1);
    
        this.setVisible(true);
    }
  
    public void MAJ() 
    { 
        Box line1 = Box.createHorizontalBox() ;
        Box line2 = Box.createHorizontalBox() ;
        Box line3 = Box.createHorizontalBox() ;
        
        line1.add(nouveauP);
        line1.add(nouvelE) ;
        line1.add(nouveauS) ;
        line1.add(modifier);
        line2.add(suppP) ;
        line2.add(suppE) ;
        line2.add(suppS);
        line3.add(boutonRetour);
        
        pan2.setLayout(new BoxLayout(pan2, BoxLayout.PAGE_AXIS));
        pan2.add(line1);
        pan2.add(line2);
        pan2.add(line3);
        this.setContentPane(pan2);
        this.setVisible(true);
    }
    
    public void optionsRecherche() 
    { 
        pan3.removeAll();
        pan3.add(rechMalade);
        pan3.add(rechInf) ;
        pan3.add(rechDoc) ;
        pan3.add(rechServ);
        pan3.add(rechChambre);
        pan3.add(boutonRetour);
        this.setContentPane(pan3);
        this.setVisible(true);
    }
  
    public void saisieRecherche(String[] champs, String module)
    {
     //   this.setVisible(false) ;
        ArrayList<JTextField> textField = new ArrayList();
        saisie.removeAll();
        pan3.removeAll();
        
        for(int i = 0 ; i < champs.length ; i++)
        {
            JTextField jtf = new JTextField("") ;
            jtf.setPreferredSize(new Dimension(200, 30));
            jtf.setForeground(Color.black);
            textField.add(jtf) ;
            saisie.add(new JLabel(champs[i]));
            saisie.add(jtf) ;
        }
        
        pan3.add(saisie);
        pan3.add(submit) ;
        pan3.add(boutonRetour);
        this.setContentPane(pan3);
        this.setVisible(true);
        submit.addActionListener(new ActionListener(){
            public @Override void actionPerformed(ActionEvent arg0) {
                if(module == "Malade") 
                    hop.malade(textField.get(0).getText(), textField.get(1).getText()) ;
                if(module == "Infirmier") 
                    hop.infirmier(textField.get(0).getText(), textField.get(1).getText()) ;
                if(module == "Docteur") 
                    hop.docteur(textField.get(0).getText(), textField.get(1).getText()) ;
                if(module == "Service") 
                    hop.service(textField.get(0).getText()) ;
                if(module == "Chambre") 
                    hop.chambre(textField.get(0).getText(), textField.get(1).getText()) ;
                
            }  
        }); 
    }
    
    public void ajouterPatient()
    {
        JTextField numero = new JTextField("") ;
        JTextField nom = new JTextField("") ;
        JTextField prenom = new JTextField("") ;
        JTextField adresse = new JTextField("") ;
        JTextField tel = new JTextField("") ;
        JTextField mutuelle = new JTextField("") ;
        JTextField chambre = new JTextField("") ;
        
        numero.setPreferredSize(new Dimension(200, 30));
        numero.setForeground(Color.black);
        nom.setPreferredSize(new Dimension(200, 30));
        nom.setForeground(Color.black);
        prenom.setPreferredSize(new Dimension(200, 30));
        prenom.setForeground(Color.black);
        adresse.setPreferredSize(new Dimension(200, 30));
        adresse.setForeground(Color.black);
        tel.setPreferredSize(new Dimension(200, 30));
        tel.setForeground(Color.black);
        mutuelle.setPreferredSize(new Dimension(200, 30));
        mutuelle.setForeground(Color.black);
        chambre.setPreferredSize(new Dimension(200, 30));
        chambre.setForeground(Color.black);
        
        saisie.setLayout(new BoxLayout(saisie, BoxLayout.PAGE_AXIS));
        saisie.add(new JLabel("Numero"));
        saisie.add(numero) ;
        saisie.add(new JLabel("Nom"));
        saisie.add(nom) ;
        saisie.add(new JLabel("Prénom"));
        saisie.add(prenom) ;
        saisie.add(new JLabel("Adresse"));
        saisie.add(adresse) ;
        saisie.add(new JLabel("Téléphone"));
        saisie.add(tel) ;
        saisie.add(new JLabel("Mutuelle"));
        saisie.add(mutuelle) ;
        saisie.add(new JLabel("Chambre"));
        saisie.add(chambre) ;
        
        pan5.add(saisie);
        pan5.add(submit);
        pan5.add(boutonRetour);
        this.setContentPane(pan5);
        this.setVisible(true);
        submit.addActionListener(new ActionListener(){
            public @Override void actionPerformed(ActionEvent arg0) {
                //ajout
            }  
        }); 
    }
    
    public void modifier()
    {
        pan4.removeAll();
        pan4.add(modMalade) ;
        pan4.add(modInfirmier) ;
        pan4.add(modDocteur) ;
        pan4.add(modService) ;
        pan4.add(modChambre) ;
        pan4.add(boutonRetour) ;
        this.setContentPane(pan4);
        this.setVisible(true);
    }
    
    public JMenuItem getMenuRetour()
    {
        return menuRetour ;
    }
    
    public JButton getBoutonRetour()
    {
        return boutonRetour ;
    }
  
    public JMenuItem getMenuMAJ()
    {
        return menuMAJ ;
    }
  
    public JMenuItem getMenuRecherche()
    {
        return menuRecherche ;
    }
  
    public JButton getBoutonMAJ()
    {
        return boutonMAJ ;
    }
  
    public JButton getBoutonRecherche()
    {
        return boutonRech ;
    }
  
    public JButton getNouveauP()
    {
        return nouveauP ;
    }
  
    public JButton getNouvelE()
    {
        return nouvelE ;
    }
  
    public JButton getModifier()
    {
        return modifier ;
    }
  
    public JButton getSuppP()
    {
        return suppP ;
    }
  
    public JButton getSuppE()
    {
        return suppE ;
    }
    
    public JButton getNouveauS()
    {
        return nouveauS ;
    }
  
    public JButton getSuppS()
    {
        return suppS ;
    }
    
    public JButton getSubmit()
    {
        return submit ;
    }
    
    public JButton getRechMalade()
    {
        return rechMalade ;
    }
  
    public JButton getRechInf()
    {
        return rechInf ;
    }
    
    public JButton getRechDoc()
    {
        return rechDoc ;
    }
    
    public JButton getRechServ()
    {
        return rechServ ;
    }
    
    public JButton getRechChambre()
    {
        return rechChambre ;
    }
    
    public JButton getModMalade()
    {
        return modMalade ;
    }
    
    public JButton getModInfirmier()
    {
        return modInfirmier ;
    }
    
    public JButton getModDocteur()
    {
        return modDocteur ;
    }
    
    public JButton getModService()
    {
        return modService ;
    }
    
    public JButton getModChambre()
    {
        return modChambre ;
    }
    
}
