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

    public Keko() {
        keonSisalto = new Koordinaatti[100];
    }

    public void heapInsert(Koordinaatti k) {
    }

    public Koordinaatti heapMin() {
        return null;
    }

    public void heapDelMin() {
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
        if (keonSisalto[this.length() - 1] != null) {
            return this.length();
        }
        int koko = 0;
        Koordinaatti eka = keonSisalto[0];
        while (eka != null) {
            koko++;
            eka = keonSisalto[koko];
        }
        return koko;

    }
    
    /**
     * Palauttaa keon alkion vanhemman
     * @param i
     * @return 
     */
    public Koordinaatti parent(int i) {
        if (i == 0 || i > this.heapSize() * 2) {
            return null;
        }
        return keonSisalto[i / 2];
    }
    /**
     * Palauttaa keon alkion vasemman lapsen
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
     * @param i
     * @return 
     */
    public Koordinaatti right(int i) {
        if (i > (this.heapSize() / 2)) {
            return null;
        }
        return keonSisalto[i * 2 + 1];
    }
}
