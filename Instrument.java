/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;
import java.util.Collections;
//import static jdk.nashorn.internal.objects.NativeArray.sort;

/**
 *
 * @author Rim, Camille
 */
public class Instrument {
    
    private ArrayList<Fonds> instrument;
    private String key;
    
    public Instrument(String key){
        this.key = key ;
    }
    
    public ArrayList<Fonds> getStringList() {
        return instrument;
    }
    
    public void ajoutFonds(Fonds fonds){
    instrument.add(fonds);
    }
    
    public void tri()
    {
        CompareFonds comparator = new CompareFonds() ;
        Collections.sort(instrument, comparator) ;
    }
    
    public String getKey()
    {
        return this.key;
    }
    
}
