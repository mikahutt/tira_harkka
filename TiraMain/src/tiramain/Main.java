/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tiramain;

import javax.swing.SwingUtilities;
import kayttoliittyma.KL;
import logiikka.Bittikartta;
import logiikka.Koordinaatti;
import logiikka.Labyrintti;
import logiikka.LabyrinttiSolver;

/**
 *
 * @author MH
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bittikartta kartta = new Bittikartta("tokaLaby.png");
        Labyrintti laby = new Labyrintti(kartta.getLabyrintti());
        LabyrinttiSolver solveri = new LabyrinttiSolver(laby,false);
        KL kayttoliittyma = new KL(solveri,kartta);
        SwingUtilities.invokeLater(kayttoliittyma);

        }
    }


