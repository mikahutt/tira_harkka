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

    /**
     * Luodaan keon käyttämä tuplaava taulukko. Jos parametri on nolla tai negatiivinen, niin luodaan sadan kokoinen taulukko.
     *
     * @param koko
     */
    public KeonTaulukko(int koko) {
        if (koko < 1) {
            taulukko = new Koordinaatti[100];
        }
        taulukko = new Koordinaatti[koko];
    }

    /**
     * Parametriton konstruktori luo oletuksena sadan pituisen taulukon.
     */
    public KeonTaulukko() {
        taulukko = new Koordinaatti[100];
    }

    /**
     * Lisää koordinaatin k taulukon paikkaan i. Metodi tarkastaa syötteen oikeellisuuden. Jos taulukko on täynnä, niin taulukon koko tuplataan ennen lisäämistä.
     * 
     * @param k
     * @param i 
     */
    public void lisaa(Koordinaatti k, int i) {
       
        if (kuinkaMontaMahtuu() == 0) {
            Koordinaatti[] kopio = new Koordinaatti[taulukonKoko() * 2];
            for (int j = 0; j < taulukko.length; j++) {
                kopio[j] = taulukko[j];
                
            }
            taulukko = kopio;   
        }
        taulukko[i] = k;
    }
    
    public void poista(int i) {
        taulukko[i] = null;
    }

    public int taulukonKoko() {
        return taulukko.length;
    }

    public int kuinkaMontaMahtuu() {
        int maara = 0;
        for (int i = 0; i < taulukko.length; i++) {
            if (taulukko[i] == null) {
                break;
            }
            maara++;
            
        }
        return this.taulukonKoko() - maara;
    }

}
