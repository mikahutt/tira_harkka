package tietorakenteet;

import logiikka.Koordinaatti;

public class OmaHashMap {

    private Koordinaatti[] arvot;

    public OmaHashMap(int koko) {
        arvot = new Koordinaatti[koko];
    }

    public void put(Koordinaatti avain, Koordinaatti arvo) {
        int hash = avain.hashCode();
        arvot[hash % koko()] = arvo;
    }

    public Koordinaatti get(Koordinaatti avain) {
        int hash = avain.hashCode();
        return arvot[hash % koko()];
    }

    private int koko() {
        return arvot.length;
    }
}
