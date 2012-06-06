/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import logiikka.Koordinaatti;

/**
 * Oma toteutus keosta
 *
 * @author MH
 */
public class Keko {

    /**
     * Tehdään keon sisältö aluksi maksimipituudeltaan sadan kokoiseksi,
     * myöhemmin tehdään tuplaava taulukko. Alussa minimikeko.
     */
    private Koordinaatti[] keonSisalto;
    private int hiipinKoko;

    public Koordinaatti[] getKeonSisalto() {
        return keonSisalto;
    }

    public void setKeonSisalto(Koordinaatti[] keonSisalto) {
        this.keonSisalto = keonSisalto;
    }

    public Keko() {
        keonSisalto = new Koordinaatti[2000001];
        hiipinKoko = 0;
    }

    public void heapInsert(Koordinaatti k) {
        int i = heapSize() + 1;
        while (i > 1 && this.parent(i).getPainoarvo() > k.getPainoarvo()) {
            keonSisalto[i] = keonSisalto[i / 2];
            i = (i / 2);
        }
        keonSisalto[i] = k;
        hiipinKoko++;
    }

    /**
     * Palauttaa keon pienimmän alkion
     *
     * @return
     */
    public Koordinaatti heapMin() {
        return keonSisalto[1];
    }

    /**
     * Palauttaa ja poistaa keon pienimmän alkion.
     *
     * @return
     */
    public Koordinaatti heapDelMin() {
        Koordinaatti min = this.heapMin();
        keonSisalto[1] = keonSisalto[heapSize()];
        keonSisalto[heapSize()] = null;
        hiipinKoko--;

        heapify(1);

        return min;
    }

    /**
     * Palauttaa keon pituuden
     *
     * @return int
     */
    public int length() {
        return keonSisalto.length;
    }

    /**
     * Palauttaa keon alkioiden lukumäärän.
     *
     * @return
     */
    public int heapSize() {
        return hiipinKoko;

    }

    /**
     * Palauttaa keon alkion vanhemman
     *
     * @param i
     * @return
     */
    public Koordinaatti parent(int i) {
//        if (i == 0 || i > this.heapSize() * 2) {
//            return null;
//        }
        return keonSisalto[i / 2];
    }

    /**
     * Palauttaa keon alkion vasemman lapsen
     *
     * @param i
     * @return
     */
    public Koordinaatti left(int i) {
        if (i > (this.heapSize() / 2)) {
            return null;
        }
        return keonSisalto[i * 2];
    }

    /**
     * Palauttaa keon alkion oikean lapsen
     *
     * @param i
     * @return
     */
    public Koordinaatti right(int i) {
        if (i > (this.heapSize() / 2)) {
            return null;
        }
        return keonSisalto[i * 2 + 1];
    }

    /**
     * Metodi heapify pitää keko-ehdon voimassa
     *
     * @param i
     */
    public void heapify(int i) {
        if (heapSize() == 1 || i >= this.length()) {
            return;
        }
        Koordinaatti vasen = this.left(i);
        Koordinaatti oikea = this.right(i);
        Koordinaatti pienin;
        int pienimmanIndeksi;
        if (oikea != null) {
            if (vasen.getPainoarvo() < oikea.getPainoarvo()) {
                pienin = vasen;
                pienimmanIndeksi = i * 2;
            } else {
                pienin = oikea;
                pienimmanIndeksi = i * 2 + 1;
            }
            if (keonSisalto[i].getPainoarvo() > pienin.getPainoarvo()) {
                this.vaihda(i, pienimmanIndeksi);
                this.heapify(pienimmanIndeksi);
            }
        } else if (i * 2 == heapSize() && keonSisalto[i].getPainoarvo() > vasen.getPainoarvo()) {
            this.vaihda(i, i * 2);
        }
    }

    private void vaihda(int i, int j) {
        Koordinaatti talteen = keonSisalto[i];
        keonSisalto[i] = keonSisalto[j];
        keonSisalto[j] = talteen;
    }
}
