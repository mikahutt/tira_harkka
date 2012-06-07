package logiikka;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Luokka, joka esittää labyrintteja 2-ulotteisena char-taulukkona.
 * 
 */
public class Labyrintti {
    private char[][] labyrintti;

    /**
     * Ilman parametreja luotaessa oletus labyrintti on kovakoodattu.
     */
    public Labyrintti(){
        labyrintti = new char[][] {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', 'A', '#', '#', '#', '.', '.', '.', '.', '#'},
        {'#', '.', '.', '#', '#', '#', '#', '#', '.', '#'},
        {'#', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', 'L', '#'}};

    }
    
    /**
     * Labyrintin voi luoda myös parametrina saadusta char-taulukosta.
     * Erityisesti Bittikartta luokka luo tälläisia taulukoita.
     * @param taulu 
     */
    public Labyrintti(char[][] taulu) {
        labyrintti = taulu;
    }

    /**
     * Palauttaa labyrintin sisältämän char-esityksen.
     * @return 
     */
    public char[][] getLabyrintti() {
        return labyrintti;
    }

    /**
     * Palauttaa labyrintin leveyden
     * @return 
     */
    public int labyrintinLeveys() {
        return labyrintti[0].length;
    }

    /**
     * Palauttaa labyrintin korkeuden.
     * @return 
     */
    public int labyrintinKorkeus() {
        return labyrintti.length;
    }

}
