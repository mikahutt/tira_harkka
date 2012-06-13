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
 * Tämä luokka muuttaa paintilla piirrettyjä labyrintteja Labyrintti-luokalle
 * syötäväksi.
 */
public class Bittikartta {

    final static int MUSTA = -16777216;
    final static int VALKEA = -1;
    final static int SUO = -14503604; // vihreä
    final static int LAHTO = -3584; // keltainen
    final static int MAALI = -1237980; // punainen
    final static int OVI = -20791; //pinkki
    final static int AVAIN = -6075996; //lila
    private char[][] labyrintti;
    private int korkeus;
    private int leveys;
    private BufferedImage kuva;

    /**
     * Asettaa Bittikartalle kuvan
     *
     * @param kuva
     */
    public void setKuva(BufferedImage kuva) {
        this.kuva = kuva;
    }

    /**
     * Palauttaa bittikartan sisältämän kuvan.
     *
     * @return
     */
    public BufferedImage getKuva() {
        return kuva;
    }

    /**
     * Muodostaa parametrina saadusta tiedosto nimestä/polusta char[][]
     * taulukon.
     *
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
     * Palauttaa kuvasta luodun 2-ulotteisen char-taulukon, jonka voi antaa
     * Labyrintti luokan konstruktorille.
     *
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
                } else if (pikseli == SUO) {
                    labyrintti[j][i] = 'S';
                } else if (pikseli == AVAIN) {
                    labyrintti[j][i] = 'K';
                } else if (pikseli == OVI) {
                    labyrintti[j][i] = 'D';
                } else {
                    labyrintti[j][i] = '.';
                }
            }
        }
    }
}