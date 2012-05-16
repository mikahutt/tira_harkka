/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MH
 */
public class Labyrintti {
    private char[][] labyrintti;

    public Labyrintti(){
        labyrintti = new char[][] {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '.', '#', '#', '#', '.', '.', '.', '.', '#'},
        {'#', '.', '.', '#', '#', '#', '#', '#', '.', '#'},
        {'#', '.', '.', '.', '#', '.', '#', '#', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    }

    public char[][] getLabyrintti() {
        return labyrintti;
    }

    public int labyrintinLeveys() {
        return labyrintti[0].length;
    }

    public int labyrintinKorkeus() {
        return labyrintti.length;
    }

}
