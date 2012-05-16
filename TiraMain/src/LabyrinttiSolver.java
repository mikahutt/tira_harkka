
import java.util.ArrayList;
import java.util.PriorityQueue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MH
 */
public class LabyrinttiSolver {

    private Labyrintti labyrintti;

    public LabyrinttiSolver(Labyrintti labyrintti) {
        this.labyrintti = labyrintti;
    }

    public ArrayList Dijkstra() {
        Koordinaatti[][] koordinaatit = new Koordinaatti[labyrintti.labyrintinLeveys()][labyrintti.labyrintinKorkeus()];

        PriorityQueue<Koordinaatti> valekeko = new PriorityQueue<Koordinaatti>(1, new KoordinaattiComparator());

        return null;
    }
}
