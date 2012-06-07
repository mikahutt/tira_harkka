package logiikka.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import logiikka.Koordinaatti;
import logiikka.KoordinaattiComparator;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author MH
 */
public class KoordinaattiComparatorTest {
    private KoordinaattiComparator komparaattori;
    
    public KoordinaattiComparatorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        komparaattori = new KoordinaattiComparator();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void komparaattoriToimiiOikein() {
        Koordinaatti a = new Koordinaatti(1,1,5,'H');
        Koordinaatti b = new Koordinaatti(2,1,2,'H');
        Koordinaatti c = new Koordinaatti(2,9,2,'H');
        assertTrue(komparaattori.compare(a, b) > 0);
        assertTrue(komparaattori.compare(b,a) < 0);
        assertTrue(komparaattori.compare(c,b) == 0);
    }

}
