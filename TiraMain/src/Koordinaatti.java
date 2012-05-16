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

    public int getPainoarvo() {
        return painoarvo;
    }

  

    public int getX() {
        return x;
    }

 

    public int getY() {
        return y;
    }



    public Koordinaatti(int x, int y, int painoarvo) {
        this.x = x;
        this.y = y;
        if (painoarvo < 0) {
            this.painoarvo = 0;
        } else {
            this.painoarvo = painoarvo;
        }
    }
}
