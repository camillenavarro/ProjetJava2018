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
public class Chambre {
    private int numero, nb_lits ;
    private String code_service, surveillant ;
    private ArrayList<Malade> hospitalises ;
    
    public Chambre(int numero, int nb_lits, String code, String surveillant) 
    {
        this.numero = numero ;
        this.nb_lits = nb_lits ;
        this.code_service = code ;
        this.surveillant = surveillant ;
    }
    
    public void mettreAJour(int numero, int nb_lits, String code, String surveillant) 
    {
        this.numero = numero ;
        this.nb_lits = nb_lits ;
        this.code_service = code ;
        this.surveillant = surveillant ;
    }
    
    public Boolean hospitalisation(Malade mal)
    {
        if(hospitalises.size() == nb_lits)
        {
            return false ;
        }
        hospitalises.add(mal) ;
        return true ;
    }
    
    public Boolean departMalade(Malade mal) 
    {
        return hospitalises.remove(mal) ;
    }
}
