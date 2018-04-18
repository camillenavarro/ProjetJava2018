/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava2018;
import java.util.ArrayList ;
/**
 *
 * @author camille
 */
public class Docteur extends Employe {
    private String specialite ;
    private ArrayList<Malade> patients ;
    
    public Docteur(int numero, int tel, String nom, String prenom, String adresse, String specialite)
    {
        super(numero, tel, nom, prenom, adresse) ;
        this.specialite = specialite ;
    }
    
    public void mettreAJour(int numero, int tel, String nom, String prenom, String adresse, String specialite)
    {
        this.numero = numero ;
        this.tel = tel ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.adresse = adresse ;
        this.specialite = specialite ;
    }
    
    public void soigne(Malade mal)
    {
        patients.add(mal) ;
    }
    
    public Boolean neSoignePlus(Malade mal) 
    {
        return patients.remove(mal) ;
    }
}
