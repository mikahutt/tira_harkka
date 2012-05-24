package logiikka;


import java.util.ArrayList;
import java.util.PriorityQueue;



public class LabyrinttiSolver {

    private Labyrintti labyrintti;

    public LabyrinttiSolver(Labyrintti labyrintti) {
        this.labyrintti = labyrintti;
    }
    /**
     * 
     * Dijkstran algoritmi, joka palauttaa tällä hetkellä tiedon siitä, kuinka monta askelta kesti kulkea aloituksesta lopetukseen
     * @return 
     */
    public int dijkstra() {

        // Laitetaan talteen jokaista labyrintin kohtaa vastaava koordinaatti-olio
        Koordinaatti[][] koordinaatit = new Koordinaatti[labyrintti.labyrintinKorkeus()][labyrintti.labyrintinLeveys()];
        // Dijkstran ydin, keko, toteutettuna alussa javan valmiin PriorityQueue:n avulla
        PriorityQueue<Koordinaatti> valekeko = new PriorityQueue<Koordinaatti>(1, new KoordinaattiComparator());

        koordinaattienAlustus(koordinaatit);
        Koordinaatti aloitus = aloituksenEtsinta(koordinaatit);
        
        valekeko.add(aloitus);
        int montaLiikkumista = 0;
        while (!valekeko.isEmpty()) {
            montaLiikkumista++;
            Koordinaatti p = valekeko.poll();
            if (p.getMerkki() == 'L') {
                break;
            }
            relaksoiKaikkiVierukset(p, koordinaatit, valekeko);

        }


        return montaLiikkumista;
    }
    /**
     * 
     * Etsii parametrina saadusta koordinaatti-taulukosta aloituskohdas, eli koordinaatin, jonka merkki on 'A'
     * @param koordinaatit
     * @return  
     */
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

    /**
     * 
     * Alustaa koordinaatit char-labyrintin mukaan, josta koordinaatille asetetaan koordinaatit (ei tarvitse ja poistetaan), painoarvo ja merkki. HUOM. tämä on tässä vaiheessa todella typerä ratkaisu
     *
     * @param koordinaatit 
     */
    public void koordinaattienAlustus(Koordinaatti[][] koordinaatit) {
        for (int i = 0; i < koordinaatit.length; i++) {
            for (int j = 0; j < koordinaatit[0].length; j++) {
                if (labyrintti.getLabyrintti()[i][j] == '#') {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 100000, '#');
                } else if (labyrintti.getLabyrintti()[i][j] == 'A') {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 0, 'A');
                } else if (labyrintti.getLabyrintti()[i][j] == 'L') {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 0, 'L');
                } else {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 1000, '.');
                }
            }
        }
    }

    /**
     * 
     * "Relaksoi" eli päivittää solmun (koordinaatin) painoarvon
     */
    private void relaksoi(Labyrintti labyrintti, PriorityQueue<Koordinaatti> valekeko, Koordinaatti[][] koordinaatit, Koordinaatti p, int x, int y) {

        if (onkoKelpoSeuraaja(x, y,p.isKayty(), koordinaatit)) {
            return;
        }

        int uusiE = p.getPainoarvo() + 1;


        int vanhaE = koordinaatit[y][x].getPainoarvo();
        if (uusiE < vanhaE) {
            char valiaikainen = koordinaatit[y][x].getMerkki();
            valekeko.remove(p);
            koordinaatit[y][x] = new Koordinaatti(x, y, uusiE, valiaikainen);
            valekeko.add(koordinaatit[y][x]);
        }
    }

    private boolean onkoKelpoSeuraaja(int x, int y, boolean kayty, Koordinaatti[][] koordinaatit) {
        return x < 0 || y < 0 || x >= koordinaatit[0].length || y >= koordinaatit.length || kayty;
    }

    /**
     * 
     * relaksoi kaikki koordinaatin vierukset, eli suorittaa leveyssuuntaisen haun.
     */
    private void relaksoiKaikkiVierukset(Koordinaatti p, Koordinaatti[][] koordinaatit, PriorityQueue valekeko) {

        relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX() - 1, p.getY());
        relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX() + 1, p.getY());
        relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX(), p.getY() - 1);
        relaksoi(labyrintti, valekeko, koordinaatit, p, p.getX(), p.getY() + 1);
        p.setKayty(true);
    }
}
