/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;
import Vue.*;
import Modele.*;
import java.awt.*;
import java.awt.event.* ;
import javax.swing.*;
/**
 *
 * @author camille
 */
public class NPListener implements ActionListener {
    private static NouveauPatient np = new NouveauPatient() ;
    private static Hopital hop = new Hopital() ;
    private static ZFenetre zFen = new ZFenetre() ;
    
    public NPListener()
    {
        np.getSave().addActionListener(this);
        np.getRetour().addActionListener(this) ;
    }
    
    public @Override void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == np.getSave())
        {
            if (np.getDonnees().size() == 8)
            {
                System.out.println("OUI");
                hop.nouveauPatient(np.getDonnees());
                zFen.accueil();
            }
            else
            {
                System.out.println("NON");
                JTextArea erreur = new JTextArea("Formulaire non valide !") ;
                //faire un truc
            }
        }
        if(e.getSource() == np.getRetour())
        {
            zFen.accueil();
        }
    }
}
