/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import logiikka.LabyrinttiSolver;

/**
 *
 * Ohjelman käyttöliittymä. Tästä tullee kevyt graafinen liittymä.
 */
public class KL implements Runnable {

    private LabyrinttiSolver solveri;
    private JFrame frame;

    /**
     * Käyttöliitymä saa parametrina LabyrinttiSolver olion, jonka kautta se
     * pääsee käsiksi sovelluslogiikan luokkiin.
     *
     * @param solveri
     */
    public KL(LabyrinttiSolver solveri) {
        this.solveri = solveri;
    }

    public void run() {
        frame = new JFrame("Magee Labyrintti");
        frame.setPreferredSize(new Dimension(200, 100));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container contentPane) {
        JPanel paneeli = new JPanel();
        
    }
}
