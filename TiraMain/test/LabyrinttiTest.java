/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import logiikka.Labyrintti;
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
public class LabyrinttiTest {
    private Labyrintti labyrintti;

    public LabyrinttiTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        labyrintti = new Labyrintti();
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void leveysJaPituusOikeinPain() {
        assertTrue(labyrintti.labyrintinKorkeus() == 5 && labyrintti.labyrintinLeveys() == 10);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}