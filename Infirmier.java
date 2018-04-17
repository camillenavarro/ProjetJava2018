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
public class Infirmier extends Employe {
    private int rotation ;
    private String code_service ;
    private double salaire ;
    
    public Infirmier(int numero, int tel, String nom, String prenom, String adresse, int rotation, String code, double salaire)
    {
        super(numero, tel, nom, prenom, adresse) ;
        this.code_service = code ;
        this.rotation = rotation ;
        this.salaire = salaire ;
    }
    
    public void mettreAJour(int numero, int tel, String nom, String prenom, String adresse, int rotation, String code, double salaire)
    {
        this.numero = numero ;
        this.tel = tel ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.adresse = adresse ;
        this.code_service = code ;
        this.rotation = rotation ;
        this.salaire = salaire ;
    }
}
