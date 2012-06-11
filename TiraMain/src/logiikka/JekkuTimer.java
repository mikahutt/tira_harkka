/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

/**
 *
 * @author MH
 */
public class JekkuTimer {

    public void odota(int i) {
        long alku = System.currentTimeMillis();
        
        while (true) {
            long loppu = System.currentTimeMillis();
            if ((loppu - alku) > i) {
                break;
            }
        }
    }
    
}
