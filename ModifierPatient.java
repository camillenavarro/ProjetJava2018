/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Hopital;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Cette class permert de modifier un Patient
 *
 * @author Camille,Rim,Roman
 */
public class ModifierPatient {

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu Fichier = new JMenu("Fichier");

    private final JMenuItem menuRetour = new JMenuItem("Retour Ã  l'accueil");
    private final JMenuItem menuMAJ = new JMenuItem("Mise Ã  jour");
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

    private JButton save = new JButton("Enregistrer");
    private ArrayList<String> donnees = new ArrayList();

    private JButton retour = new JButton("Retour Ã  l'accueil");
    private Hopital hop = new Hopital();

    /**
     * Constructeur de ModifierPatient Affiche le formuaire pour modifier un
     * Patient
     *
     * @param zFen
     */
    public ModifierPatient(ZFenetre zFen) {

        Font police = new Font("Arial", Font.BOLD, 14);

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(new JLabel("Nouveau Patient"));

        container.add(new JLabel("Nom : "));
        nom.setFont(police);
        nom.setPreferredSize(new Dimension(150, 30));
        nom.setForeground(Color.BLACK);
        container.add(nom);

        container.add(new JLabel("Prenom : "));
        prenom.setFont(police);
        prenom.setPreferredSize(new Dimension(150, 30));
        prenom.setForeground(Color.BLACK);
        container.add(prenom);

        container.add(new JLabel("Tel : "));
        tel.setFont(police);
        tel.setPreferredSize(new Dimension(150, 30));
        tel.setForeground(Color.BLACK);
        container.add(tel);

        container.add(new JLabel("Adresse : "));
        adresse.setFont(police);
        adresse.setPreferredSize(new Dimension(150, 30));
        adresse.setForeground(Color.BLACK);
        container.add(adresse);

        container.add(new JLabel("Mutuelle : "));
        mutuel.setFont(police);
        mutuel.setPreferredSize(new Dimension(150, 30));
        mutuel.setForeground(Color.BLACK);
        container.add(mutuel);

        JPanel truc = new JPanel();
        truc.add(container);
        truc.add(save);
        truc.add(retour);
        zFen.setContentPane(truc);
        zFen.setVisible(true);

        save.addActionListener(new ActionListener() {
            public @Override
            /**
             * Methode qui vérifie si le formualire est validé 
             * Ajoute les modifications dans la base de données 
             */
            void actionPerformed(ActionEvent e) {
                donnees.clear();
                if (!(nom.getText()).equals("") && nom.getText().matches("[a-zA-Z]+")) {
                    donnees.add(nom.getText());
                } else {
                    zFen.messageErreur("nom");
                }
                if (!(prenom.getText()).equals("") && prenom.getText().matches("[a-zA-Z]+")) {
                    donnees.add(prenom.getText());
                } else {
                    zFen.messageErreur("prÃ©nom");
                }
                donnees.add(tel.getText());
                donnees.add(adresse.getText());
                donnees.add(mutuel.getText());

                try {
                    if (donnees.size() == 5) {
                        if (hop.updateMalade(donnees)) {
                            zFen.accueil();
                        } else {
                            JOptionPane.showMessageDialog(null, "Ce malade n'est pas dans nos rÃ©pertoires");
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ModifierPatient.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );

        retour.addActionListener(new ActionListener() {
            /**
             * Après l'appui du bouton "Retour Ã  l'accueil"
             * Lance l'accueil
             * @param e 
             */
            public @Override
            void actionPerformed(ActionEvent e) {
                zFen.accueil();
            }
        });
    }
}
