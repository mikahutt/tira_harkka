/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.PriorityQueue;
import logiikka.LabyrinttiSolver;
import logiikka.Koordinaatti;
import logiikka.KoordinaattiComparator;
import logiikka.Labyrintti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MH
 */
public class LabyrinttiSolverTest {
    LabyrinttiSolver solveri;
    Koordinaatti[][] koordinaatit;

    public LabyrinttiSolverTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        Labyrintti laby = new Labyrintti();
        solveri = new LabyrinttiSolver(laby);
        koordinaatit = new Koordinaatti[laby.labyrintinKorkeus()][laby.labyrintinLeveys()];
        solveri.koordinaattienAlustus(koordinaatit);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void koordinaattienAlustuksenAloituspaikkaToimii() {
        assertTrue(koordinaatit[1][1].getMerkki() == 'A');
    }
    
    @Test
    public void koordinaattienAlustuksenLopetuspaikkaToimii() {
        assertTrue(koordinaatit[4][8].getMerkki() == 'L');
    }
    
    @Test
    public void aloituksenEtsintaToimii() {
        assertTrue(solveri.aloituksenEtsinta(koordinaatit).equals(koordinaatit[1][1]));
    }
    
    @Test
    public void onkoEpaKelpoSeuraajaToimii() {
        assertFalse(solveri.onkoEpaKelpoSeuraaja(2, 3, false, koordinaatit));
        assertTrue(solveri.onkoEpaKelpoSeuraaja(2, 5, true, koordinaatit));
        assertTrue(solveri.onkoEpaKelpoSeuraaja(-1, 5, false, koordinaatit));
        assertTrue(solveri.onkoEpaKelpoSeuraaja(2, 115, true, koordinaatit));
    }
    
    @Test
    public void relaksoidaanJoKaytya() {
        koordinaatit[3][3].setKayty(true);
        int painoarvo = koordinaatit[3][3].getPainoarvo();
        PriorityQueue<Koordinaatti> valekeko = new PriorityQueue<Koordinaatti>(1, new KoordinaattiComparator());
        Koordinaatti naatti = koordinaatit[1][1];
        solveri.relaksoi(valekeko, koordinaatit,naatti , 3, 3);
        assertTrue(koordinaatit[3][3].getPainoarvo() < painoarvo);
    }

    

}