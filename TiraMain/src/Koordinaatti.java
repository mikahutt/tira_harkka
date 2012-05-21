/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MH
 */
public class Koordinaatti {

    private final int x;
    private final int y;
    private final int painoarvo;
    private final char merkki;

    public int getPainoarvo() {
        return painoarvo;
    }

  

    public int getX() {
        return x;
    }

    public char getMerkki() {
        return merkki;
    }

 

    public int getY() {
        return y;
    }



    public Koordinaatti(int x, int y, int painoarvo, char merkki) {
        this.x = x;
        this.y = y;
        this.merkki = merkki;
        if (painoarvo < 0) {
            this.painoarvo = 0;
        } else {
            this.painoarvo = painoarvo;
        }
    }
}
