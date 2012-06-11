package tietorakenteet.test;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import java.util.PriorityQueue;
import logiikka.Koordinaatti;
import logiikka.KoordinaattiComparator;
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
    private Keko keko2;
    private int isoLuku;

    public KekoTest() {
        isoLuku = 600000;
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
        keko2 = new Keko();
        keko2.heapInsert(koordinaatti);
        keko2.heapInsert(koordinaatti2);

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
        assertEquals(keko2.heapSize(), 2);
    }

    @Test
    public void yksiKoordinaattiOnKeonHuipulla() {
        keko.heapInsert(koordinaatti);
        assertEquals(keko.heapMin(), koordinaatti);
    }

    @Test
    public void pieninKoordinaattiOnKeonHuipullaLisattynaVikana() {
        assertEquals(keko2.heapMin(), koordinaatti2);
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
            Koordinaatti kaapo = new Koordinaatti(5, 5, i + 10, 't');
            if (i == 16) {
                teppo = new Koordinaatti(5, 5, 2, 't');
                keko.heapInsert(teppo);
            }
            keko.heapInsert(kaapo);
        }
        assertEquals(keko.heapMin(), teppo);
        assertEquals(keko.heapSize(), 50);
    }

    @Test
    public void tuplaPoistoDellilla() {
        Koordinaatti teppo = null;
        Koordinaatti sami = null;
        for (int i = 1; i < 50; i++) {
            Koordinaatti kaapo = new Koordinaatti(5, 5, i + 10, 't');
            if (i == 16) {
                teppo = new Koordinaatti(5, 5, 2, 't');
                keko.heapInsert(teppo);
            }
            if (i == 15) {
                sami = new Koordinaatti(5, 5, 4, 't');
                keko.heapInsert(sami);
            }
            keko.heapInsert(kaapo);
        }
        assertEquals(keko.heapDelMin(), teppo);
        assertEquals(keko.heapSize(), 50);
        assertEquals(keko.heapMin(), sami);
    }

    @Test
    public void parentLoytyy() {
        assertEquals(koordinaatti2, keko2.parent(2));
        assertEquals(koordinaatti2, keko2.parent(3));

    }

    @Test
    public void parentEiLoydy() {
        keko.heapInsert(koordinaatti);
        assertNull(keko.parent(1));
        assertNull(keko.parent(5));

    }

    @Test
    public void montaParentia() {
        for (int i = 1; i < 100; i++) {
            Koordinaatti k = new Koordinaatti(1, 1, i, 'g');
            keko.heapInsert(k);
            assertEquals(keko.getKeonSisalto().haeIndeksista(i), keko.parent(i * 2));
        }
    }

    @Test
    public void leftOikein() {
        assertEquals(koordinaatti, keko2.left(1));
    }

    @Test
    public void leftNull() {
        assertNull(keko2.left(2));
        assertNull(keko2.left(5));
    }

    @Test
    public void leftKetju() {
        for (int i = 1; i < 100; i++) {
            Koordinaatti k = new Koordinaatti(1, 1, i, 'g');
            keko.heapInsert(k);
        }
        assertEquals(keko.getKeonSisalto().haeIndeksista(2), keko.left(1));
        assertEquals(keko.getKeonSisalto().haeIndeksista(4), keko.left(2));
        assertEquals(keko.getKeonSisalto().haeIndeksista(56), keko.left(28));
    }

    @Test
    public void rightNull() {
        assertNull(keko2.right(1));
    }

    @Test
    public void rightOikein() {
        keko2.heapInsert(new Koordinaatti(1, 1, 1, 't'));
        assertEquals(koordinaatti2, keko2.right(1));
    }

    /**
     * Verrataan omaa heapInsert toteutusta javan PriorityQueuen vastaavaan.
     * Testi on pitkä, koska molemmat keot tulee alustaa ja ajat kellottaa
     */
    @Test
    public void lisaamisenAikaVerrattunaJavanOmaanToteutukseen() {
        PriorityQueue<Koordinaatti> javaKeko = new PriorityQueue<Koordinaatti>(1, new KoordinaattiComparator());
        Keko omaKeko = new Keko(100000);

        long aikaJavalleAlku = System.currentTimeMillis();
        for (int i = 0; i < isoLuku / 3; i++) {
            Koordinaatti testi = new Koordinaatti(1, 1, i, 'g');
            javaKeko.add(testi);
        }
        long aikaJavalleLoppu = System.currentTimeMillis();
        double javanTulos = aikaJavalleLoppu - aikaJavalleAlku;

        long aikaOmalleAlku = System.currentTimeMillis();
        for (int i = 0; i < isoLuku / 3; i++) {
            Koordinaatti testi = new Koordinaatti(1, 1, i, 'g');
            omaKeko.heapInsert(testi);
        }
        long aikaOmalleLoppu = System.currentTimeMillis();
        double omaTulos = aikaOmalleLoppu - aikaOmalleAlku;

        assertTrue("Ois ollu: " + (omaTulos - javanTulos), (omaTulos - javanTulos) < 1000);
    }

    /**
     * Verrataan omaa heapDelMin toteutusta javan PriorityQueuen vastaavaan.
     * Testi on pitkä, koska molemmat keot tulee alustaa ja ajat kellottaa
     */
    @Test
    public void heapDelMinToimiiOikeassaAjassa() {
        PriorityQueue<Koordinaatti> javaKeko = new PriorityQueue<Koordinaatti>(1, new KoordinaattiComparator());
        Keko omaKeko = new Keko(isoLuku);
        //Alustetaan javan priorityQueue koordinaateilla, tämän aika on testattu jo edellisessä.
        for (int i = 0; i < isoLuku/2; i++) {
            Koordinaatti testi = new Koordinaatti(1, 1, i, 'g');
            javaKeko.add(testi);
        }
        //Alustetaan oma keko koordinaateilla, tämän aika on myös testattu jo edellisessä.
        for (int i = 0; i < isoLuku/2; i++) {
            Koordinaatti testi = new Koordinaatti(1, 1, i, 'g');
            omaKeko.heapInsert(testi);
        }

        long aikaJavalleAlku = System.currentTimeMillis();
        for (int i = 0; i < isoLuku/2; i++) {
            javaKeko.poll();
        }
        long aikaJavalleLoppu = System.currentTimeMillis();
        double javanTulos = aikaJavalleLoppu - aikaJavalleAlku;

        long aikaOmalleAlku = System.currentTimeMillis();
        for (int i = 0; i < isoLuku/2; i++) {
            omaKeko.heapDelMin();
        }
        long aikaOmalleLoppu = System.currentTimeMillis();
        double omaTulos = aikaOmalleLoppu - aikaOmalleAlku;

        assertTrue("Ois ollu: " + (omaTulos - javanTulos), (omaTulos - javanTulos) < 1000);
    }

    /**
     * Testataan omaa insertia vielä lisäämällä alkiot (painoarvot) käänteisessä
     * järjestyksessä.
     */
    @Test
    public void lisaamisenAikaVerrattunaJavanOmaanToteutukseenAlkiotToisessaJarjestyksessa() {
        PriorityQueue<Koordinaatti> javaKeko = new PriorityQueue<Koordinaatti>(1, new KoordinaattiComparator());
        Keko omaKeko = new Keko(isoLuku);

        long aikaJavalleAlku = System.currentTimeMillis();
        for (int i = isoLuku; i > 0; i--) {
            Koordinaatti testi = new Koordinaatti(1, 1, i, 'g');
            javaKeko.add(testi);
        }
        long aikaJavalleLoppu = System.currentTimeMillis();
        double javanTulos = aikaJavalleLoppu - aikaJavalleAlku;

        long aikaOmalleAlku = System.currentTimeMillis();
        for (int i = isoLuku; i > 0; i--) {
            Koordinaatti testi = new Koordinaatti(1, 1, i, 'g');
            omaKeko.heapInsert(testi);
        }
        long aikaOmalleLoppu = System.currentTimeMillis();
        double omaTulos = aikaOmalleLoppu - aikaOmalleAlku;

        assertTrue("Ois ollu: " + (omaTulos - javanTulos), (omaTulos - javanTulos) < 1000);
    }

    @Test
    public void heapDelMinToimiiOikeassaAjassaAlkiotToisessaJarjestyksessa() {
        PriorityQueue<Koordinaatti> javaKeko = new PriorityQueue<Koordinaatti>(1, new KoordinaattiComparator());
        Keko omaKeko = new Keko(isoLuku);
        //Alustetaan javan priorityQueue koordinaateilla, tämän aika on testattu jo edellisessä.
        for (int i = isoLuku/2; i > 0; i--) {
            Koordinaatti testi = new Koordinaatti(1, 1, i, 'g');
            javaKeko.add(testi);
        }
        //Alustetaan oma keko koordinaateilla, tämän aika on myös testattu jo edellisessä.
        for (int i = isoLuku/2; i > 0; i--) {
            Koordinaatti testi = new Koordinaatti(1, 1, i, 'g');
            omaKeko.heapInsert(testi);
        }

        long aikaJavalleAlku = System.currentTimeMillis();
        for (int i = 0; i < isoLuku/2; i++) {
            javaKeko.poll();
        }
        long aikaJavalleLoppu = System.currentTimeMillis();
        double javanTulos = aikaJavalleLoppu - aikaJavalleAlku;

        long aikaOmalleAlku = System.currentTimeMillis();
        for (int i = 0; i < isoLuku/2; i++) {
            omaKeko.heapDelMin();
        }
        long aikaOmalleLoppu = System.currentTimeMillis();
        double omaTulos = aikaOmalleLoppu - aikaOmalleAlku;

        assertTrue("Ois ollu: " + (omaTulos - javanTulos), (omaTulos - javanTulos) < 1000);
    }
}
