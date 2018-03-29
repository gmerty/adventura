/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.gmerty.adventura.logika;





/*******************************************************************************
 * Instances of class {@code PrikazSeber} represent ...
 *
 * @author  Iuliia Loseeva
 * @version 20.12.2017
 */
public class PrikazSeber implements IPrikaz
{
    //== CONSTANT CLASS ATTRIBUTES =============================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    
    //##########################################################################
    //== CONSTUCTORS AND FACTORY METHODS =======================================

    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit"
    */      public PrikazSeber(HerniPlan plan) {
       this.plan = plan;
    }


    /**
     *  Provádí příkaz "seber". Zkouší se sebrat zadanou vec. Pokud vec
     *  existuje a je sberatelna, sebere ji, pokud je misto v batohu. Pokud zadana vec
     *  není, je nesberatelna nebo chybi misto v batohu, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno veci,
     *                         kterou má sebrat.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí nazev veci, tak ....
            return "Co mam sebrat? Musíš zadat nazev veci";
        }
        
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sbirana = aktualniProstor.najdiVec(nazevVeci);
        Batoh batoh = plan.getBatoh();

        if (sbirana != null) {
            if (sbirana.jePrenositelna()) {
                if (batoh.pridejVec(sbirana)) {
                    aktualniProstor.odeberVec(nazevVeci);
                    return "Sebral si vec " + nazevVeci;
                }
                else {
                    return "To se vám do batohu uz nevejde.";
                }
            }
            else {
                return "To neuzvednete.";
            }
        }
        else {
            return "To tu neni.";
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
}
