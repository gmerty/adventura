/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.gmerty.adventura.logika;

import java.util.*;

/*******************************************************************************
 * Instance třídy Batoh představují ...
 *
 * @author    Iuliia Loseeva
 * @version   21.12.2017
 */
public class Batoh extends Observable
{
    private int kapacita = 10;
    private Map<String,Vec> seznamVeciVBatohu;
    
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor, vytvaří batoh a nový hashSet se seznamom věci v batohu
     */
    public Batoh()
    {
        //seznamVeciVBatohu = new HashSet<>();
    	seznamVeciVBatohu = new HashMap<String, Vec>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Metoda přidavá věc do batohu, kontroluje zůstatek volných míst.
     *  
     *@param vec
     *@return true = vec je přídana do batohu, false = vec nepřidáná do batohu
     */
    public boolean pridejVec (Vec vec) {
        if (this.volnychMist() > 0) {
            seznamVeciVBatohu.put(vec.getNazev(),vec);
            
            setChanged();
            notifyObservers();
            return true;
        }
        else return false;
    }
    
    /**
     *  Metoda odebírá věc z batohu.
     *  
     *@param nazev veci
     *@return vec = vec je odebraná batohu, null = vec neodebráná z batohu
     */
    public Vec odeberVec (String nazev) {
        Vec hledana = null;
        if (seznamVeciVBatohu.containsKey(nazev)) {
            hledana = seznamVeciVBatohu.get(nazev);
            seznamVeciVBatohu.remove(nazev);
            setChanged();
            notifyObservers();
        } 
        return hledana;
    }
    
    /**
     *  Metoda počitá kolik volných míst zbyvá v batohu.
     *  
     *@return počet volných mist
     */
    public int volnychMist() {
        return kapacita-seznamVeciVBatohu.size();
    }

    /**
     *  Metoda vrací ve formatu textu seznam věcí v batohu
     *  
     *@return seznam věcí ve formě textu
     */
    @SuppressWarnings("rawtypes")
	public String vBatohu () {
        String veci = "V batohu: ";
        Set set = seznamVeciVBatohu.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
        	Map.Entry mentry = (Map.Entry)iterator.next();
        	veci += mentry.getValue() + ", ";
        }        
        return veci;
    }
    
    /**
     *  Metoda hledá věc v batohu
     *  
     *@param nazev věci
     *@return true = vec je nalezená v batohu, false = vec nenalezená
     */
    public boolean najdiVBatohu (String nazev) {
        return seznamVeciVBatohu.containsKey(nazev);
    }
    
    /**
     *  Metoda vrací seznam věci v batohu.
     *  
     *@return seznam věcí v batohu
     */
    public Collection<Vec> getVeciVBatohu() {
		return Collections.unmodifiableCollection(seznamVeciVBatohu.values());
	}
    
    
    
}
