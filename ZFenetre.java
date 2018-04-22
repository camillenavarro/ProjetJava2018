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
import javax.swing.table.DefaultTableModel;

public class ZFenetre extends JFrame {

    ArrayList<String> liste = new ArrayList<>();
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

    private JButton boutonRetour = new JButton("Retour à l'accueil");
    private JButton nouveauP = new JButton("Nouveau patient");
    private JButton nouvelE = new JButton("Nouvel employé");
    private JButton nouveauS = new JButton("Nouveau soin");
    private JButton modifier = new JButton("Modifier une donnée");
    private JButton suppP = new JButton("Supprimer un patient");
    private JButton suppE = new JButton("Supprimer un employé");
    private JButton suppS = new JButton("Supprimer un soin");
    private JPanel pan2 = new JPanel();

    private JButton rechMalade = new JButton("Rechercher un patient");
    private JButton rechInf = new JButton("Rechercher un infirmier");
    private JButton rechDoc = new JButton("Rechercher un docteur");
    private JButton rechServ = new JButton("Rechercher un service");
    private JButton rechChambre = new JButton("Rechercher une chambre");
    private JPanel pan3 = new JPanel();

    private JButton modMalade = new JButton("Modifier un patient");
    private JButton modInfirmier = new JButton("Modifier un infirmier");
    private JButton modDocteur = new JButton("Modifier un docteur");
    private JButton modService = new JButton("Modifier un service");
    private JButton modChambre = new JButton("Modifier une chambre");
    private JPanel pan4 = new JPanel();

    private JPanel pan5 = new JPanel();

    private JFrame f = new JFrame();
    private JLabel image = new JLabel(Fond);

    private JPanel saisie = new JPanel();
    private JButton submit = new JButton("Valider");

    private JTable table;

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

    public ZFenetre() throws IOException {
        this.setSize(866, 648);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(new ImagePanel(new ImageIcon("Medecin.jpg").getImage()));
        //On initialise nos menus   
        this.Fichier.add(menuRetour);
        this.Fichier.add(menuMAJ);
        this.Fichier.add(menuRecherche);
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

    public void accueil() {
        pan1.add(boutonMAJ);
        pan1.add(boutonRech);
        pan1.add(image);
        this.setContentPane(pan1);

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

        pan2.setLayout(new BoxLayout(pan2, BoxLayout.PAGE_AXIS));
        pan2.add(line1);
        pan2.add(line2);
        pan2.add(line3);
        this.setContentPane(pan2);
        this.setVisible(true);
    }

    public void optionsRecherche() {
        pan3.removeAll();
        pan3.add(rechMalade);
        pan3.add(rechInf);
        pan3.add(rechDoc);
        pan3.add(rechServ);
        pan3.add(rechChambre);
        pan3.add(boutonRetour);
        this.setContentPane(pan3);
        this.setVisible(true);
    }

    public void saisieRecherche(String[] champs, String module) {
        //   this.setVisible(false) ;
        ArrayList<JTextField> textField = new ArrayList();
        saisie.removeAll();
        pan3.removeAll();

        for (int i = 0; i < champs.length; i++) {
            JTextField jtf = new JTextField("");
            jtf.setPreferredSize(new Dimension(200, 30));
            jtf.setForeground(Color.black);
            textField.add(jtf);
            saisie.add(new JLabel(champs[i]));
            saisie.add(jtf);
        }

        pan3.add(saisie);
        pan3.add(submit);
        pan3.add(boutonRetour);
        this.setContentPane(pan3);
        this.setVisible(true);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                //DefaultTableModel model = (DefaultTableModel) table.getModel();
                //model.setRowCount(0);
                if (module.equals("Malade")) {
                    liste = hop.malade(textField.get(0).getText(), textField.get(1).getText());
                    tableRechercheM(liste);
                }
                if (module.equals("Infirmier")) {
                    liste = hop.infirmier(textField.get(0).getText(), textField.get(1).getText());
                    tableRechercheI(liste);
                }
                if (module.equals("Docteur")) {
                    liste = hop.docteur(textField.get(0).getText(), textField.get(1).getText());
                    tableRechercheD(liste);
                }
                if (module.equals("Service")) {
                    liste = hop.service(textField.get(0).getText());
                    tableRechercheS(liste);
                }
                if (module.equals("Chambre")) {
                    liste = hop.chambre(textField.get(0).getText(), textField.get(1).getText());
                    tableRechercheC(liste);
                }
            }
        });
    }

    public void tableRechercheM(ArrayList liste) {

        if (liste.size() == 1) {
            JOptionPane.showMessageDialog(null, liste.get(0),"Error",JOptionPane.ERROR_MESSAGE);
        } else if (liste.size() == 6) {
            setLayout(new FlowLayout());
            String[] columnNames = {"Prenom", "Nom", "ID Patient", "Adresse", "Telephone", "Mutuelle"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(2), liste.get(3), liste.get(4), liste.get(5)}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1000, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
            this.setVisible(true) ;
            
        } else {

            setLayout(new FlowLayout());

            while (liste.size() < 11) {
                liste.add("NA");
            }
            JOptionPane.showMessageDialog(null,"Le patient " + liste.get(0) + " " + liste.get(1) + " est actuellement hospitalisé. Veuillez consulter ses informations.");
            String[] columnNames = {"Prenom", "Nom", "ID Patient", "Adresse", "Telephone", "Mutuelle", "Num. de la chambre d'hospitalisation", "Num. du lit", "Surveillant de la chambre", "Service", "Batiment"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(2), liste.get(3), liste.get(4), liste.get(5), liste.get(6), liste.get(7), liste.get(8), liste.get(9), liste.get(10)}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1200, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
            this.setVisible(true) ;
        }

        liste.clear();
    }

    public void tableRechercheI(ArrayList liste) {

        if (liste.size() == 1) {
            JOptionPane.showMessageDialog(null, liste.get(0));
        } else {

            setLayout(new FlowLayout());
            String[] columnNames = {"Prenom", "Nom", "ID Employe", "Adresse", "Telephone", "Rotation"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(3), liste.get(4), liste.get(5), liste.get(2)}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1000, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
            this.setVisible(true) ;
        }
        liste.clear();
    }

    public void tableRechercheD(ArrayList liste) {

        if (liste.size() == 1) {
            JOptionPane.showMessageDialog(null, liste.get(0));
        } else {
            setLayout(new FlowLayout());
            String[] columnNames = {"Prenom", "Nom", "ID Employe", "Adresse", "Telephone", "Specialite"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(2), liste.get(3), liste.get(4), liste.get(5)}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1000, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
            this.setVisible(true) ;
        }
        liste.clear();
    }

    public void tableRechercheS(ArrayList liste) {

        if (liste.size() == 1) {
            JOptionPane.showMessageDialog(null, liste.get(0));
        } else {
            setLayout(new FlowLayout());
            String docteur = "Dr. " + liste.get(3) + " " + liste.get(4);
            String[] columnNames = {"Nom du Service", "Code du Service", "Batiment", "Directeur du Service"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(2), docteur}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1000, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
            this.setVisible(true) ;
        }
        liste.clear();
    }

    public void tableRechercheC(ArrayList liste) {

        if (liste.size() == 1) {
            JOptionPane.showMessageDialog(null, liste.get(0));
        } else {
            setLayout(new FlowLayout());
            String[] columnNames = {"Numero de la Chambre", "Nombre de lits", "ID surveillant", "Infirmier(e)", "Service de la Chambre"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(2), liste.get(3), liste.get(4)}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1000, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
            this.setVisible(true) ;
        }
        liste.clear();
    }



    public void modifier() {
        pan4.removeAll();
        pan4.add(modMalade);
        pan4.add(modInfirmier);
        pan4.add(modDocteur);
        pan4.add(modService);
        pan4.add(modChambre);
        pan4.add(boutonRetour);
        this.setContentPane(pan4);
        this.setVisible(true);
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

    public JButton getBoutonMAJ() {
        return boutonMAJ;
    }

    public JButton getBoutonRecherche() {
        return boutonRech;
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
