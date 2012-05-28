/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tiramain;

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
        Labyrintti laby = new Labyrintti();
        LabyrinttiSolver solveri = new LabyrinttiSolver(laby);
        System.out.println(solveri.dijkstra());
    }

}
