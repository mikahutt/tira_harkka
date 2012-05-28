/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tiramain;

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
        Labyrintti laby = new Labyrintti();
        LabyrinttiSolver solveri = new LabyrinttiSolver(laby);
//        Koordinaatti[][] naatit = solveri.dijkstra();
        
//        for (Koordinaatti[] koordinaattis : naatit) {
//            for (Koordinaatti koordinaatti : koordinaattis) {
//                System.out.print(koordinaatti.getPainoarvo() + ",");
//            }
//            System.out.println("");
//            }
//            
        }
    }


