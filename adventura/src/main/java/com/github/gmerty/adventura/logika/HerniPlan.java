package com.github.gmerty.adventura.logika;

import java.util.Observable;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory, věci a postavy
 *  propojuje prostory vzájemně pomocí východů, přiřadí každe věci a postavě prostor ve kterém má být 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Iuliia Loseeva
 *@version    20.12.2017
 */
public class HerniPlan extends Observable{
    
    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private Postava viteznaPostava;
    private String viteznaVec;
    private Batoh batoh;
    private boolean cekaNaOdpoved = false;
    private boolean postavaNoks = false;
    private boolean postavaChurchill = false;
    private int pokusy = 0;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Vytváří věci a postavy, přiřadi každé věci a postavě prostor ve kterém ona má bát. 
     *  Jako výchozí aktuální prostor nastaví Londyn.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor Londyn = new Prostor("Londyn", "tady je dům příbuznych, ve kterém Alan bydlil do nástupu do Šerbornu");
        Prostor Serborne = new Prostor("Serborne", "je to základní škola pro chlapce, Alan nastoupí tam ve věku 13 let. Tam se setkal s Kristoferem Morkmonem a dostá potvrzení o středoškolském vzdělání od pana direktora.");
        Prostor Kembridge = new Prostor("Kembridge","King's College, do kterého Alan nastoupil po škole a kde se učil u profesora Hardy.");
        Prostor Princeton = new Prostor("Princeton","je to univerzita na které Alan studujé kryptografie a učí se od pána Chorcha a pána Witgenštejna.");
        Prostor BletchleyPark = new Prostor("Bletchley_Park","je to hlavní místo kde Britské vědci pokouší prolomit syfru Enigmy.");
       
        // zamykají se nedostupné prostory
        
        Kembridge.zamknout(true);
        Princeton.zamknout(true);
        BletchleyPark.zamknout(true); 
        
        // nastavíme výpisy pro zamčené prostory
        Kembridge.setZamcenoVypis("musite nejdřiv dostat středoškolské vzděláni!");
        Princeton.setZamcenoVypis("musite nejdřiv dosahnout titula bakalaře!");
        BletchleyPark.setZamcenoVypis("musite nejdřiv dostat doktorský titul!"); 
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        Londyn.setVychod(Serborne);
        Londyn.setVychod(Kembridge);
        Londyn.setVychod(Princeton);
        Londyn.setVychod(BletchleyPark);
        Serborne.setVychod(Londyn);
        Serborne.setVychod(Kembridge);
        Kembridge.setVychod(Londyn);
        Kembridge.setVychod(Princeton);
        Princeton.setVychod(Londyn);
        Princeton.setVychod(BletchleyPark);
        BletchleyPark.setVychod(Londyn);
        BletchleyPark.setVychod(Princeton);
        
        batoh = new Batoh();
        
        Vec sSVzdelani = new Vec("SS_Diploma",false);
        Vec bakalarzskyTitul = new Vec("Diplom_bakalare",false);
        Vec doktorskyTitul = new Vec("Diplom_doktora",false);
        Vec clanek = new Vec("The_Entscheidungsproblem",true);
        Vec polskeSifry = new Vec("informace_od_polaku",false);
        
        Kembridge.vlozVec(bakalarzskyTitul);
        Serborne.vlozVec(sSVzdelani);
        Princeton.vlozVec(doktorskyTitul);
        Londyn.vlozVec(clanek);
        Londyn.vlozVec(polskeSifry);
        
        Postava panDirektor = new Postava("pan_Direktor", "Dobrý den, Alan, chceš udělat zkoušku?", true);
        Postava panHardy = new Postava("pan_Hardy", "Dobrý den, Alan, napsal jsi bakalařskou práce?", true);
        Postava panChurch = new Postava("pan_Church", "Dobrý den, Alan, napsal jsi doktořskou práci?", true);
        Postava panNoks = new Postava("pan_Noks", "Dobrý den, Alan.", true);
        Postava panChurchill = new Postava("pan_Churchill", "Dobrý den, Alan, potřebuješ něco?", true);
                
        Kembridge.vlozPostavu(panHardy);
        Serborne.vlozPostavu(panDirektor);
        Princeton.vlozPostavu(panChurch);
        Londyn.vlozPostavu(panNoks);
        Londyn.vlozPostavu(panChurchill);
        
        panChurchill.setJeVidet(false);
        
        viteznyProstor = BletchleyPark;
        viteznaPostava = panNoks;
        viteznaVec = "informace_od_polaku"; // kombinace techto třech věci umožní výhrat hru
        aktualniProstor = Londyn;  // hra začíná v Londýnu       
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       setChanged();
       notifyObservers();
    }
    
    /**
     *  Metoda nastaví zda hra čeká na odpoveď hrače, používá se se při mluvení s postavy, které davají nějakou otázku
     *
     *@param  boolean hodnota, je-li odpověď očekaváná
     */
     public void setCekaNaOdpoved(boolean in) {
        cekaNaOdpoved = in;
    }
    
    /**
     *  Metoda vrací odkaz zda hra čeká na odpoveď hrače, používá při mluvení s postavy, které davají nějakou otázku
     *
     *@return boolean hodnota, je-li odpověď očekaváná
     */
    public boolean getCekaNaOdpoved() {
        return cekaNaOdpoved;
    }
    
    /**
     *  Metoda vraci třidu batoh
     *
     *@return batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }
    
    /**
     *  Metoda vrací zda hrače teď mluví s panem Noksem, používá se při mluvení s panem Noksem, protože on má specialní odpoveď
     *
     *@return  boolean hodnota, je-li mluvicí pan Noks
     */
    public boolean getPostavaNoks () {
        return postavaNoks;
    }
    
    /**
     *  Metoda vrací zda hrače teď mluví s panem Churchillem, používá se při mluvení s panem Churchillem, protože on má specialní odpoveď
     *
     *@return  boolean hodnota, je-li mluvicí pan Churchill
     */
    public boolean getPostavaChurchill () {
        return postavaChurchill;
    }
    
    /**
     *  Metoda nastaví zda hrače teď mluví s panem Noksem, používá se při mluvení s panem Noksem, protože on má specialní odpoveď
     *
     *@param  boolean hodnota, je-li mluvicí pan Noks
     */
    public void setPostavaNoks (boolean in) {
        postavaNoks = in;
    }
    
    /**
     *  Metoda nastaví zda hrače teď mluví s panem Churchillem, používá se při mluvení s panem Churchillem, protože on má specialní odpoveď
     *
     *@param  boolean hodnota, je-li mluvicí pan Churchill
     */
    public void setPostavaChurchill (boolean in) {
        postavaChurchill = in;
    }
    
    /**
     *  Metoda vrací odkaz na vítezný prostor, ve ktetém se hráč právě nachází.
     *
     *@return vítezný prostor
     */
    public Prostor getViteznyProstor() {
        return viteznyProstor;
    }
    
    /**
     *  Metoda vrací kolik pokusu hrač udělal u zkoušek.
     *
     *@return počet pokusu
     */
    public int getPokusy() {
        return pokusy;
    }
    
    /**
     *  Metoda nastaví kolik pokusu hrač udělal u zkoušek.
     *
     *@param počet pokusu
     */
    public void novyPokus() {
        pokusy++;
    }
    
    /**
     *  Metoda vrací jestli je vyplněna podminka vyhry.
     *
     *@return boolean je podminka splněna nebo ne
     */
    public boolean jeVyhra() {
        return (aktualniProstor.equals(viteznyProstor) && batoh.najdiVBatohu(viteznaVec) && viteznaPostava.equals(aktualniProstor.najdiPostavu("pan_Noks")));
    }
    
    /**
     *  Metoda vrací jestli je vyplněna podminka prohry.
     *
     *@return boolean je podminka splněna nebo ne
     */
    public boolean jeProhra() {
        return pokusy>5;
    }
   
    /**
     *  Metoda nastaví zkouškové otazky.
     *
     *@return text otázky
     */
    public String otazka() {
        Prostor prostor = getAktualniProstor();
        String ret = "";
        
        if (prostor.getNazev().equals("Serborne")) {
            setCekaNaOdpoved(true);
            ret = " o kolik procent se zvětší objem válce, jestliže jeho poloměr se zvětší o 10 % a jeho výška o 20 %?"
            +"\nPro odpoved použijte příkaz odpovedZkouska."
            +"\nPak napiště vaší odpoveď ve formě čísla s procentou a čarkoj jako desetinným oddělovačem, misto mezery použivejte podtržitko.";
        }        
        else if (prostor.getNazev().equals("Kembridge")) {
            setCekaNaOdpoved(true);
            ret = " jaká je tato formula: !(A∧B) <=> !A ∨ !B ?"
            +"\nPro odpoved použijte příkaz odpovedZkouska."
            +"\nPak napiště vaší odpoveď bez diakrityky.";
        }        
        else if (prostor.getNazev().equals("Princeton")) {
            setCekaNaOdpoved(true);
            ret = " dešifrujte větu: Dodqh, mvl mhgqlfnd!"
            +"\nPro odpoved použijte příkaz odpovedZkouska."
            +"\nPak napiště vaší odpoveď v cestine bez diakritiky, místo mezery použivejte podtržitko.";
        }
        
        return ret;
    } 
    
   

}
