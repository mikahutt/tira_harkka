package logiikka;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * Luokka pitää sisällään koordinaatteihin liittyvää dataa.
 */
public class Koordinaatti {

    private final int x;
    private final int y;
    private int painoarvo;
    private final char merkki;
    private boolean kayty;

    /**
     * Luodaan koordinaatti, jolla on painoarvo ja labyrintin paikkaa vastaava
     * merkki
     * @param x
     * @param y
     * @param painoarvo
     * @param merkki  
     */
    public Koordinaatti(int x, int y, int painoarvo, char merkki) {
        this.x = x;
        this.y = y;
        kayty = false;
        this.merkki = merkki;
        if (painoarvo < 0) {
            this.painoarvo = 0;
        } else {
            this.painoarvo = painoarvo;
        }
    }
    /**
     * Palauttaa tiedon siitä onko koordinaatissa jo vierailtu (Dijkstraa varten)
     * @return 
     */
    public boolean isKayty() {
        return kayty;
    }

    /**
     * Asettaa koordinaatin käydyksi.
     * @param kayty 
     */
    public void setKayty(boolean kayty) {
        this.kayty = kayty;
    }

    /**
     * Palauttaa koordinaattiin liittyvän painoarvon
     * @return 
     */
    public int getPainoarvo() {
        return painoarvo;
    }

    /**
     * Palauttaa koordinaatin x-arvon eli sijainnin x-akselilla
     * @return 
     */
    public int getX() {
        return x;
    }

    /**
     * Palauttaa koordinaattiin liittyvän char-merkin, joka liittyy bittikarttojen mappaamiseen char-taulukoiden kautta.
     * @return 
     */
    public char getMerkki() {
        return merkki;
    }

    /**
     * Asettaa koordinatin painoarvon.
     * @param painoarvo 
     */
    public void setPainoarvo(int painoarvo) {
        this.painoarvo = painoarvo;
    }

    /**
     * Palauttaa koordinaatin sijainnin y-akselilla.
     * @return 
     */
    public int getY() {
        return y;
    }
}
