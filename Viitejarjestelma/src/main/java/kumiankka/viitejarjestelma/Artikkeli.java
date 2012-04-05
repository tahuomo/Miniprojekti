package kumiankka.viitejarjestelma;

import java.util.List;

public class Artikkeli {
    private String otsikko;
    private String julkaisija;
    private String lehdenNimi;
    private int vuosi;
    private int aloitusSivu;
    private int vikaSivu;
    private int lehdenNumero;
    private List<Kirjoittaja> kirjoittajat;

    public Artikkeli() {
    }

    public Artikkeli(String otsikko, String julkaisija, String lehdenNimi, int vuosi, int aloitusSivu, int vikaSivu, int lehdenNumero, List<Kirjoittaja> kirjoittajat) {
        this.otsikko = otsikko;
        this.julkaisija = julkaisija;
        this.lehdenNimi = lehdenNimi;
        this.vuosi = vuosi;
        this.aloitusSivu = aloitusSivu;
        this.vikaSivu = vikaSivu;
        this.lehdenNumero = lehdenNumero;
        this.kirjoittajat = kirjoittajat;
    }

    public String toString() {
        String palautus = "";
        for (int i = 0; i < this.kirjoittajat.size(); i++) {
            palautus += this.kirjoittajat.get(i).toString();
            if (i < this.kirjoittajat.size() - 1) {
                palautus += " , ";
            }
        }
        palautus += " " + otsikko + ". " + lehdenNimi + " (" + julkaisija + "), " + lehdenNumero
                + ":" + aloitusSivu + "-" + vikaSivu + ", " + vuosi;

        return palautus;

    }

    public List<Kirjoittaja> getKirjoittajat() {
        return kirjoittajat;
    }
}
