package kumiankka.viitejarjestelma;

import java.util.ArrayList;
import java.util.List;

public class Viite {
    private String tyyppi;
    private String otsikko;
    private String julkaisija;
    private String lehdenNimi;
    private int vuosi;
    private int aloitusSivu;
    private int vikaSivu;
    private int lehdenNumero;
    private List<Kirjoittaja> kirjoittajat;

    public Viite() {
    }

    public Viite(String tyyppi) {
        this.tyyppi = tyyppi;
        kirjoittajat = new ArrayList<Kirjoittaja>();
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public int getAloitusSivu() {
        return aloitusSivu;
    }

    public String getJulkaisija() {
        return julkaisija;
    }

    public String getLehdenNimi() {
        return lehdenNimi;
    }

    public int getLehdenNumero() {
        return lehdenNumero;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public int getVikaSivu() {
        return vikaSivu;
    }

    public int getVuosi() {
        return vuosi;
    }   
    
    public List<Kirjoittaja> getKirjoittajat() {
        return kirjoittajat;
    }

    public void setAloitusSivu(int aloitusSivu) {
        this.aloitusSivu = aloitusSivu;
    }

    public void setJulkaisija(String julkaisija) {
        this.julkaisija = julkaisija;
    }

    public void setKirjoittajat(List<Kirjoittaja> kirjoittajat) {
        this.kirjoittajat = kirjoittajat;
    }

    public void lisaaKirjoittaja(Kirjoittaja k) {
        this.kirjoittajat.add(k);
    }

    public void setLehdenNimi(String lehdenNimi) {
        this.lehdenNimi = lehdenNimi;
    }

    public void setLehdenNumero(int lehdenNumero) {
        this.lehdenNumero = lehdenNumero;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setVikaSivu(int vikaSivu) {
        this.vikaSivu = vikaSivu;
    }

    public void setVuosi(int vuosi) {
        this.vuosi = vuosi;
    }

    public String toString() {
        String palautus = tyyppi + ": ";
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
}
