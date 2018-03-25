/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.gmerty.adventura.logika;



/*******************************************************************************
 * Třída Postava - popisuje jednotlivé postavy hry
 * 
 * Každa postava něco mluví a může komunikovat s hračen, davá otazky a čeká na odpovědi.
 *
 * @author  Iuliia Loseeva
 * @version 20.12.2017
 */
public class Postava
{
    //== CONSTANT CLASS ATTRIBUTES =============================================
    //== VARIABLE CLASS ATTRIBUTES =============================================

    private String jmeno;
    private String proslov;
    private boolean mluvi;
    private Prostor aktProstor;
    private boolean jeVidet = true;
    
    //##########################################################################
    //== CONSTUCTORS AND FACTORY METHODS =======================================

    /**
     * Vytvoření postavy, postava má jmeno, proslov a indikator jestli mluví, 
     * dá se nastavit aktualní prostor, ve kterém postava je a jestli je viditelná.   * 
     * 
     *
     * @param jmeno, proslov a indikator jestli mluví
     * 
     */
    public Postava(String jmeno, String proslov, boolean mluvi)
    {
        this.jmeno=jmeno;
        this.proslov=proslov;
        this.mluvi=mluvi;
       
    }

    //== ABSTRACT METHODS ======================================================
    
    
    //== INSTANCE GETTERS AND SETTERS ==========================================
    /**
     * Vrací jmeno postavy (bylo zadáno při vytváření postavy jako parametr
     * konstruktoru)
     *
     * @return jmeno postavy
     */
    public String getJmeno() {
        return jmeno;
    }
    
    /**
     * Vrací proslov, co postava řiká (byl zadán při vytváření postavy jako parametr
     * konstruktoru)
     *
     * @return proslov
     */
    public String getProslov() {
        return proslov;
    }
    
    /**
     * Nastaví proslov, co postava řiká
     *
     * @param proslov
     */
    public void setProslov(String veta) {
        proslov = veta;
    }
    
    /**
     * Nastaví mluví-li postava
     *
     * @return true - mluví, false - nemluví, nemá proslov
     */
    public boolean mluvi() {
        return mluvi;
    }
    
    /**
     * Nastaví aktualní prostor, kde postava je 
     *
     * @param aktualní prostor 
     */
    public void setAktProstor(Prostor prostor) {
        aktProstor = prostor;
    }
    
    /**
     * Vrací aktualní prostor postavy, kde stojí, da se nastavit pomoci setu
     *
     * @return aktualní prostor
     */
    public Prostor getAktProstor() {
        return aktProstor;
    }
    
    /**
     * Nastaví je-li postava viditelná 
     *
     * @param true - je vidět, false - neviditelná
     */
    public void setJeVidet(boolean in) {
        jeVidet = in;
    }
    
    /**
     * Vrací je-li postava viditelná 
     *
     * @return true - je vidět, false - neviditelná
     */
    public boolean getJeVidet() {
        return jeVidet;
    }
    
    @Override
    public String toString() {
    	return getJmeno();
    
    }
    
}