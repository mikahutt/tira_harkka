/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet.test;

import logiikka.Koordinaatti;
import org.junit.*;
import tietorakenteet.OmaHashMap;
import static org.junit.Assert.*;
/**
 *
 * @author MH
 */
public class OmaHashMapTest {
    private OmaHashMap map;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        map = new OmaHashMap(10000000);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void ekaTesti() {
        assertTrue(true);
    }
    
    @Test
    public void lisaaKoordinaatti() {
        Koordinaatti k = new Koordinaatti(1, 2, 5, 'A');
        Koordinaatti v = new Koordinaatti(1, 2, 5, 'A');
        map.put(k, v);
        assertEquals(v,map.get(k));
    }


}