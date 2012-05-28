package logiikka;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    
    
    /*
     * Luodaan koordinaatti, jolla on painoarvo ja labyrintin paikkaa vastaava merkki
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
            
    public boolean isKayty() {
        return kayty;
    }

    public void setKayty(boolean kayty) {
        this.kayty = kayty;
    }
    

    public int getPainoarvo() {
        return painoarvo;
    }

  

    public int getX() {
        return x;
    }

    public char getMerkki() {
        return merkki;
    }

    public void setPainoarvo(int painoarvo) {
        this.painoarvo = painoarvo;
    }

 

    public int getY() {
        return y;
    }


 
}
