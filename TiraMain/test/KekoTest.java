/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import logiikka.Koordinaatti;
import org.junit.*;
import static org.junit.Assert.*;
import tietorakenteet.Keko;

/**
 *
 * @author MH
 */
public class KekoTest {

    private Keko keko;
    private Koordinaatti koordinaatti;
    private Koordinaatti koordinaatti2;

    public KekoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        keko = new Keko();
        koordinaatti = new Koordinaatti(5, 6, 7, '{');
        koordinaatti2 = new Koordinaatti(1, 2, 3, 'g');
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tyhjassaKeossaEiOleAlkioita() {
        assertTrue(keko.heapSize() == 0);
    }

    @Test
    public void ensimmaisenAlkionLisaaminenKasvattaaAlkioidenMaaranYhteen() {
        keko.heapInsert(koordinaatti);
        assertTrue(keko.heapSize() == 1);
    }

    @Test
    public void kahdenAlkionLisaaminenKasvattaaAlkioidenMaaranKahteen() {
        keko.heapInsert(koordinaatti);
        keko.heapInsert(koordinaatti2);
        assertEquals(keko.heapSize(), 2);
    }

    @Test
    public void yksiKoordinaattiOnKeonHuipulla() {
        keko.heapInsert(koordinaatti);
        assertEquals(keko.heapMin(), koordinaatti);
    }

    @Test
    public void pieninKoordinaattiOnKeonHuipullaLisattynaVikana() {
        keko.heapInsert(koordinaatti);
        keko.heapInsert(koordinaatti2);
        assertEquals(keko.heapMin(), koordinaatti2);
    }

    @Test
    public void pieninKoordinaattiOnKeonHuipullaLisattynaEkana() {
        keko.heapInsert(koordinaatti2);
        keko.heapInsert(koordinaatti);
        assertEquals(keko.heapMin(), koordinaatti2);
    }

    @Test
    public void heapDelMin() {
        keko.heapInsert(koordinaatti2);
        keko.heapInsert(koordinaatti);
        assertEquals(keko.heapSize(), 2);
        assertEquals(keko.heapDelMin(), koordinaatti2);
        assertEquals(keko.heapSize(), 1);
        assertEquals(keko.heapMin(), koordinaatti);
    }

    @Test
    public void lisataanTosiPaljon() {
        Koordinaatti teppo = null;
        for (int i = 1; i < 50; i++) {
            Koordinaatti kaapo = new Koordinaatti(5, 5, i+10, 't');
            if (i == 16) {
                teppo = new Koordinaatti(5,5,2,'t');
                keko.heapInsert(teppo);
            }
            keko.heapInsert(kaapo);
        }
        assertEquals(keko.heapMin(),teppo);
        assertEquals(keko.heapSize(),50);
    }
    
    @Test
    public void tuplaPoistoDellilla() {
         Koordinaatti teppo = null;
         Koordinaatti sami = null;
        for (int i = 1; i < 50; i++) {
            Koordinaatti kaapo = new Koordinaatti(5, 5, i+10, 't');
            if (i == 16) {
                teppo = new Koordinaatti(5,5,2,'t');
                keko.heapInsert(teppo);
            }
            if (i == 15) {
                sami = new Koordinaatti(5,5,4,'t');
                keko.heapInsert(sami);
            }
            keko.heapInsert(kaapo);
        }
        assertEquals(keko.heapDelMin(),teppo);
        assertEquals(keko.heapSize(),50);
        assertEquals(keko.heapMin(),sami);
    }
}
