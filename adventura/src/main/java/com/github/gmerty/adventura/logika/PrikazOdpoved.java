/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.gmerty.adventura.logika;

import java.util.Observable;

/*******************************************************************************
 * Instance třídy PrikazOdpoved implementuje pro hru příkaz odpovědi na otázky od postavy.
 *
 * @author    Iuliia Loseeva
 * @version   30.12.2017
 */
public class PrikazOdpoved extends Observable implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================

    private static final String NAZEV = "odpoved";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */ 
    public PrikazOdpoved(HerniPlan plan) {
        this.plan = plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     *  Provádí příkaz "odpoved". Čte odpověď hrače na otázku,
     *  odpověď může být jen ano nebo ne.
     *  
     *@param čté název přikazu a odpověď na otázku
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        String ret = "";
        if (parametry.length == 0) {
            ret = "Musite napsat co chcete řict, tady očekaváná odpoveď ano nebo ne.";
        }

        String odpoved = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Postava viteznaPostava = aktualniProstor.najdiPostavu("pan_Noks");
        Prostor viteznyProstor = plan.getViteznyProstor();
        Postava panChurchill = aktualniProstor.najdiPostavu("pan_Churchill");
        Batoh batoh = plan.getBatoh();
        Vec vec = aktualniProstor.najdiVec("informace_od_polaku");

        if (odpoved.equals("ano") && plan.getCekaNaOdpoved() && plan.getPostavaNoks()) {
            plan.setCekaNaOdpoved(false);
            viteznyProstor.vlozPostavu(viteznaPostava);
            aktualniProstor.odeberPostavu("pan_Noks");
            viteznaPostava.setAktProstor(plan.getViteznyProstor());
            plan.setPostavaNoks(false);
            panChurchill.setJeVidet(true);
            setChanged();
            notifyObservers();
            //aktualniProstor.vlozVec()
            ret = "Pan Noks přejde do Bletchley Parku" + "\n" + aktualniProstor.dlouhyPopis();
        }        
        else if (odpoved.equals("ano") && plan.getCekaNaOdpoved() && plan.getPostavaChurchill()) {
            plan.setCekaNaOdpoved(false);
            //viteznyProstor.vlozPostavu(viteznaPostava);
            //aktualniProstor.odeberPostavu(viteznaPostava);
            //viteznaPostava.setAktProstor(plan.getViteznyProstor());
            plan.setPostavaChurchill(false);
            vec.setPrenositelnost(true);
            batoh.pridejVec(vec);
            //panChurchill.setJeVidet(true);
            aktualniProstor.odeberVec("informace_od_polaku");
            setChanged();
            notifyObservers();
            ret = "Pan Churchill dal Vám informace od polaku pro prolom Enigmy v Bletchley Parku" + "\n" + aktualniProstor.dlouhyPopis();
        }        
        else if (odpoved.equals("ano") && plan.getCekaNaOdpoved()) {
            plan.setCekaNaOdpoved(false);
            ret = "Ted musite odpovedet na otazku:" + plan.otazka();
        }
        else if (odpoved.equals("ne") && plan.getCekaNaOdpoved()) {
            plan.setCekaNaOdpoved(false);
            ret = "Tak nic.";
        } 
        else if (!plan.getCekaNaOdpoved()) {
            ret = "Nemáte na co odpovědět.";
        } else {
            ret = "Musite napsat ano nebo ne.";
        }
        return ret;
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
    
    
    //== Soukromé metody (instancí i třídy) ========================================

}
