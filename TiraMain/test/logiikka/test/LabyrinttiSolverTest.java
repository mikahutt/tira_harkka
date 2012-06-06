package logiikka.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.PriorityQueue;
import logiikka.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tietorakenteet.Keko;

/**
 *
 * @author MH
 */
public class LabyrinttiSolverTest {
    private LabyrinttiSolver solveri;
    private Koordinaatti[][] koordinaatit;

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
        //Bittikartta mappi = new Bittikartta("ekaLaby.tif");
        //Labyrintti laby = new Labyrintti(mappi.getLabyrintti());
        solveri = new LabyrinttiSolver(laby);
        koordinaatit = new Koordinaatti[laby.labyrintinKorkeus()][laby.labyrintinLeveys()];
        solveri.koordinaattienAlustus(koordinaatit);
    }

    @After
    public void tearDown() {
    }
    
//    @Test
//    public void keltanenOnKeltasta(){
//        for (int i = 0; i < koordinaatit.length; i++) {
//            for (int j = 0; j < koordinaatit[0].length; j++) {
//                assertTrue(koordinaatit[j][i].getMerkki() == 'A');
//                
//            }
//            
//        }
//    }
    
//    @Test
//    public void oikeenKokoinenLabyrintti() {
//        assertTrue("Oli oikeesti" + koordinaatit.length,koordinaatit.length == 318);
//        assertTrue("Oli oikeesti" + koordinaatit[0].length,koordinaatit[0].length == 436);
//    }
    
    @Test
    public void koordinaattienAlustuksenAloituspaikkaToimii() {
        assertTrue("Oli oikeesti: " + koordinaatit[1][1].getMerkki(),koordinaatit[1][1].getMerkki() == 'A');
    }
    
//    @Test
//    public void mustaOnRisuaitaa() {
//        assertEquals('#', koordinaatit[31][31].getMerkki());
//    }
    
    @Test
    public void koordinaattienAlustusJaAloituksenPainoarvo() {
        assertTrue(koordinaatit[1][1].getPainoarvo() == 0);
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
    
    /**
     * Tässä päivitetään koordinaatti-taulukon koordinaattia (3,3). Koska oikeanlaiset kutsut hoidetaan 
     * dijkstran-algoritmin sisällä, niin tässä testissä voidaan asettaa (1,1) olemaan koordinaatin (3,3) vierus.
     * Silloin päivitetyksi arvoksi tulee 0+1 joka on aloituksen painoarvo plus liikkuminen.
     */
    
    @Test
    public void relaksoidaanJotainKoordinaattia() {
        int painoarvo = koordinaatit[3][3].getPainoarvo();
        //PriorityQueue<Koordinaatti> valekeko = new PriorityQueue<Koordinaatti>(1, new KoordinaattiComparator());
        Keko valekeko = new Keko();
        Koordinaatti naatti = koordinaatit[1][1];
        solveri.relaksoi(valekeko, koordinaatit,naatti , 3, 3);
        assertTrue(koordinaatit[3][3].getPainoarvo() < painoarvo);
        assertTrue(koordinaatit[3][3].getPainoarvo() == 1);
    }

    @Test
    public void dijkstranAlgoritmiPalauttaaJotainJarkevaa() {
        assertTrue("ois ollu: " + solveri.dijkstra(),solveri.dijkstra() == 10);
    }
    

}