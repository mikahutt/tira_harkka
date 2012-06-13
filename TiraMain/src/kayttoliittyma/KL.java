/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import logiikka.Bittikartta;
import logiikka.Koordinaatti;
import logiikka.LabyrinttiSolver;
import tietorakenteet.OmaArrayList;

/**
 *
 * Ohjelman käyttöliittymä. Näyttää valitun labyrintin.
 */
public class KL implements Runnable,ActionListener {

    private LabyrinttiSolver solveri;
    private JFrame frame;
    private JLabel kuvaLabel;
    private JPanel kuvaPaneeli;
    private Bittikartta kartta;
    private Koordinaatti[][] koordinaatit;
    private JButton simuloi;

    /**
     * Käyttöliitymä saa parametrina LabyrinttiSolver olion, jonka kautta se
     * pääsee käsiksi sovelluslogiikan luokkiin. Toinen parametri on Bittikartta,
     * jota käytetään dijkstran algoritmin kuvaamiseen.
     *
     * @param solveri
     * @param kartta  
     */
    public KL(LabyrinttiSolver solveri, Bittikartta kartta) {
        this.solveri = solveri;
        this.kartta = kartta;
        solveri.dijkstra();
        koordinaatit = this.solveri.getKoordinaatit();
    }

    /**
     * Käynnistää käyttöliittymän
     */
    public void run() {
        String teksti;
       if (solveri.isEukleides()) {
           teksti = "Euklidinen metriikka";
       }else {
           teksti = "Taksikuski";
       }
       
        frame = new JFrame(teksti + ", suokerroin: " + solveri.getSuoKerroin());

      luoKomponentit(frame.getContentPane());

    }

    private void luoKomponentit(Container contentPane) {
        BoxLayout layout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(layout);
        
        kuvaPaneeli = new JPanel();
        kuvaLabel = new JLabel(new ImageIcon(kartta.getKuva()));
        kuvaPaneeli.add(kuvaLabel);
        contentPane.add(kuvaPaneeli); 
                
        simuloi = new JButton("Simuloi");
        simuloi.addActionListener(this);
        contentPane.add(simuloi);

        frame.pack();
        frame.setVisible(true);
    }

    private void piirraaDijkstraa() {
        Graphics2D graffa = kartta.getKuva().createGraphics();
        graffa.setColor(Color.BLUE);
        for (int i = 0; i < koordinaatit.length; i++) {
            for (int j = 0; j < koordinaatit[0].length; j++) {
                if (koordinaatit[i][j].isKayty()) {
                    graffa = kartta.getKuva().createGraphics();
                    graffa.setColor(Color.BLUE);
                    if (koordinaatit[i][j].getMerkki() == 'S') {
                        graffa.setColor(Color.CYAN);
                    }
                    graffa.drawOval(j, i, 0, 0);
                    graffa.dispose();
                }
            }
        }
        kuvaLabel.setIcon(new ImageIcon(kartta.getKuva()));
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

    /**
     * Kuuntelee "simuloi"-nappulaa. Tämä käynnistää privaatin-metodin, joka piirtää dijkstraa näytölle
     * @param e 
     */
    public void actionPerformed(ActionEvent e) {
        piirraaDijkstraa();
    }
}
