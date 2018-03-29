/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.gmerty.adventura.logika;

import java.util.Observable;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/*******************************************************************************
 * Třída PrikazZkouskaOdpoved implementuje pro hru příkaz odpovědi na zkouškovou otázky.
 *
 * @author    Iuliia Loseeva
 * @version   30.12.2017
 */
public class PrikazZkouskaOdpoved extends Observable implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================

    private static final String NAZEV = "odpovedZkouska";
    private HerniPlan plan;
    @FXML private ListView<Vec> seznamVeciVBatohu;
    //private IHra hra;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */ 
    public PrikazZkouskaOdpoved(HerniPlan plan)
    {
        this.plan = plan;
        //this.hra=hra;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     *  Provádí příkaz "odpovedZkouska". Čte odpověď hrače na zkouškovou otázku, 
     *  kontroluje jestli odpovezeno správne. Jestli odpoveď nesprávná přičitá jeden pokus a da se odpovědět znovu.
     *
     *@param čté název přikazu a odpověď na otázku
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        String ret="";
        //HerniPlan plan;
        if (parametry.length == 0) {
            ret = "Tady očekaváná odpoveď.";
        }

        String odpoved = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Batoh batoh = plan.getBatoh();
        Prostor sousedniProstor;
        setChanged();
        notifyObservers();
        
        if (aktualniProstor.getNazev().equals("Serborne") && odpoved.equals("o_45,2_%") && plan.getCekaNaOdpoved()) {
            sousedniProstor = plan.getAktualniProstor().vratSousedniProstor("Kembridge");
            plan.setCekaNaOdpoved(false);
            Vec diploma = aktualniProstor.najdiVec("SS_Diploma");
            diploma.setPrenositelnost(true);
            batoh.pridejVec(diploma);
            aktualniProstor.odeberVec("SS_Diploma");
            sousedniProstor.zamknout(false);
            setChanged();
            notifyObservers();
            //seznamVeciVBatohu.getItems().clear();
            //seznamVeciVBatohu.getItems().addAll(plan.getBatoh().getVeciVBatohu());
            ret = "Odpověděl(a) jste správně, teď dostanete potvrzení o ukončeném SŠ vzdělání";
        }        
        else if (aktualniProstor.getNazev().equals("Kembridge") && odpoved.equals("tautologie") && plan.getCekaNaOdpoved()) {
            sousedniProstor = plan.getAktualniProstor().vratSousedniProstor("Princeton");
            plan.setCekaNaOdpoved(false);
            Vec diploma = aktualniProstor.najdiVec("Diplom_bakalare");
            diploma.setPrenositelnost(true);
            batoh.pridejVec(diploma);
            aktualniProstor.odeberVec("Diplom_bakalare");
            sousedniProstor.zamknout(false);
            setChanged();
            notifyObservers();
            ret = "Odpověděl(a) jste správně, teď dostanete potvrzení o ukončeném VŠ vzdělání";
        }        
        else if (aktualniProstor.getNazev().equals("Princeton") && odpoved.equals("Alane,_jsi_jednicka!") && plan.getCekaNaOdpoved()) {
            sousedniProstor = plan.getAktualniProstor().vratSousedniProstor("Bletchley_Park");
            plan.setCekaNaOdpoved(false);
            Vec diploma = aktualniProstor.najdiVec("Diplom_doktora");
            diploma.setPrenositelnost(true);
            batoh.pridejVec(diploma);
            aktualniProstor.odeberVec("Diplom_doktora");
            sousedniProstor.zamknout(false);
            setChanged();
            notifyObservers();
            ret = "Odpověděl(a) jste správně, teď mužete jit do Bletčley parku.";
        }
        else if (!plan.getCekaNaOdpoved()) {
        	setChanged();
            notifyObservers();
            ret = "Nemáte na co odpovědět."; 
        }
        else {
            plan.novyPokus();
            setChanged();
            notifyObservers();
            ret = "Odpověď není správná, skontrolujte své řešení." 
            +"\nOdpoveď napiště bez diakrityky, čarka je desetinný oddělovač, místo mezery použivejte podtržitko.";
        }
        
        return ret + "\n"+aktualniProstor.dlouhyPopis();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
