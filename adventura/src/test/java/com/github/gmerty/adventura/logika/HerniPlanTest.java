/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.gmerty.adventura.logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HerniPlanTest slouží ke komplexnímu otestování třídy HerniPlan
 *
 * @author    Iuliia Loseeva
 * @version   31.12.2017
 */
public class HerniPlanTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    //== VLASTNÍ TESTY =========================================================
    //
    //     /********************************************************************
    //      *
    //      */
    //     @Test
    //     public void testXxx()
    //     {
    //     }

    @Test
    public void herniPlan()
    {
    	com.github.gmerty.adventura.logika.HerniPlan herniPla1 = new com.github.gmerty.adventura.logika.HerniPlan();
    	com.github.gmerty.adventura.logika.Batoh batoh1 = new com.github.gmerty.adventura.logika.Batoh();
    	com.github.gmerty.adventura.logika.Prostor prostor1 = new com.github.gmerty.adventura.logika.Prostor("A", "prostor A",0,0);
    	com.github.gmerty.adventura.logika.Vec vec1 = new com.github.gmerty.adventura.logika.Vec("va", true);
    	com.github.gmerty.adventura.logika.Vec vec2 = new com.github.gmerty.adventura.logika.Vec("vb", false);
    	com.github.gmerty.adventura.logika.Postava postava1 = new com.github.gmerty.adventura.logika.Postava("pa", "ahoj", true);
        //assertEquals(null, herniPla1.getBatoh());
        assertEquals(0, herniPla1.getPokusy());
        assertEquals(false, herniPla1.jeProhra());
        assertEquals(false, herniPla1.jeVyhra());
        herniPla1.novyPokus();
        assertEquals(1, herniPla1.getPokusy());
        herniPla1.setAktualniProstor(prostor1);
        herniPla1.setCekaNaOdpoved(true);
    }
}

