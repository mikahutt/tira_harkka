/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tiramain;

import kayttoliittyma.Aloitus;
import java.util.Scanner;
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

    
    public static void main(String[] args) {
        Aloitus aloitus = new Aloitus();
        SwingUtilities.invokeLater(aloitus);
        
//        Bittikartta kartta = new Bittikartta("suoLaby.png");
//        Labyrintti laby = new Labyrintti(kartta.getLabyrintti());
//        LabyrinttiSolver solveri = new LabyrinttiSolver(laby,false, 5);
//        KL kayttoliittyma = new KL(solveri,kartta);
//        SwingUtilities.invokeLater(kayttoliittyma);

        }
    }


