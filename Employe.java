/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava2018;

/**
 *
 * @author camille
 */
public class Employe {
    protected int numero, tel ;
    protected String nom, prenom, adresse ;
    
    public Employe(int numero, int tel, String nom, String prenom, String adresse)
    {
        this.numero = numero ;
        this.tel = tel ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.adresse = adresse ;
    }
    
}
