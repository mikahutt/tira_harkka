
import java.util.ArrayList;
import java.util.PriorityQueue;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
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

    public ArrayList dijkstra() {

        // Laitetaan talteen jokaista labyrintin kohtaa vastaava koordinaatti-olio
        Koordinaatti[][] koordinaatit = new Koordinaatti[labyrintti.labyrintinKorkeus()][labyrintti.labyrintinLeveys()];
        // Dijkstran ydin, keko, toteutettuna alussa javan valmiin PriorityQueue:n avulla
        PriorityQueue<Koordinaatti> valekeko = new PriorityQueue<Koordinaatti>(1, new KoordinaattiComparator());

        koordinaattienAlustus(koordinaatit);
        Koordinaatti aloitus = aloituksenEtsinta(koordinaatit);
        
        valekeko.add(aloitus);
      
        while (!valekeko.isEmpty()) {
            Koordinaatti p = valekeko.poll();
            relaksoiKaikkiVierukset(p, koordinaatit, valekeko);

        }


        return null;
    }

    public Koordinaatti aloituksenEtsinta(Koordinaatti[][] koordinaatit) {
        for (int i = 0; i < koordinaatit.length; i++) {
            for (int j = 0; j < koordinaatit.length; j++) {
                if (koordinaatit[i][j].getMerkki() == 'A') {
                    return koordinaatit[i][j];
                }
            }
        }
        return null;
    }

    public void koordinaattienAlustus(Koordinaatti[][] koordinaatit) {
        for (int i = 0; i < koordinaatit.length; i++) {
            for (int j = 0; j < koordinaatit[0].length; j++) {
                if (labyrintti.getLabyrintti()[i][j] == '#') {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 1000, '#');
                } else if (labyrintti.getLabyrintti()[i][j] == 'A') {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 0, 'A');
                } else if (labyrintti.getLabyrintti()[i][j] == 'L') {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 0, 'L');
                } else {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 1, '.');
                }
            }
        }
    }

    private void relaksoi(Labyrintti labyrintti, PriorityQueue<Koordinaatti> valekeko, Koordinaatti[][] koordinaatit, Koordinaatti p, int x, int y) {

        if (x < 0 || y < 0 || x >= koordinaatit[0].length || y >= koordinaatit.length) {
            return;
        }

        int uusiE = p.getPainoarvo() + Math.abs(labyrintti.getLabyrintti()[p.getY()][p.getX()] - labyrintti.getLabyrintti()[y][x]);


        int vanhaE = koordinaatit[y][x].getPainoarvo();
        if (uusiE < vanhaE) {
            char valiaikainen = koordinaatit[y][x].getMerkki();
            valekeko.remove(koordinaatit[y][x]);
            koordinaatit[y][x] = new Koordinaatti(x, y, uusiE, valiaikainen);
            valekeko.add(koordinaatit[y][x]);
        }
    }

    private void relaksoiKaikkiVierukset(Koordinaatti p, Koordinaatti[][] koordinaatit, PriorityQueue valekeko) {

        relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX() - 1, p.getY());
        relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX() + 1, p.getY());
        relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX(), p.getY() - 1);
        relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX(), p.getY() + 1);
    }
}
