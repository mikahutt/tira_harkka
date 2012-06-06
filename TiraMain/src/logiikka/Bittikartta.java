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
 * @author MH
 */
public class Bittikartta {

    final static int MUSTA = -16777216;
    final static int VALKEA = -1;
    final static int LAHTO = -3584;
    final static int MAALI = -65536;
    private char[][] labyrintti;
    private int korkeus;
    private int leveys;
    BufferedImage kuva;

    public Bittikartta(String tiedosto) {
        kuva = null;

        try {

            kuva = ImageIO.read(new File(tiedosto));

        } catch (IOException ex) {

            System.exit(0);

        }
       // teeLabyrintti(kuva);
    }
    
    public char[][] getLabyrintti() {
        teeLabyrintti(kuva);
        return labyrintti;
    }

    private void teeLabyrintti(BufferedImage kuva) {
        korkeus = kuva.getHeight();
        leveys = kuva.getWidth();

        labyrintti = new char[korkeus][leveys];

        alustaLabyrintti(labyrintti, kuva);
    }

    private void alustaLabyrintti(char[][] labyrintti, BufferedImage kuva) {
        for (int j = 0; j < korkeus; j++) {
            for (int i = 0; i < leveys; i++) {
                int pikseli = kuva.getRGB(i, j);
                
                if (pikseli != MUSTA && pikseli != VALKEA) {
                    System.out.println(pikseli);
                }
                
                if (pikseli == VALKEA) {
                    labyrintti[j][i] = '.';
                }
                if (pikseli == MUSTA) {
                    labyrintti[j][i] = '#';
                }
                if (pikseli == LAHTO) {
                    labyrintti[j][i] = 'A';
                }
                if (pikseli == MAALI) {
                    labyrintti[j][i] = 'L';
                }
            }
        }
    }
}