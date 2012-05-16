
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

        // Laitetaan talteen jokaista labyrintin kohtaa vastaava koordinaatti-olio
        Koordinaatti[][] koordinaatit = new Koordinaatti[labyrintti.labyrintinKorkeus()][labyrintti.labyrintinLeveys()];
        // Dijkstran ydin, keko, toteutettuna alussa javan valmiin PriorityQueue:n avulla
        PriorityQueue<Koordinaatti> valekeko = new PriorityQueue<Koordinaatti>(1, new KoordinaattiComparator());
        // Aloitetaan nyt yläkulmasta, valmiissa toteutuksessa aloitusruudusta
        koordinaatit[0][0] = new Koordinaatti(0, 0, 0);
        valekeko.add(koordinaatit[0][0]);

        while (!valekeko.isEmpty()) {

            Koordinaatti p = valekeko.poll();

            // Käydään läpi naapurit
            relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX() - 1, p.getY());
            relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX() + 1, p.getY());
            relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX(), p.getY() - 1);
            relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX(), p.getY() + 1);
        }


        return null;
    }

    private void relaksoi(Labyrintti labyrintti, PriorityQueue<Koordinaatti> valekeko, Koordinaatti[][] koordinaatit, Koordinaatti p, int x, int y) {

        if (x < 0 || y < 0 || x >= koordinaatit[0].length || y >= koordinaatit.length) {
            return;
        }

        int uusiE = p.getPainoarvo() + Math.abs(labyrintti.getLabyrintti()[p.getY()][p.getX()] - labyrintti.getLabyrintti()[y][x]);


        if (koordinaatit[y][x] == null) {
            koordinaatit[y][x] = new Koordinaatti(x, y, uusiE);
            valekeko.add(koordinaatit[y][x]);
            return;
        }

        int vanhaE = koordinaatit[y][x].getPainoarvo();
        if (uusiE < vanhaE) {
            valekeko.remove(koordinaatit[y][x]);
            koordinaatit[y][x] = new Koordinaatti(x, y, uusiE);
            valekeko.add(koordinaatit[y][x]);
        }
    }
}
