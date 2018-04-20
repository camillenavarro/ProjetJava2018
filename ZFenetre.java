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
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;


public class ZFenetre extends JFrame {
    private Hopital hop = new Hopital();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu Fichier = new JMenu("Fichier");
    
    private JMenuItem Retour = new JMenuItem("Retour à l'accueil");
    private JMenuItem Misajour = new JMenuItem("Mise à jour");
    private JMenuItem Recherche = new JMenuItem("Recherche");
    private JMenuItem Close = new JMenuItem("Fermer");

    private ImageIcon Fond = new ImageIcon("Medecin.jpg");

    private JButton maj = new JButton("Mise à Jour");
    private JButton recher = new JButton("Rechercher");
    private JPanel pan1 = new JPanel();
   
    private JButton nouveauP = new JButton("Nouveau patient") ;
    private JButton nouvelE = new JButton("Nouvel employé") ;
    private JButton modifier = new JButton("Modifier une donnée") ;
    private JButton suppP = new JButton("Supprimer un patient") ;
    private JButton suppE = new JButton("Supprimer un employé") ;
    private JPanel pan2 = new JPanel();
    
    private JButton Malades = new JButton("Rechercher un patient") ;
    private JButton Infirmiers = new JButton("Rechercher un infirmier") ;
    private JButton Docteurs = new JButton("Rechercher un docteur") ;
    private JButton Services = new JButton("Rechercher un service") ;
    private JButton Chambres = new JButton("Rechercher une chambre") ;
    private JPanel pan3 = new JPanel();
   
    private JFrame f = new JFrame();
    private JLabel image = new JLabel(Fond);
    
    private ArrayList<JTextField> textField = new ArrayList();
    private JPanel saisie = new JPanel() ;
    private JButton submit = new JButton("Valider") ;
    
    
   
    class ImagePanel extends JComponent {
        private Image image;
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
        this.setSize(1000, 748);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(new ImagePanel(new ImageIcon("Medecin.jpg").getImage()));
        //On initialise nos menus   
        this.Fichier.add(Retour);
        this.Fichier.add(Misajour);
        this.Fichier.add(Recherche);
        f.setContentPane(new JLabel(new ImageIcon("Medecin.jpg")));
        
        this.Fichier.addSeparator();
    
        Close.addActionListener(new ActionListener(){
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
  
    public void accueil() 
    {
        pan1.add(maj);
        pan1.add(recher) ;
        pan1.add(image);
        this.setContentPane(pan1);
    
        this.setVisible(true);
    }
  
    public void MAJ() 
    { 
        pan2.add(nouveauP);
        pan2.add(nouvelE) ;
        pan2.add(modifier);
        pan2.add(suppP) ;
        pan2.add(suppE) ;
        this.setContentPane(pan2);
        this.setVisible(true);
    }
    
    public void optionsRecherche() 
    { 
        pan3.add(Malades);
        pan3.add(Infirmiers) ;
        pan3.add(Docteurs) ;
        pan3.add(Services);
        pan3.add(Chambres);
        this.setContentPane(pan2);
        this.setVisible(true);
    }
  
    public void saisieRecherche(String[] champs, String module)
    {
        this.setVisible(false) ;
        
        for(int i = 0 ; i < champs.length ; i++)
        {
            JTextField jtf = new JTextField("") ;
            jtf.setPreferredSize(new Dimension(200, 30));
            jtf.setForeground(Color.black);
            textField.add(jtf) ;
            saisie.add(new JLabel(champs[i]));
            saisie.add(jtf) ;
        }
        
        pan2.add(saisie, BorderLayout.WEST);
        pan2.add(submit) ;
        this.setContentPane(pan2);
        this.setVisible(true);
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                if(module == "Malades") 
                    hop.malade(textField.get(0).getText(), textField.get(1).getText()) ;
                if(module == "Employes") 
                    hop.employe(textField.get(0).getText(), textField.get(1).getText()) ;
                if(module == "Services") 
                    hop.service(textField.get(0).getText()) ;
                
               
            }        
        });
    }
    
    public JMenuItem getRetour()
    {
        return Retour ;
    }
  
    public JMenuItem getMAJmenu()
    {
        return Misajour ;
    }
  
    public JMenuItem getRecherMenu()
    {
        return Recherche ;
    }
  
    public JButton getMAJ()
    {
        return maj ;
    }
  
    public JButton getRecherche()
    {
        return recher ;
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
    
    public JButton getSubmit()
    {
        return submit ;
    }
    
    public JButton getMalades()
    {
        return Malades ;
    }
  
    public JButton getInfirmiers()
    {
        return Infirmiers ;
    }
    
    public JButton getDocteurs()
    {
        return Docteurs ;
    }
    
    public JButton getServices()
    {
        return Services ;
    }
    
    public JButton getChambres()
    {
        return Chambres ;
    }
}
