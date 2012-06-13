/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import logiikka.Koordinaatti;

/**
 *
 * Keko sisältää luokan OmaArrayList, joka kapseloi sisäänsä tavallisen taulukon
 * joka sisältää koordinaatteja. Kun taulukko tulee täyteen, niin sen koko tuplataan.
 */
public class OmaArrayList {

    private Koordinaatti[] taulukko;
    private int maara;

    /**
     * Luodaan keon käyttämä tuplaava taulukko. Jos parametri on nolla tai
     * negatiivinen, niin luodaan sadan kokoinen taulukko.
     *
     * @param koko
     */
    public OmaArrayList(int koko) {
        if (koko < 1) {
            taulukko = new Koordinaatti[100];
            maara = 100;
        } else {
            taulukko = new Koordinaatti[koko];
            maara = koko;
        }

    }

    /**
     * Parametriton konstruktori luo oletuksena sadan pituisen taulukon.
     */
    public OmaArrayList() {
        taulukko = new Koordinaatti[100];
        maara = 100;
    }

    /**
     * Palauttaa taulukon pituuden (EI alkioiden lukumäärää)
     * @return 
     */
    public int length() {
        return taulukko.length;
    }

    /**
     * Lisää koordinaatin k taulukon paikkaan i. Metodi tarkastaa syötteen
     * oikeellisuuden. Jos taulukko on täynnä, niin taulukon koko tuplataan
     * ennen lisäämistä. Teen taulukon kopioimisen manuaalisesti, sillä
     * automaattinen IDE:n generoima arraycopy aiheutti minulle puolen tunnin
     * debuggaus virheen.
     *
     * @param k
     * @param i
     */
    public void lisaa(int i, Koordinaatti k) {

        if (kuinkaMontaMahtuu() == 0 || i >= taulukko.length) {
            Koordinaatti[] kopio = new Koordinaatti[taulukonKoko() * 2];
            for (int j = 0; j < taulukko.length; j++) {
                kopio[j] = taulukko[j];

            }
            maara = kopio.length-taulukko.length;
            taulukko = kopio;
            
        }
        maara--;
        taulukko[i] = k;

    }

    /**
     * Poistaa alkion taulukosta, eli asettaa sen nulliksi.
     * @param i 
     */
    public void poista(int i) {
        taulukko[i] = null;
    }

    /**
     * Palauttaa taulukon pituuden
     * @return 
     */
    public int taulukonKoko() {
        return taulukko.length;
    }

    /**
     * Palauttaa tiedon siitä kuinka monta alkiota taulukkoon mahtuu vielä, ennen kuin sen koko tulee tuplata.
     * @return 
     */
    public int kuinkaMontaMahtuu() {
        return maara;
    }

    /**
     * Palauttaa taulukon alkion joka sijaitsee parametrina saadussa indeksissä.
     * @param i
     * @return 
     */
    public Koordinaatti haeIndeksista(int i) {
        return taulukko[i];
    }
    
    /**
     * Lisää koordinaatin listan seuraavaan vapaaseen paikkaan.
     * @param koordinaatti 
     */
    public void add(Koordinaatti koordinaatti) {
        this.lisaa((this.length()-(this.kuinkaMontaMahtuu())), koordinaatti);
    }
    
    
}
