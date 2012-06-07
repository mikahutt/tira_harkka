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
     * Tuplaavalla taulukolla toimiva minimikeko.
     *
     */
    private KeonTaulukko keonSisalto;
    private int hiipinKoko;

    /**
     * Palauttaa keon sisältämien alkioiden lukumäärän.
     * @return 
     */
    public int getHiipinKoko() {
        return hiipinKoko;
    }

    /**
     * Asettaa alkioiden lukumäärän, tämä lienee täysin turha.
     * @param hiipinKoko 
     */
    public void setHiipinKoko(int hiipinKoko) {
        this.hiipinKoko = hiipinKoko;
    }

    /**
     * Palauttaa keon sisällön, eli keon kapseloiman keonTaulukon joka sisältää
     * koordinaatteja.
     * @return 
     */
    public KeonTaulukko getKeonSisalto() {
        return keonSisalto;
    }

    /**
     * Asettaa keolle parametrina saadut alkiot ja poistaa vanhat.
     * @param keonSisalto 
     */
    public void setKeonSisalto(KeonTaulukko keonSisalto) {
        this.keonSisalto = keonSisalto;
    }

    /**
     * Luo oletuksena keon johon mahtuu 100 alkiota, ennen kuin aikaa vievä taulukon tuplaaminen
     * on tehtävä.
     */
    public Keko() {
        keonSisalto = new KeonTaulukko(100);
        hiipinKoko = 0;
    }

    /**
     * Luo halutun kokoisen keon. Tämä koko siis määrittää sen, kuinka monennen alkion lisäämisen jälkeen
     * keon sisältämä taulukko joudutaan tuplaamaan.
     * @param taulukonKoko 
     */
    public Keko(int taulukonKoko) {
        keonSisalto = new KeonTaulukko(taulukonKoko);
        hiipinKoko = 0;
    }

    /**
     * Palauttaa tiedon siitä onko keko tyhjä.
     * @return 
     */
    public boolean isEmpty() {
        return heapSize() == 0;
    }

    /**
     * Asettaa alkion kekoon oikealle paikalleen. Tämä tapahtuu yksinkertaisesti vertailemalla
     * keossa olevien koordinaattien painoarvoja.
     * @param k 
     */
    public void heapInsert(Koordinaatti k) {
        hiipinKoko++;
        int i = heapSize();
        while (i > 1 && this.parent(i).getPainoarvo() > k.getPainoarvo()) {
            keonSisalto.lisaa(i, keonSisalto.haeIndeksista(i / 2));
            i = (i / 2);
        }
        keonSisalto.lisaa(i, k);

    }

    /**
     * Palauttaa keon pienimmän alkion
     *
     * @return
     */
    public Koordinaatti heapMin() {
        return keonSisalto.haeIndeksista(1);
    }

    /**
     * Palauttaa ja poistaa keon pienimmän alkion.
     *
     * @return
     */
    public Koordinaatti heapDelMin() {
        Koordinaatti min = this.heapMin();
        keonSisalto.lisaa(1, keonSisalto.haeIndeksista(heapSize()));
        keonSisalto.lisaa(heapSize(), null);
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
        return keonSisalto.length();
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
        return keonSisalto.haeIndeksista(i / 2);
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
        return keonSisalto.haeIndeksista(i * 2);
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
        return keonSisalto.haeIndeksista(i * 2 + 1);
    }

    /**
     * Metodi heapify pitää keko-ehdon voimassa. Koitan keksiä tähän
     * elegantimman toteutuksen.
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
            if (keonSisalto.haeIndeksista(i).getPainoarvo() > pienin.getPainoarvo()) {
                this.vaihda(i, pienimmanIndeksi);
                this.heapify(pienimmanIndeksi);
            }
        } else if (i * 2 == heapSize() && keonSisalto.haeIndeksista(i).getPainoarvo() > vasen.getPainoarvo()) {
            this.vaihda(i, i * 2);
        }
    }

    private void vaihda(int i, int j) {
        Koordinaatti talteen = keonSisalto.haeIndeksista(i);
        keonSisalto.lisaa(i, keonSisalto.haeIndeksista(j));
        keonSisalto.lisaa(j, talteen);
    }
}
