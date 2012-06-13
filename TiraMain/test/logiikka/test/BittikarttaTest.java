/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.test;

import logiikka.Bittikartta;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * Nämä testit olivat todella tärkeitä, sillä alkuperäisen A-star materiaalin
 * RGB-arvot olivat väärin!
 */
public class BittikarttaTest {

    Bittikartta musta;
    Bittikartta keltainen;
    Bittikartta punainen;
    Bittikartta valkoinen;
    Bittikartta vihrea;
    Bittikartta labyrintti;
    Bittikartta lila;
    Bittikartta pinkki;
    int korkeus;
    int leveys;

    /**
     * Luodaan konstruktorissa, turha luoda setUpissa aina uudestaan ennen
     * jokaista testiä.
     */
    public BittikarttaTest() {
        labyrintti = new Bittikartta("ekaLaby.png");
        musta = new Bittikartta("labyMusta.png");
        valkoinen = new Bittikartta("labyValkoinen.png");
        keltainen = new Bittikartta("labyKeltanen.png");
        punainen = new Bittikartta("labyPunainen.png");
        vihrea = new Bittikartta("labyvihrea.png");
        lila = new Bittikartta("labyLila.png");
        pinkki = new Bittikartta("labyPinkki.png");
        korkeus = punainen.getLabyrintti().length;
        leveys = punainen.getLabyrintti()[0].length;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void keltainenVariToimii() {
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                assertTrue(keltainen.getLabyrintti()[j][i] == 'A');
            }
        }
    }

    @Test
    public void pinkkiVariToimii() {
        assertTrue("" + pinkki.getKuva().getRGB(5, 5), pinkki.getKuva().getRGB(5, 5) == -20791);
    }

    @Test
    public void lilaVariToimii() {
        assertTrue("" + lila.getKuva().getRGB(5, 5), lila.getKuva().getRGB(5, 5) == -6075996);
    }

    @Test
    public void punainenVariToimii() {
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                assertTrue(punainen.getLabyrintti()[j][i] == 'L');
            }
        }
    }

    @Test
    public void mustaVariToimii() {
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                assertTrue(musta.getLabyrintti()[j][i] == '#');
            }
        }
    }

    @Test
    public void valkoinenVariToimii() {
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                assertTrue(valkoinen.getLabyrintti()[j][i] == '.');
            }
        }
    }

    @Test
    public void vihreaVariToimii() {
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                assertTrue(vihrea.getLabyrintti()[j][i] == 'S');
            }
        }
    }

    @Test
    public void labyrintistaLoytyyKaikkiaMerkkejaOikeassaSuhteessa() {
        int punaisia = 0;
        int mustia = 0;
        int valkoisia = 0;
        int keltaisia = 0;
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                if (labyrintti.getLabyrintti()[j][i] == 'A') {
                    keltaisia++;
                } else if (labyrintti.getLabyrintti()[j][i] == 'L') {
                    punaisia++;
                } else if (labyrintti.getLabyrintti()[j][i] == '#') {
                    mustia++;
                } else {
                    valkoisia++;
                }
            }

        }
        assertEquals("ois ollu: " + punaisia + "," + keltaisia, punaisia, keltaisia);
        assertTrue("ois ollu: " + mustia + "," + punaisia, mustia > punaisia);
        assertTrue(valkoisia > mustia);
    }
}