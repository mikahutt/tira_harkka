/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.*;
import logiikka.Bittikartta;
import logiikka.JekkuTimer;
import logiikka.Koordinaatti;
import logiikka.LabyrinttiSolver;
import tietorakenteet.OmaArrayList;

/**
 *
 * Ohjelman käyttöliittymä. Tästä tullee kevyt graafinen liittymä.
 */
public class KL implements Runnable {

    private LabyrinttiSolver solveri;
    private JFrame frame;
    private JLabel kuvaLabel;
    private JPanel kuvaPaneeli;
    private Bittikartta kartta;
    private Koordinaatti[][] koordinaatit;

    /**
     * Käyttöliitymä saa parametrina LabyrinttiSolver olion, jonka kautta se
     * pääsee käsiksi sovelluslogiikan luokkiin.
     *
     * @param solveri
     */
    public KL(LabyrinttiSolver solveri, Bittikartta kartta) {
        this.solveri = solveri;
        this.kartta = kartta;
        solveri.dijkstra();
        koordinaatit = this.solveri.getKoordinaatit();
    }

    public void run() {
        frame = new JFrame("Magee Labyrintti");
        frame.setPreferredSize(new Dimension(500, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

        piirraaDijkstraa();
    }

    private void luoKomponentit(Container contentPane) {
        kuvaPaneeli = new JPanel();
        kuvaLabel = new JLabel(new ImageIcon(kartta.getKuva()));
        kuvaPaneeli.add(kuvaLabel);
        contentPane.add(kuvaPaneeli);

    }

    private void piirraaDijkstraa() {
        Graphics2D graffa = kartta.getKuva().createGraphics();
        graffa.setColor(Color.BLUE);
        for (int i = 0; i < koordinaatit.length; i++) {
            for (int j = 0; j < koordinaatit[0].length; j++) {
                if (koordinaatit[i][j].isKayty()) {
                    graffa = kartta.getKuva().createGraphics();
                    graffa.setColor(Color.BLUE);
                    graffa.drawOval(j, i, 0, 0);
                    graffa.dispose();
                }
                
               

            }

        }

        kuvaLabel.paint(graffa);
        piirraParasReitti();
    }

    private void piirraParasReitti() {
        Graphics2D graffa = kartta.getKuva().createGraphics();
        OmaArrayList parhaat = solveri.getParasReitti();
        for (int i = 0; i < parhaat.length();i++) {
            Koordinaatti k = parhaat.haeIndeksista(i);
            if (k != null) {
                graffa = kartta.getKuva().createGraphics();
                graffa.setColor(Color.ORANGE);
                graffa.drawOval(k.getY(), k.getX(), 0, 0);
                graffa.dispose();
            }
        }
        kuvaLabel.paint(graffa);
    }
}
