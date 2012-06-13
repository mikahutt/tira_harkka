package tietorakenteet;

import logiikka.Koordinaatti;
/**
 * Oma toteutus javan HashMapista. Toteutettuna vain ohjelman tarvitsemat metodit put ja get
 * @author MH
 */

public class OmaHashMap {

    private Koordinaatti[] avaimet;
    private Koordinaatti[] arvot;

    /**
     * Luo parametrina annetun Koordinaatti-taulukon. Voisi kapseloida myös omaan ArrayListiin. Nyt vältän
     * uudelleenhajautuksen käyttämällä tietoa koordinaattien maksimimäärästä. Perustelut lähteen sivulla 286
     * (Matti Luukkainen tira 2011)
     * @param koko 
     */
    public OmaHashMap(int koko) {
        avaimet = new Koordinaatti[koko];
        arvot = new Koordinaatti[koko];
    }

    /**
     * Lisää avaimen hashcodeen perustuvaan indeksiin liittyvän arvon. Tässä käytetään lineaarista
     * avointa hajautusta.
     * @param avain
     * @param arvo 
     */
    public void put(Koordinaatti avain, Koordinaatti arvo) {
        int i = 0;
        int hash = avain.hashCode();
        int kokeiluPaikka = hash % koko();
        while (true) {
            kokeiluPaikka = kokeiluPaikka + i;
            if (avaimet[kokeiluPaikka] == null) {
                avaimet[kokeiluPaikka] = avain;
                arvot[kokeiluPaikka] = arvo;
                return;
            }
            i++;
        }
    }

    /**
     * Hakee avaimeen liittyvän arvon käyttäen yksinkertaista lineaarista "kokeilu-jonoa".
     * @param avain
     * @return 
     */
    public Koordinaatti get(Koordinaatti avain) {
        int i = 0;
        int hash = avain.hashCode();
        int kokeiluPaikka = hash % koko();
        while (true) {
            kokeiluPaikka = kokeiluPaikka + i;
//            System.out.println(kokeiluPaikka);
            if (avaimet[kokeiluPaikka] == null) {
                return null;
            }
            if (avaimet[kokeiluPaikka].equals(avain))
                return arvot[kokeiluPaikka];
            i++;
        }
        
    }

    private int koko() {
        return avaimet.length;
    }
}
