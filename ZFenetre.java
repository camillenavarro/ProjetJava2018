package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
 
import Vue.NouveauPatient;
import Vue.NewEmploye;
import java.util.logging.Level;
import java.util.logging.Logger;
;




public class ZFenetre extends JFrame {
  private JMenuBar menuBar = new JMenuBar();
  private JMenu Fichier = new JMenu("Fichier");



  private JMenuItem Patien = new JMenuItem("Nouveau Patient");
  private JMenuItem Recherche = new JMenuItem("Recherche");
  private JMenuItem Close = new JMenuItem("Fermer");

  
    private ImageIcon Fond = new ImageIcon("Medecin.jpg");

   private JButton nouveauP = new JButton("Nouveau Patient");
   private JButton recher = new JButton("Rechercher");
   private JButton newemp = new JButton("Nouvel Employé");
   
   private JPanel pan = new JPanel();
     JFrame f = new JFrame();
  JLabel image = new JLabel(Fond);
  
  
  

    private class boutonListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() == nouveauP)
            {try {
                NouveauPatient a = new NouveauPatient();
               // this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(ZFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(ae.getSource() == newemp)
        {       NewEmploye a = new NewEmploye();
      
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

  public static void main(String[] args) throws IOException{
    ZFenetre zFen = new ZFenetre();
   
  }

  public ZFenetre() throws IOException{
        
       
    this.setSize(1000, 1500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
this.setContentPane(new ImagePanel(new ImageIcon("Fond.png").getImage()));
      nouveauP.addActionListener(new boutonListener());
      newemp.addActionListener(new boutonListener());
    //On initialise nos menus      
    this.Fichier.add(Patien);
    this.Fichier.add(Recherche);
    
    
    
   
    
   
   f.setContentPane(new JLabel(new ImageIcon("Medecin.jpg")));
 
         pan.add(nouveauP);
         pan.add(recher) ;
         pan.add(newemp);
          pan.add(image);
    this.setContentPane(pan);
    
   
    

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
    this.setVisible(true);
  }}