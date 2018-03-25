/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.gmerty.adventura.logika;





/*******************************************************************************
 * Instances of class {@code Vec} represent ...
 *
 * @author  Iuliia Loseeva
 * @version 20.12.2017
 */
public class Vec
{
    //== CONSTANT CLASS ATTRIBUTES =============================================
    //== VARIABLE CLASS ATTRIBUTES =============================================

    private String nazev;
    private boolean prenositelnost;

    /**
     * Vytvoření věci se zadaným popisem
     *
     * @param nazev věci, je-li věc přenositelná 
     */
    public Vec(String nazev, boolean prenositelnost)
    {
        this.nazev=nazev;
        this.prenositelnost=prenositelnost;
    }



    //== ABSTRACT METHODS ======================================================
    //== INSTANCE GETTERS AND SETTERS ==========================================
    /**
     *  Metoda vrací nazev věci
     *  
     *@return nazev veci
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     *  Metoda vrací je-li věc přenositelna a da se ji vzit do batohu.
     *  
     *@return boolean true - přenositelna, false - nelze ji vzit do batohu
     */
    public boolean jePrenositelna() {
        return prenositelnost;
    }
    
    /**
     *  Metoda nastaví je-li věc přenositelna a da se ji vzit do batohu.
     *  
     *@param boolean true - přenositelna, false - nelze ji vzit do batohu
     */
    public void setPrenositelnost(boolean in) {
        prenositelnost = in;
    }
    
    @Override
    public String toString() {
    	return getNazev();
    
    }
    
}
