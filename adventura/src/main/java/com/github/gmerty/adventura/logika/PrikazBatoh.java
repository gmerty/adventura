/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.gmerty.adventura.logika;

import java.util.Observable;

/*******************************************************************************
 * Instance třídy PrikazBatoh představují ...
 *
 * @author    Iuliia Loseeva
 * @version   21.12.2017
 */
public class PrikazBatoh extends Observable implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    
    private static final String NAZEV = "batoh";
    private HerniPlan plan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazBatoh(HerniPlan plan)
    {
        this.plan=plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    
    /**
     *  Provádí příkaz "batoh". Vypisuje seznam věci v batohu.
     *
     *@param nemá, přečte jen nazev príkazu.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            Batoh batoh = plan.getBatoh();
            setChanged();
            notifyObservers();
            return batoh.vBatohu();
            
        }
        else {
            return "Tento príkaz nepotřebuje parametry.";
        }

    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
    

    //== Soukromé metody (instancí i třídy) ========================================

}
