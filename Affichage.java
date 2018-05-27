/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.ExceptionExistence;
import Modele.*;
import java.util.*;

/**
 *
 * @author Rim, Camille
 */
public class Affichage {
    
    public Affichage(){}
    
    
    
    /* Méthode d'affichage des fonds d'un instrument triés par montant */
    
    public void afficherTriFondsInstrument(Instrument instrument)
    {
        instrument.tri();
        ArrayList<Fonds> fonds = instrument.getStringList();
        for(int i = 0 ; i < fonds.size() ; i++)
        {
            System.out.println("Fonds " + fonds.get(i).getKey() + " de montant " + fonds.get(i).getAmount());
        }
    }
    
    /**
     *
     * @param hmapInstrument
     * @return 
     */
    /*Une méthode qui affiche, pour chaque instrument, 
    sa clé, son nombre total de fonds et la somme totale des montants de ses fonds*/
    
    public List<Double> afficherInstrument(HashMap<String,Instrument> hmapInstrument)
    {
        Instrument instrument;
        ArrayList<Fonds> collection;
        List<Double> list = new ArrayList<>();
        double valeur;
        double somme = 0;
        
        for (HashMap.Entry<String,Instrument> entry : hmapInstrument.entrySet())
        {
            //affichage de la cle
            System.out.println(entry.getKey() + "/" + entry.getValue());
            
            //affichage du nombre total de fonds
            instrument = entry.getValue();
            collection = instrument.getStringList();
            System.out.println("Cet instrument contient " + collection.size() + " fonds.");
            
            //affichage de la somme des fonds
            for(Fonds amount : collection){
                valeur = amount.getAmount();
                somme = somme + valeur; 
            }
            
            System.out.println("La somme est de " + somme + " .");
            list.add(somme);
        }
        
        return list;
    }
    
    public void afficherPourcentage(String key, Portefeuille wallet)
    {
        ArrayList<Double> pourcentage = new ArrayList<>();
        int nbInstruTotal = wallet.getHmapInstru().size();
        
        
        try {
            if(wallet.rechercheFonds(key) != null)
            {
                for(int i = 0 ; i < nbInstruTotal ; i++)
                {
                    Instrument instru = wallet.getHmapInstru().get(i);
                    ArrayList<Fonds> instruList = instru.getStringList();
                    int somme = 0 ;
                    Boolean fondsPlace = false ;
                    
                    for(int j = 0 ; j < instruList.size() ; j++)
                    {
                        somme += instruList.get(j).getAmount();
                        if(instruList.get(j).getKey() == key)
                        {
                            fondsPlace = true;
                        }
                    }
                    
                    if(fondsPlace && somme != 0) 
                        pourcentage.add(wallet.rechercheFonds(key).getAmount()*100/somme) ;
                    
                    System.out.println("Le pourcentage du fonds pour l'instrument " + instru.getKey() + " est de " + pourcentage.get(i));
                }
            }
       
        } 
        catch (ExceptionExistence ex) {
            //ne exception est générée, message d'erreur
            System.out.println(ex.getMessage());
        }
    }
    
    
}