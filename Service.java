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
public class Service {
    private String code, nom ;
    private char batiment ;
    private int directeur ;
    
    public Service(String code, String nom, char batiment, int directeur) 
    {
        this.code = code ;
        this.nom = nom ;
        this.batiment = batiment ;
        this.directeur = directeur ;
    }
    
    public void mettreAJour(String code, String nom, char batiment, int directeur) 
    {
        this.code = code ;
        this.nom = nom ;
        this.batiment = batiment ;
        this.directeur = directeur ;
    }
    
}
