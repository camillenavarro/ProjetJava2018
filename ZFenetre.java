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
    
    private JMenuItem menuRetour = new JMenuItem("Retour à l'accueil");
    private JMenuItem menuMAJ = new JMenuItem("Mise à jour");
    private JMenuItem menuRecherche = new JMenuItem("Recherche");
    private JMenuItem Close = new JMenuItem("Fermer");

    private ImageIcon Fond = new ImageIcon("Medecin.jpg");

    private JButton boutonMAJ = new JButton("Mise à Jour");
    private JButton boutonRech = new JButton("Rechercher");
    private JPanel pan1 = new JPanel();
   
    private JButton boutonRetour = new JButton("Retour à l'accueil") ;
    private JButton nouveauP = new JButton("Nouveau patient") ;
    private JButton nouvelE = new JButton("Nouvel employé") ;
    private JButton modifier = new JButton("Modifier une donnée") ;
    private JButton suppP = new JButton("Supprimer un patient") ;
    private JButton suppE = new JButton("Supprimer un employé") ;
    private JPanel pan2 = new JPanel();
    
    private JButton rechMalade = new JButton("Rechercher un patient") ;
    private JButton rechInf = new JButton("Rechercher un infirmier") ;
    private JButton rechDoc = new JButton("Rechercher un docteur") ;
    private JButton rechServ = new JButton("Rechercher un service") ;
    private JButton rechChambre = new JButton("Rechercher une chambre") ;
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
        this.Fichier.add(menuRetour);
        this.Fichier.add(menuMAJ);
        this.Fichier.add(menuRecherche);
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
        pan1.add(boutonMAJ);
        pan1.add(boutonRech) ;
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
        pan2.add(boutonRetour);
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
        textField.clear(); ;
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
        
        pan3.add(saisie, BorderLayout.WEST);
        pan3.add(submit) ;
        this.setContentPane(pan3);
        this.setVisible(true);
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                if(module == "Malades") 
                    hop.malade(textField.get(0).getText(), textField.get(1).getText()) ;
                if(module == "Employes") 
                    hop.employe(textField.get(0).getText(), textField.get(1).getText()) ;
                if(module == "Services") 
                    hop.service(textField.get(0).getText()) ;
//                pan3.remove(saisie);
//                pan3.remove(submit);
//                for(int i = champs.length ; i >=0 ; i--)
//                {
//                    textField.remove(i);
//                    saisie.remove(i); 
//                }
               
            }  
        });
        
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
}
