package logiikka.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import logiikka.Koordinaatti;
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
public class KoordinaattiTest {
    private Koordinaatti koordinaatti;
    private Koordinaatti koordinaatti2;
    private Koordinaatti koordinaatti3;

    public KoordinaattiTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        koordinaatti = new Koordinaatti (5,8,10, '.');
        koordinaatti2 = new Koordinaatti (0,0,0,'#');
        koordinaatti3 = new Koordinaatti (5,8,-1,'A');
    }

    @After
    public void tearDown() {
    }

    @Test
    public void koordinaattienLuominenJaNiidenPainoarvot() {
        assertTrue(koordinaatti.getPainoarvo() == 10 && koordinaatti2.getPainoarvo() == 0 && koordinaatti3.getPainoarvo() == 0);

    }

}