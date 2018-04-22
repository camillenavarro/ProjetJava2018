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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Camille,Rim,Roman
 */
public class ModifierEmploye {
    private JTextField nom = new JTextField("");
    private JTextField prenom = new JTextField("");
    private JTextField adresse = new JTextField("");

    private JFormattedTextField salaire = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tel = new JFormattedTextField(NumberFormat.getIntegerInstance());

    private JLabel prof = new JLabel("Profession");
    private JRadioButton doc = new JRadioButton("Docteur");
    private JRadioButton inf = new JRadioButton("Infirmier");

    private ButtonGroup bg = new ButtonGroup();


    private JComboBox rot = new JComboBox();
    private JLabel Rot = new JLabel("Rotation");

    private JPanel container = new JPanel();

    private JButton save = new JButton("Enregistrer");

    private ArrayList<String> donnees = new ArrayList();

    private JButton retour = new JButton("Retour à  l'accueil");
    private Hopital hop = new Hopital();

    /**
     * Constructuer de la classe Elle fait une nouvelle fenetre
     *
     * @param zFen
     */
    public ModifierEmploye(ZFenetre zFen) {

        Font police = new Font("Arial", Font.BOLD, 14);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(new JLabel("Nouvel Employé"));

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

        container.add(prof);
        bg.add(doc);
        bg.add(inf);
        doc.addActionListener(new StateListener());
        inf.addActionListener(new StateListener());
        container.add(doc);
        container.add(inf);

        container.add(Rot);
        container.add(rot);

        rot.setPreferredSize(new Dimension(100, 20));
        rot.addItem("JOUR");
        rot.addItem("NUIT");

        container.add(new JLabel("Salaire : "));
        salaire.setFont(police);
        salaire.setPreferredSize(new Dimension(150, 30));
        salaire.setForeground(Color.BLACK);
        container.add(salaire);

        container.add(save);
        JPanel truc = new JPanel();
        truc.add(container);
        truc.add(save);
        truc.add(retour);
        zFen.setContentPane(truc);
        zFen.setVisible(true);

        save.addActionListener(new ActionListener() {
            /**
             * Cette methode vérifie la validité du formulaire appres avoir
             * appuyé sur le bouton "Enregistrer"
             *
             * @param e
             */
            public @Override
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
                    zFen.messageErreur("prénom");
                }
                donnees.add(tel.getText());
                donnees.add(adresse.getText());
                
                if (doc.isSelected() == true) {
                    
                    donnees.add("doc");
                    if (donnees.size() == 5) {
                        try {
                            if(hop.updateEmploye(donnees))
                            {
                                System.out.println("hop");
                                zFen.accueil();
                            }
                            else
                                JOptionPane.showMessageDialog(null, "Ce docteur n'est pas dans nos répertoires");
                            
                        } catch (SQLException ex) {
                            System.out.println("SQL problem");
                        }
                    }

                } 
                else {
                    if (inf.isSelected() == true) {
                        donnees.add("inf");
                        donnees.add(rot.getSelectedItem().toString());
                        donnees.add(salaire.getText());
                        if (donnees.size() == 7) {
                            try {
                                if(hop.updateEmploye(donnees))
                                    zFen.accueil();
                                else
                                    JOptionPane.showMessageDialog(null, "Cet infirmier n'est pas dans nos répertoires");
                            } catch (SQLException ex) {
                                System.out.println("SQL problem");
                            }
                        }
                    } else {
                        zFen.messageErreur("profession");
                    }
                }

            }
        }
        );

        retour.addActionListener(new ActionListener() {
            /**
             * Lance l'accueil après avoir appuyé sur le bouton "Retour à
             * l'accueil"
             *
             * @param e
             */
            public @Override
            void actionPerformed(ActionEvent e) {
                zFen.accueil();
            }
        });
    }

    /**
     * Cette méthode permet de savoir ce quil se passe quand on chosie Docteur
     * ou Infirmier Si on choisi un docteur on ne peut pas rentrer le salaire ou
     * le rotation Si on choisi un infirmier on ne peut pas choisir la
     * spécialisation
     */
    private class StateListener implements ActionListener {

        public @Override
        void actionPerformed(ActionEvent ae) {

            if (ae.getSource() == doc) {
                rot.setEnabled(false); /// un docteur ne peut pas avoir de rotation
                salaire.setEnabled(false); /// on ne choisit pas le salaire d'un docteur 
                
            }
            if (ae.getSource() == inf) {
                rot.setEnabled(true); /// un infirmier a une rotation
                salaire.setEnabled(true); /// on choisit le salaire d'un infirmier
               
            }

        }
    }

}
