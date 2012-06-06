/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import logiikka.Koordinaatti;

/**
 *
 * @author MH
 */
public class KeonTaulukko {

    private Koordinaatti[] taulukko;
    private int maara;

    /**
     * Luodaan keon käyttämä tuplaava taulukko. Jos parametri on nolla tai
     * negatiivinen, niin luodaan sadan kokoinen taulukko.
     *
     * @param koko
     */
    public KeonTaulukko(int koko) {
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
    public KeonTaulukko() {
        taulukko = new Koordinaatti[100];
        maara = 100;
    }

    public int length() {
        return taulukko.length;
    }

    /**
     * Lisää koordinaatin k taulukon paikkaan i. Metodi tarkastaa syötteen
     * oikeellisuuden. Jos taulukko on täynnä, niin taulukon koko tuplataan
     * ennen lisäämistä.
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
            taulukko = kopio;
            maara = taulukko.length;
        }
        maara--;
        taulukko[i] = k;

    }

    public void poista(int i) {
        taulukko[i] = null;
    }

    public int taulukonKoko() {
        return taulukko.length;
    }

    public int kuinkaMontaMahtuu() {
//        int maara = 0;
//        for (int i = 0; i < taulukko.length; i++) {
//            if (taulukko[i] == null) {
//                break;
//            }
//            maara++;
//            
//        }
        return maara;
    }

//    public void asetaIndeksiin(int i, Koordinaatti k) {
//        taulukko[i] = k;
//    }
    public Koordinaatti haeIndeksista(int i) {
        return taulukko[i];
    }
}
