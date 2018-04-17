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
public class Malade {
    private int numero, tel ;
    private String nom, prenom, adresse, mutuelle ;
    
    public Malade(int numero, int tel, String nom, String prenom, String adresse, String mutuelle)
    {
        this.numero = numero ;
        this.tel = tel ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.adresse = adresse ;
        this.mutuelle = mutuelle ;
    }
    
    public void mettreAJour(int numero, int tel, String nom, String prenom, String adresse, String mutuelle)
    {
        this.numero = numero ;
        this.tel = tel ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.adresse = adresse ;
        this.mutuelle = mutuelle ;
    }
}
