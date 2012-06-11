/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet.test;

import logiikka.Koordinaatti;
import org.junit.*;
import tietorakenteet.OmaArrayList;
import static org.junit.Assert.*;

/**
 *
 * @author MH
 */
public class OmaArrayListTest {

    private OmaArrayList taulu;
    private OmaArrayList taulu2;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        taulu = new OmaArrayList(300);
        taulu2 = new OmaArrayList();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void parametritonKonstruktori() {
        assertEquals(100, taulu2.taulukonKoko());
        assertEquals(100, taulu2.kuinkaMontaMahtuu());
    }

    @Test
    public void tyhjaanTaulukkoonMahtuuOikeaMaara() {
        assertEquals(300, taulu.kuinkaMontaMahtuu());
    }

    @Test
    public void puolillaanOlevaanTaulukkoonMahtuuVielaPuolet() {
        for (int i = 0; i < 150; i++) {
            taulu.lisaa(i, new Koordinaatti(1, 1, 1, 'd'));
        }
        assertEquals(150, taulu.kuinkaMontaMahtuu());
    }

    @Test
    public void tayteenTaulukkoonEiMahduEnaa() {
        for (int i = 0; i < 300; i++) {
            taulu.lisaa(i, new Koordinaatti(1, 1, 1, 'd'));
        }
        assertEquals(0, taulu.kuinkaMontaMahtuu());
    }

    @Test
    public void tuplattuunTaulukkoonMahtuu() {
        for (int i = 0; i < 500; i++) {
            taulu.lisaa(i, new Koordinaatti(1, 1, 1, 'd'));
        }
        assertEquals(100, taulu.kuinkaMontaMahtuu());
    }

    @Test
    public void lisaaminenToimii() {
        for (int i = 0; i < 1000; i++) {
            Koordinaatti k = new Koordinaatti(1, 1, 1, 'k');
            taulu.lisaa(i, k);
        }
        assertEquals(taulu.taulukonKoko(), 1200);
    }

    @Test
    public void lisaaminenToimiiRajaArvoilla() {
        for (int i = 0; i < 1200; i++) {
            Koordinaatti k = new Koordinaatti(1, 1, 1, 'k');
            taulu.lisaa(i, k);
        }
        assertEquals(taulu.taulukonKoko(), 1200);
    }

    @Test
    public void lisaaminenToimiiRajaArvoaYhtaSuuremmalla() {
        for (int i = 0; i < 1201; i++) {
            Koordinaatti k = new Koordinaatti(1, 1, 1, 'k');
            taulu.lisaa(i, k);
        }
        assertEquals(taulu.taulukonKoko(), 2400);
    }

    @Test
    public void indeksitonLisaaminen() {
        for (int i = 0; i < 500; i++) {
            Koordinaatti k = new Koordinaatti(1, 1, 1, 'f');
            taulu.add(k);
        }
        for (int i = 0; i < 500; i++) {
            assertNotNull("nää indeksit ei menny: " + i, taulu.haeIndeksista(i));

        }
    }
}
