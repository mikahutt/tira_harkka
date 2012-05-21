/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class LabyrinttiSolverTest {
    LabyrinttiSolver solveri;
    Koordinaatti[][] koordinaatit;

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
        Labyrintti laby = new Labyrintti();
        solveri = new LabyrinttiSolver(laby);
        koordinaatit = new Koordinaatti[laby.labyrintinKorkeus()][laby.labyrintinLeveys()];
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void koordinaattienAlustuksenAloituspaikkaToimii() {
        solveri.koordinaattienAlustus(koordinaatit);
        assertTrue(koordinaatit[1][1].getMerkki() == 'A');
    }
    
    @Test
    public void koordinaattienAlustuksenLopetuspaikkaToimii() {
        solveri.koordinaattienAlustus(koordinaatit);
        assertTrue(koordinaatit[4][8].getMerkki() == 'L');
    }

    

}