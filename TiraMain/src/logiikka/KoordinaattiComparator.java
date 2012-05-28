package logiikka;


import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Luokka määrittelee koordinaattien välisen järjestyksen niiden painoarvon mukaan
 */
public class KoordinaattiComparator implements Comparator<Koordinaatti>{

    public KoordinaattiComparator() {
    }

    @Override
    public int compare(Koordinaatti o1, Koordinaatti o2) {
        return o1.getPainoarvo() - o2.getPainoarvo();
    }

}
