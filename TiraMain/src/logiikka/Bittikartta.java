/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * Tämä luokka muuttaa paintilla piirrettyjä labyrintteja Labyrintti-luokalle syötäväksi.
 */
public class Bittikartta {

    final static int MUSTA = -16777216;
    final static int VALKEA = -1;
    final static int LAHTO = -3584;
    final static int MAALI = -1237980;
    private char[][] labyrintti;
    private int korkeus;

    public void setKuva(BufferedImage kuva) {
        this.kuva = kuva;
    }
    private int leveys;
    private BufferedImage kuva;

    public BufferedImage getKuva() {
        return kuva;
    }
    /**
     * Muodostaa parametrina saadusta tiedosto nimestä/polusta char[][] taulukon.
     * @param tiedosto 
     */
    public Bittikartta(String tiedosto) {
        kuva = null;

        try {

            kuva = ImageIO.read(new File(tiedosto));

        } catch (IOException ex) {

            System.exit(0);

        }
        
        teeLabyrintti(kuva);
    }

    /**
     * Palauttaa kuvasta luodun 2-ulotteisen char-taulukon, jonka voi antaa Labyrintti luokan konstruktorille.
     * @return 
     */
    public char[][] getLabyrintti() {
        return labyrintti;
    }

    private void teeLabyrintti(BufferedImage kuva) {
        korkeus = kuva.getHeight();
        leveys = kuva.getWidth();

        labyrintti = new char[korkeus][leveys];

        alustaLabyrintti(labyrintti, kuva);
    }

    /**
     * Alustaa labyrintin vastaaviksi charreiksi. Else on tässä sen takia että
     * png-muotoiset kuvat tasoittavat värien reunoja, jolloin labyrintissa on
     * paljon erilaisia värisävyjä. Tässä nekin määritellään kuljettaviksi.
     *
     * @param labyrintti
     * @param kuva
     */
    private void alustaLabyrintti(char[][] labyrintti, BufferedImage kuva) {

        for (int j = 0; j < korkeus; j++) {
            for (int i = 0; i < leveys; i++) {
                int pikseli = kuva.getRGB(i, j);

                if (pikseli == MUSTA) {
                    labyrintti[j][i] = '#';
                } else if (pikseli == LAHTO) {
                    labyrintti[j][i] = 'A';
                } else if (pikseli == MAALI) {
                    labyrintti[j][i] = 'L';
                } else {
                    labyrintti[j][i] = '.';
                }
            }
        }
    }
}