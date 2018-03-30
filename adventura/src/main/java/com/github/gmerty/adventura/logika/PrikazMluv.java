/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.gmerty.adventura.logika;



/*******************************************************************************
 * Instance třídy PrikazMluv implementuje pro hru příkaz mluvit s postavou.
 *
 * @author    Iuliia Loseeva
 * @version   20.12.2017
 */
public class PrikazMluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================

    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    private boolean promluvil = false;
    
     /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */ 
    public PrikazMluv(HerniPlan plan) {
        this.plan=plan;
    }

    
    /**
     *  Provádí příkaz "mluv". Čte jmeno postavy se kterou hrač chcé mluvit.
     *  
     *@param čté název přikazu a jmeno postavy
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        String ret = "";
        if (parametry.length == 0) {
            ret = "S kym chcete mluvit? Musite zadat jmeno postavy.";
        }

        String jmenoPostavy = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Postava mluvci = aktualniProstor.najdiPostavu(jmenoPostavy);
        Prostor viteznyProstor = plan.getViteznyProstor();

        if (mluvci != null) {
            if (mluvci.mluvi()) {
                if (jmenoPostavy.equals("pan_Direktor") || jmenoPostavy.equals("pan_Hardy") || jmenoPostavy.equals("pan_Church")) {
                    plan.setCekaNaOdpoved(true); 
                    ret = "Postava " + jmenoPostavy + " řiká: " + mluvci.getProslov() + "\nPro odpověď napiště příkaz odpoved a co chcete řict.";
                
                }
                else if (jmenoPostavy.equals("pan_Noks") && !viteznyProstor.jeZamcen()) {
                    plan.setCekaNaOdpoved(true); 
                    plan.setPostavaNoks(true);
                    ret = "Postava " + jmenoPostavy + " řiká: Chceš abych šel s tebou do Bletchley Parku?" + "\nPro odpověď napiště příkaz odpoved a co chcete řict.";
                }
                else if (jmenoPostavy.equals("pan_Churchill")) {
                    plan.setCekaNaOdpoved(true); 
                    plan.setPostavaChurchill(true);
                    ret = "Postava " + jmenoPostavy + " řiká: " + mluvci.getProslov() + "\nPro odpověď napiště příkaz odpoved a co chcete řict.";
                
                }                
                else {
                    ret = "Postava " + jmenoPostavy + " řiká: " + mluvci.getProslov();
                }
            }
            else {
                ret = "S nim nemužete mluvit.";
            }
        }
        else {
            ret = "On tu není.";
        }
        return ret;
    }

    /**
     *  Metoda vrací nazev příkazu
     *  
     *@return nazev příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
    
    /**
     *  Metoda vrací indikator, jestli konkretní postava něco řekla
     *  
     *@return true - řečeno, false - nemluvila
     */
    public boolean getPromluvil(){
        return promluvil;
    }
    
    //== Soukromé metody (instancí i třídy) ========================================

}
