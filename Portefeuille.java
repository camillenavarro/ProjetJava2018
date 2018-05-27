/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Controleur.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Rim, Camille
 */
public class Portefeuille {
    
    public HashMap<String,Fonds> hmapFonds = new HashMap<>() ;
    public HashMap<String,Instrument> hmapInstrument = new HashMap<>();
    
    public Portefeuille(){
    }

    public Fonds rechercheFonds(String key) throws ExceptionExistence {

        // si la cle existe 
        if (hmapFonds.containsKey(key)) {      
           // recuperer la valeur de la hmapFonds associÃ© Ã  la cle passÃ© en param
           Fonds value = hmapFonds.get(key);  
           return value;       
       }
       else{
           throw new ExceptionExistence("Fonds inexistant"); 
       }
    }

    public ArrayList<Fonds> rechercheInstrument(String key) throws ExceptionExistence {

        if (hmapInstrument.containsKey(key)) {
           // si la cle existe
           // recuperer la valeur de la hmapFonds associÃ© Ã  la cle passÃ© en param
            Instrument collection = hmapInstrument.get(key);  
            return collection.getStringList();       
        }
        else{
            throw new ExceptionExistence("Instrument inexistant"); 
        }

    }

    public void ajoutHmapFonds(String key, Fonds fonds){
        hmapFonds.put(key, fonds);
    }
    
    public void ajoutHmapInstrument(String key, Instrument instru){
        hmapInstrument.put(key, instru);
    }

    public void ajoutFondsInstrument(String cle, Fonds fonds) 
    {
        try {
            if(rechercheInstrument(cle) != null)
            {
                 Instrument instru = hmapInstrument.get(cle) ;
                 instru.ajoutFonds(fonds);
            }
        }
        catch(ExceptionExistence e)
        {
            System.out.println(e.getMessage());
        }


    }

    public void supprimerHmapFonds(String cle) 
    {
        try {
            if(rechercheFonds(cle) != null)
            {
                hmapFonds.remove(cle);
            }
        }
        catch(ExceptionExistence e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void supprimerHmapInstrument(String cle) 
    {
        try {
            if(rechercheInstrument(cle) != null)
            {
                ArrayList<Fonds> instrument = hmapInstrument.get(cle).getStringList();
                for(int i = 0 ; i < instrument.size() ; i++)
                {
                    instrument.remove(i);
                }
                hmapInstrument.remove(cle);
            }
        }
        catch(ExceptionExistence e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public HashMap<String,Instrument> getHmapInstru()
    {
        return this.hmapInstrument;
    }

}