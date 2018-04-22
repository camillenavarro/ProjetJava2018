/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.*;
import Modele.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Class permettant d'afficher les recherches effectuées 
 *
 * @author Camille,Rim,Roman
 */
public class AffichageRecherche {

    private JPanel pan = new JPanel();
    private ArrayList<JTextField> textField = new ArrayList();
    private JPanel saisie = new JPanel();
    private JButton submit = new JButton("Valider");
    private ArrayList<String> liste = new ArrayList<>();
    private Hopital hop = new Hopital();
    private JTable table;

    /**
     * Constructeur de la class AfficheRecherche
     *
     * @param champs
     * @param module
     * @param zFen
     */
    public AffichageRecherche(String[] champs, String module, ZFenetre zFen) {
        //   this.setVisible(false) ;

        saisie.removeAll();

        for (int i = 0; i < champs.length; i++) {
            JTextField jtf = new JTextField("");
            jtf.setPreferredSize(new Dimension(200, 30));
            jtf.setForeground(Color.black);
            textField.add(jtf);
            saisie.add(new JLabel(champs[i]));
            saisie.add(jtf);
        }

        pan.add(saisie);
        pan.add(submit);
        pan.add(submit);
        zFen.setContentPane(pan);
        zFen.setVisible(true);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (module.equals("Malade")) {
                    liste = hop.malade(textField.get(0).getText(), textField.get(1).getText());
                    tableRechercheM(liste, zFen);
                }
                if (module.equals("Infirmier")) {
                    liste = hop.infirmier(textField.get(0).getText(), textField.get(1).getText());
                    tableRechercheI(liste, zFen);
                }
                if (module.equals("Docteur")) {
                    liste = hop.docteur(textField.get(0).getText(), textField.get(1).getText());
                    tableRechercheD(liste, zFen);
                }
                if (module.equals("Service")) {
                    liste = hop.service(textField.get(0).getText());
                    tableRechercheS(liste, zFen);
                }
                if (module.equals("Chambre")) {
                    liste = hop.chambre(textField.get(0).getText(), textField.get(1).getText());
                    tableRechercheC(liste, zFen);
                }
            }
        });
    }

    /**
     * Affiche sous forme d'un tableau les informations des malades recherchés
     *
     * @param liste
     * @param zFen
     */
    public void tableRechercheM(ArrayList liste, ZFenetre zFen) {

        if (liste.size() == 1) {
            JOptionPane.showMessageDialog(null, liste.get(0), "Error", JOptionPane.ERROR_MESSAGE);
        } else if (liste.size() == 6) {
            zFen.setLayout(new FlowLayout());
            String[] columnNames = {"Prenom", "Nom", "ID Patient", "Adresse", "Telephone", "Mutuelle"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(2), liste.get(3), liste.get(4), liste.get(5)}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1000, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            zFen.add(scrollPane);
            zFen.setVisible(true);

        } else {

            zFen.setLayout(new FlowLayout());

            while (liste.size() < 11) {
                liste.add("NA");
            }
            JOptionPane.showMessageDialog(null, "Le patient " + liste.get(0) + " " + liste.get(1) + " est actuellement hospitalisÃ©. Veuillez consulter ses informations.");
            String[] columnNames = {"Prenom", "Nom", "ID Patient", "Adresse", "Telephone", "Mutuelle", "Num. de la chambre d'hospitalisation", "Num. du lit", "Surveillant de la chambre", "Service", "Batiment"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(2), liste.get(3), liste.get(4), liste.get(5), liste.get(6), liste.get(7), liste.get(8), liste.get(9), liste.get(10)}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1200, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            zFen.add(scrollPane);
            zFen.setVisible(true);
        }

        liste.clear();
    }

    /**
     * Affiche sous forme d'un tableau les informations des infirmiers
     * recherchés
     *
     * @param liste
     * @param zFen
     */
    public void tableRechercheI(ArrayList liste, ZFenetre zFen) {

        if (liste.size() == 1) {
            JOptionPane.showMessageDialog(null, liste.get(0));
        } else {

            zFen.setLayout(new FlowLayout());
            String[] columnNames = {"Prenom", "Nom", "ID Employe", "Adresse", "Telephone", "Rotation"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(3), liste.get(4), liste.get(5), liste.get(2)}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1000, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            zFen.add(scrollPane);
            zFen.setVisible(true);
        }
        liste.clear();
    }

    /**
     * Affiche sous forme d'un tableau les informations des doteurs recherchés
     *
     * @param liste
     * @param zFen
     */
    public void tableRechercheD(ArrayList liste, ZFenetre zFen) {

        if (liste.size() == 1) {
            JOptionPane.showMessageDialog(null, liste.get(0));
        } else {
            zFen.setLayout(new FlowLayout());
            String[] columnNames = {"Prenom", "Nom", "ID Employe", "Adresse", "Telephone", "Specialite"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(2), liste.get(3), liste.get(4), liste.get(5)}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1000, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            zFen.add(scrollPane);
            zFen.setVisible(true);
        }
        liste.clear();
    }

    /**
     * Affiche sous forme d'un tableau les informations des services recherchés
     *
     * @param liste
     * @param zFen
     */
    public void tableRechercheS(ArrayList liste, ZFenetre zFen) {

        if (liste.size() == 1) {
            JOptionPane.showMessageDialog(null, liste.get(0));
        } else {
            zFen.setLayout(new FlowLayout());
            String docteur = "Dr. " + liste.get(3) + " " + liste.get(4);
            String[] columnNames = {"Nom du Service", "Code du Service", "Batiment", "Directeur du Service"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(2), docteur}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1000, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            zFen.add(scrollPane);
            zFen.setVisible(true);
        }
        liste.clear();
    }

    /**
     * Affiche sous forme d'un tableau les informations des chambres recherchées
     *
     * @param liste
     * @param zFen
     */
    public void tableRechercheC(ArrayList liste, ZFenetre zFen) {

        if (liste.size() == 1) {
            JOptionPane.showMessageDialog(null, liste.get(0));
        } else {
            zFen.setLayout(new FlowLayout());
            String[] columnNames = {"Numero de la Chambre", "Nombre de lits", "ID surveillant", "Infirmier(e)", "Service de la Chambre"};

            Object[][] data = {
                {liste.get(0), liste.get(1), liste.get(2), liste.get(3), liste.get(4)}
            };

            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(1000, 100));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            zFen.add(scrollPane);
            zFen.setVisible(true);
        }
        liste.clear();
    }

}
