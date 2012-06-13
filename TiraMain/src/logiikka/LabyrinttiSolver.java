package logiikka;

import java.util.HashMap;
import tietorakenteet.Keko;
import tietorakenteet.OmaArrayList;
import tietorakenteet.OmaHashMap;

/**
 * "Kirjastoluokka, joka sisältää metodeja labyrintin läpikäymiseen
 *
 */
public class LabyrinttiSolver {

    private Labyrintti labyrintti;
    private Koordinaatti[][] koordinaatit;
    private int suoKerroin;
    private OmaArrayList parasReitti;
    //private HashMap<Koordinaatti,Koordinaatti> edeltajat;
    private OmaHashMap edeltajat;

    public boolean isEukleides() {
        return eukleides;
    }
    private boolean eukleides;

    public OmaArrayList getParasReitti() {
        return parasReitti;
    }

    public void setParasReitti(OmaArrayList parasReitti) {
        this.parasReitti = parasReitti;
    }

    public int getSuoKerroin() {
        return suoKerroin;
    }

    /**
     * Ottaa talteen parametrina saadun labyrintin. Luokan metodit käsittelevät
     * juuri tätä labyrinttia. Toisen parametrin ollessa true käytetään
     * "euklidista metriikkaa" eli haku tapahtuu kahdeksaan eri suuntaan
     *
     * @param labyrintti
     * @param eukleides
     * @param suoKerroin
     */
    public LabyrinttiSolver(Labyrintti labyrintti, boolean eukleides, int suoKerroin) {
        this.labyrintti = labyrintti;
        edeltajat = new OmaHashMap(20000000);
        this.eukleides = eukleides;
        this.suoKerroin = suoKerroin;
    }

    /**
     *
     * Dijkstran algoritmi, joka palauttaa tällä hetkellä tiedon siitä, kuinka
     * monta askelta kesti kulkea aloituksesta lopetukseen
     *
     * @return
     */
    public int dijkstra() {

        // Laitetaan talteen jokaista labyrintin kohtaa vastaava koordinaatti-olio
        koordinaatit = new Koordinaatti[labyrintti.labyrintinKorkeus()][labyrintti.labyrintinLeveys()];
        // Dijkstran ydin, keko.
        Keko valekeko = new Keko();

        parasReitti = new OmaArrayList();

        koordinaattienAlustus(koordinaatit);
        Koordinaatti aloitus = aloituksenEtsinta(koordinaatit);

        valekeko.heapInsert(aloitus);
        int montaLiikkumista = 0;
        while (!valekeko.isEmpty()) {
            Koordinaatti p = valekeko.heapDelMin();

            if (p.getMerkki() == 'L') {
                montaLiikkumista = p.getPainoarvo();
                asetaParasReitti(edeltajat, p);
                break;
            }
            relaksoiKaikkiVierukset(p, koordinaatit, valekeko);
            p.setKayty(true);
        }


        return montaLiikkumista;
    }

    public Koordinaatti[][] getKoordinaatit() {
        return koordinaatit;
    }

    public void setKoordinaatit(Koordinaatti[][] koordinaatit) {
        this.koordinaatit = koordinaatit;
    }

    /**
     *
     * Etsii parametrina saadusta koordinaatti-taulukosta aloituskohdas, eli
     * koordinaatin, jonka merkki on 'A'
     *
     * @param koordinaatit
     * @return
     */
    public Koordinaatti aloituksenEtsinta(Koordinaatti[][] koordinaatit) {
        for (int i = 0; i < koordinaatit.length; i++) {
            for (int j = 0; j < koordinaatit[0].length; j++) {
                if (koordinaatit[i][j].getMerkki() == 'A') {
                    return koordinaatit[i][j];
                }
            }
        }
        return null;
    }

    /**
     *
     * Alustaa koordinaatit char-labyrintin mukaan, josta koordinaatille
     * asetetaan koordinaatit, painoarvo ja merkki. Tämä on tyylikkäästi
     * kovakoodattu.
     *
     *
     * @param koordinaatit
     */
    public void koordinaattienAlustus(Koordinaatti[][] koordinaatit) {
        for (int i = 0; i < koordinaatit.length; i++) {
            for (int j = 0; j < koordinaatit[0].length; j++) {
                if (labyrintti.getLabyrintti()[i][j] == '#') {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 10000000, '#');
                } else if (labyrintti.getLabyrintti()[i][j] == 'A') {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 0, 'A');
                } else if (labyrintti.getLabyrintti()[i][j] == 'L') {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 100000000, 'L');
                } else if (labyrintti.getLabyrintti()[i][j] == 'S') {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 20000, 'S');
                } else {
                    koordinaatit[i][j] = new Koordinaatti(i, j, 10000, '.');
                }
            }
        }
    }

    /**
     *
     * "Relaksoi" eli päivittää solmun (koordinaatin) painoarvon
     *
     * @param valekeko
     * @param koordinaatit
     * @param p
     * @param x
     * @param y
     */
    public void relaksoi(Keko valekeko, Koordinaatti[][] koordinaatit, Koordinaatti p, int x, int y) {
        Koordinaatti tutkittava = koordinaatit[x][y];
        if (onkoEpaKelpoSeuraaja(x, y, koordinaatit)) {
            return;
        }

        int uusiE = p.getPainoarvo() + 1;
        if (tutkittava.getMerkki() == 'S') {
            uusiE = p.getPainoarvo() + suoKerroin;
        }

        //Tasoitetaan euklidista etäisyyttä paremmaksi
        if (eukleides && Math.abs((p.getX() + p.getY()) - (tutkittava.getX() + tutkittava.getY())) > 1) {
            uusiE *= 1.42;
        }

        int vanhaE = tutkittava.getPainoarvo();
        if (uusiE < vanhaE) {
            char valiaikainen = tutkittava.getMerkki();
            koordinaatit[x][y] = new Koordinaatti(x, y, uusiE, valiaikainen);
            edeltajat.put(koordinaatit[x][y], p);
            valekeko.heapInsert(koordinaatit[x][y]);
        }
    }

    /**
     * Tarkastaa onko parametrina annetut koordinaatit taulukossa ja onko niihin
     * liittyvässä koordinaatissa jo vierailtu.
     *
     * @param x
     * @param y
     * @param kayty
     * @param koordinaatit
     * @return
     */
    public boolean onkoEpaKelpoSeuraaja(int x, int y, Koordinaatti[][] koordinaatit) {
        return x < 0 || y < 0 || x >= koordinaatit.length || y >= koordinaatit[0].length;
    }

    /**
     *
     * relaksoi kaikki koordinaatin vierukset, eli suorittaa leveyssuuntaisen
     * haun.
     */
    private void relaksoiKaikkiVierukset(Koordinaatti p, Koordinaatti[][] koordinaatit, Keko valekeko) {
        if (!seina(koordinaatit, p.getX() - 1, p.getY())) {
            relaksoi(valekeko, koordinaatit, p, p.getX() - 1, p.getY());
        }
        if (!seina(koordinaatit, p.getX() + 1, p.getY())) {
            relaksoi(valekeko, koordinaatit, p, p.getX() + 1, p.getY());
        }
        if (!seina(koordinaatit, p.getX(), p.getY() - 1)) {
            relaksoi(valekeko, koordinaatit, p, p.getX(), p.getY() - 1);
        }
        if (!seina(koordinaatit, p.getX(), p.getY() + 1)) {
            relaksoi(valekeko, koordinaatit, p, p.getX(), p.getY() + 1);
        }

        if (eukleides) {
            if (!seina(koordinaatit, p.getX() - 1, p.getY() - 1)) {
                relaksoi(valekeko, koordinaatit, p, p.getX() - 1, p.getY() - 1);
            }
            if (!seina(koordinaatit, p.getX() + 1, p.getY() + 1)) {
                relaksoi(valekeko, koordinaatit, p, p.getX() + 1, p.getY() + 1);
            }
            if (!seina(koordinaatit, p.getX() - 1, p.getY() + 1)) {
                relaksoi(valekeko, koordinaatit, p, p.getX() - 1, p.getY() + 1);
            }
            if (!seina(koordinaatit, p.getX() + 1, p.getY() - 1)) {
                relaksoi(valekeko, koordinaatit, p, p.getX() + 1, p.getY() - 1);
            }
        }
    }

    private boolean seina(Koordinaatti[][] koordinaatit, int x, int y) {
        return koordinaatit[x][y].getMerkki() == '#';
    }

    private void asetaParasReitti(HashMap<Koordinaatti,Koordinaatti> edeltajat, Koordinaatti maali) {
        Koordinaatti askel = maali;
        parasReitti.add(askel);
        
        while (edeltajat.get(askel) != null) {
            askel = edeltajat.get(askel);
            parasReitti.add(askel);
        }

    }
        private void asetaParasReitti(OmaHashMap edeltajat, Koordinaatti maali) {
        Koordinaatti askel = maali;
        parasReitti.add(askel);
        
        while (edeltajat.get(askel) != null) {
            askel = edeltajat.get(askel);
            parasReitti.add(askel);
        }

    }
}
