package logiikka.test;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
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
        //Labyrintti laby = new Labyrintti();
        Bittikartta mappi = new Bittikartta("ekaLaby.png");
        Labyrintti laby = new Labyrintti(mappi.getLabyrintti());
        solveri = new LabyrinttiSolver(laby);
        koordinaatit = new Koordinaatti[laby.labyrintinKorkeus()][laby.labyrintinLeveys()];
        solveri.koordinaattienAlustus(koordinaatit);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void oikeenKokoinenLabyrintti() {
        assertTrue("Oli oikeesti" + koordinaatit.length, koordinaatit.length == 318);
        assertTrue("Oli oikeesti" + koordinaatit[0].length, koordinaatit[0].length == 436);
    }

    @Test
    public void koordinaattienAlustuksenAloituspaikkaToimii() {
        assertTrue("Oli oikeesti: " + koordinaatit[234][290].getMerkki(), koordinaatit[234][290].getMerkki() == 'A');
    }

    @Test
    public void mustaOnRisuaitaa() {
        assertEquals('#', koordinaatit[31][31].getMerkki());
    }

    @Test
    public void koordinaattienAlustusJaAloituksenPainoarvo() {
        assertTrue(koordinaatit[234][290].getPainoarvo() == 0);
    }

    @Test
    public void koordinaattienAlustuksenLopetuspaikkaToimii() {
        assertTrue(koordinaatit[51][300].getMerkki() == 'L');
    }

    @Test
    public void aloituksenEtsintaToimii() {
        assertTrue(solveri.aloituksenEtsinta(koordinaatit).equals(koordinaatit[230][288]));
    }

    @Test
    public void onkoEpaKelpoSeuraajaToimii() {
        assertFalse(solveri.onkoEpaKelpoSeuraaja(2, 3, false, koordinaatit));
        assertTrue(solveri.onkoEpaKelpoSeuraaja(2, 5, true, koordinaatit));
        assertTrue(solveri.onkoEpaKelpoSeuraaja(-1, 5, false, koordinaatit));
        assertTrue(solveri.onkoEpaKelpoSeuraaja(2, 115, true, koordinaatit));
    }

    /**
     * Tässä päivitetään koordinaatti-taulukon koordinaattia (3,3). Koska
     * oikeanlaiset kutsut hoidetaan dijkstran-algoritmin sisällä, niin tässä
     * testissä voidaan asettaa aloituspiste (234,290) olemaan koordinaatin
     * (3,3) vierus. Silloin päivitetyksi arvoksi tulee 0+1 joka on aloituksen
     * painoarvo plus liikkuminen.
     */
    @Test
    public void relaksoidaanJotainKoordinaattia() {
        int painoarvo = koordinaatit[3][3].getPainoarvo();
        Keko valekeko = new Keko();
        Koordinaatti naatti = koordinaatit[234][290];
        solveri.relaksoi(valekeko, koordinaatit, naatti, 3, 3);
        assertTrue("ois ollu: " + koordinaatit[3][3].getPainoarvo(), koordinaatit[3][3].getPainoarvo() < painoarvo);
        assertTrue("ois ollu: " + koordinaatit[3][3].getPainoarvo(), koordinaatit[3][3].getPainoarvo() == 1);
    }

    @Test
    public void dijkstranAlgoritmiPalauttaaOikeanPituuden() {
        assertTrue("ois ollu: " + solveri.dijkstra(), solveri.dijkstra() == solveri.getParasReitti().size()-1);
    }
}