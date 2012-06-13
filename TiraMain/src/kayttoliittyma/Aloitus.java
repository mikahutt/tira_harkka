/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import logiikka.Bittikartta;
import logiikka.Labyrintti;
import logiikka.LabyrinttiSolver;

/**
 *
 * @author MH
 */
public class Aloitus implements Runnable,ActionListener{
    private JFrame frame;
    private JRadioButton eukleides;
    private JRadioButton manhattan;
    private boolean metriikka;
    private JTextField labyrintti;
    private JTextField suoKerroin;

    public void run() {
        frame = new JFrame("Valitse");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container contentPane) {
        BoxLayout layout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(layout);
        
        contentPane.add(new JLabel("Valitse metriikka"));
        
        eukleides = new JRadioButton("\"Euklidinen\"");
        manhattan = new JRadioButton("Manhattan");
        
        ButtonGroup buttonit = new ButtonGroup();
        buttonit.add(eukleides);
        buttonit.add(manhattan);
        
        
        JButton ok = new JButton("OK");
        ok.addActionListener(this);

        labyrintti = new JTextField("ekaLaby.png");
        suoKerroin = new JTextField("2");
                
        contentPane.add(eukleides);
        contentPane.add(manhattan);
        contentPane.add(new JLabel("Anna labyrintin nimi: "));
        contentPane.add(labyrintti);
        contentPane.add(new JLabel("Anna suon kulkukerroin (int): "));
        contentPane.add(suoKerroin);
        contentPane.add(ok);
    }
    
    private boolean onkoInteger(String str) {
        return str.matches("^-?[0-9]+(\\.[0-9]+)?$");
}


    public void actionPerformed(ActionEvent e) {
        if (!onkoInteger(suoKerroin.getText()) || (Integer.parseInt(suoKerroin.getText())) < 0) {
            JOptionPane.showMessageDialog(frame, "RTFM");
            return;
        }
        if (eukleides.isSelected()) {
            metriikka = true;
        }else {
            metriikka = false;
        }
        Bittikartta kartta = new Bittikartta(labyrintti.getText());
        Labyrintti laby = new Labyrintti(kartta.getLabyrintti());
        int kerroin = Integer.parseInt(suoKerroin.getText());
        LabyrinttiSolver solveri = new LabyrinttiSolver(laby,metriikka,kerroin);
        KL kayttoliittyma = new KL(solveri,kartta);
        SwingUtilities.invokeLater(kayttoliittyma);
    }
    
}
