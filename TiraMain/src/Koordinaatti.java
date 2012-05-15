/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MH
 */
public class Koordinaatti {

    private int x;
    private int y;
    private int painoarvo;

    public int getPainoarvo() {
        return painoarvo;
    }

    public void setPainoarvo(int painoarvo) {
        this.painoarvo = painoarvo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
