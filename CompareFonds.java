/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Comparator;

/**
 *
 * @author Rim, Camille
 */

//La classe CompareFonds implémente l'interface Comparator<Fonds> pour être utilisée dans la méthode de tri sort de Collections.
public class CompareFonds implements Comparator<Fonds> {
    
    
    public CompareFonds() {}
  
    public Boolean equals(Fonds f1, Fonds f2)
    {
        return f1.getAmount() == f2.getAmount();
    }
    
    public @Override int compare(Fonds f1, Fonds f2)
    {
        if(f1.getAmount() > f2.getAmount())
        {
            return 1 ;
        }
        if(equals(f1, f2))
        {
            return 0 ;
        }
        
        return -1 ;
    }
}
