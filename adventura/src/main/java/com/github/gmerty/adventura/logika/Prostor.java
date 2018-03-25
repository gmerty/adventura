package com.github.gmerty.adventura.logika;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Iuliia Loseeva
 * @version 20.12.2017
 */
public class Prostor {

    private String nazev;
    private String popis;
    private boolean zamceno;
    private String zamcenoVypis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    //private Set<Vec> veci;
    private Set<Postava> postavy;
    private Map<String, Vec> seznamVeci;
    
    /**
     * Vytvoření prostoru se zadaným popisem, nastavuje je-li prostor zamčen, 
     * přidavá pro každý prostor nové seznamy výhodů, věcí a postav v něm  
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        zamceno = false;
        zamcenoVypis = "";
        vychody = new HashSet<>();
        //veci = new HashSet<>();
        postavy = new HashSet<>();
        seznamVeci = new HashMap<String, Vec>();

        
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        
        return "Jsi v prostoru " + nazev + " " + popis + ".\n"
                + popisVychodu()+ "\n"
                + popisVeci()+ "\n"
                + popisPostav() ;
                
    }
    
    /**
     *  Metoda zamyká nebo odemyká prostor.
     *  
     *@param boolean true - zamknout, false - odemknout
     */
    public void zamknout(boolean zamceno) {
        this.zamceno = zamceno;
    }
    
    /**
     *  Metoda vrací je-li prostor zamčen nebo otevřen.
     *  
     *@return boolean true - zamčen, false - otevřen
     */
    public boolean jeZamcen () {
        return zamceno;
    }

    /**
     *  Metoda nastaví vypis, v případě, jestli je prostor zamčen.
     *  
     *@param string vypis
     */
    public void setZamcenoVypis (String vypis) {
        this.zamcenoVypis=vypis;
    }
    
    /**
     *  Metoda vrací vypis, v případě, jestli je prostor zamčen.
     *  
     *@return string vypis
     */
    public String getZamcenoVypis() {
        return zamcenoVypis;
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
            if (sousedni.jeZamcen()) {
                vracenyText += "(zamknuto)";
            }
        }
        return vracenyText;
    }
    
    /**
     * Vrací textový řetězec, který popisuje seznam věcí v prostoru
     *
     * @return Popis věcí
     */
    private String popisVeci() {
        String vracenyText = "veci:";
        //for (Vec vec : veci) {
        for (String nazevVeci : seznamVeci.keySet()) {
        	vracenyText += " " + nazevVeci;
            //vracenyText += " " + vec.getNazev();
        }
        return vracenyText;
    }
    
    /**
     * Vrací textový řetězec, který popisuje seznam postav v prostoru
     *
     * @return Popis postav
     */
    private String popisPostav() {
        String vracenyText = "postavy:";
        for (Postava postava : postavy) {
            if (postava.getJeVidet())
            vracenyText += " " + postava.getJmeno();
        }
        return vracenyText;
    }
    
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    
    /**
     * Metoda přidává věc do prostoru
     *
     * @param vec
     * @return true - věc přidáná, false - opačně
     */
    public void vlozVec (Vec neco) {
        //return veci.add(neco);
    	seznamVeci.put(neco.getNazev(),neco);
    }
    
    /**
     * Metoda přidává postavu do prostoru
     *
     * @param postava
     * @return true - postava přidáná, false - opačně
     */
    public boolean vlozPostavu (Postava neco) {
        return postavy.add(neco);
    }
    
    /**
     * Metoda hledá věc v prostoru
     *
     * @param nazev věci
     * @return pokud vec v danem prostoru neni, vraci hodnotu null, jinak vrací nalezenou vec
     */
    public Vec najdiVec(String nazevVeci) {
        Vec hledana = null;
        //for (Vec vec: veci) {
        for (String jmenoVeci : seznamVeci.keySet()){
            if (jmenoVeci.equals(nazevVeci)) {
                hledana = seznamVeci.get(jmenoVeci);
                break;
            }
        }
        return hledana;
    }
    
    /**
     * Metoda hledá postavu v prostoru
     *
     * @param jmeno postavy
     * @return pokud postava v danem prostoru neni, vraci hodnotu null, jinak vrací nalezenou postavu
     */
    public Postava najdiPostavu(String jmenoPostavy) {
        Postava hledana = null;
        for (Postava postava : postavy) {
            if (postava.getJmeno().equals(jmenoPostavy)) {
                hledana = postava;
                break;
            }
        }
        return hledana;
    }
    
    /**
     * Metoda odebirá věc z prostoru
     *
     * @param věc
     * @return true - vec smazana z prostoru, false - vec nesmazana
     */
    /*public boolean odeberVec(Vec odebirana) {
        return veci.remove(odebirana);
    }*/
    
    /**
     * Metoda odebirá postavu z prostoru
     *
     * @param postava
     * @return true - postava smazana z prostoru, false - postava nesmazana
     */
    public boolean odeberPostavu(Postava odebirana) {
        return postavy.remove(odebirana);
    }

	public Collection<Vec> getVeci() {
		return Collections.unmodifiableCollection(seznamVeci.values());
	}
	
    @Override
    public String toString() {
    	return getNazev();
    
    }

}
