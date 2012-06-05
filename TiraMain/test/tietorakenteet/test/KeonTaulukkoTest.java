/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet.test;

import logiikka.Koordinaatti;
import org.junit.*;
import tietorakenteet.KeonTaulukko;
import static org.junit.Assert.*;


/**
 *
 * @author MH
 */
public class KeonTaulukkoTest {
     private KeonTaulukko taulu;
     private KeonTaulukko taulu2;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        taulu = new KeonTaulukko(300);
        taulu2 = new KeonTaulukko();
        
    }

    @After
    public void tearDown() {
    }

   @Test
   public void lisaaminenToimii() {
       for (int i = 0; i < 1000; i++) {
           Koordinaatti k = new Koordinaatti(1,1,i,'k'); 
           taulu.lisaa(k, i);
       }
       assertEquals(taulu.taulukonKoko(),1200);
   }
}
