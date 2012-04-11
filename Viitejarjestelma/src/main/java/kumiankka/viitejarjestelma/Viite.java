package kumiankka.viitejarjestelma;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Viite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String tyyppi;
    @Column
    private String otsikko;
    @Column
    private String julkaisija;
    @Column
    private String lehdenNimi;
    @Column
    private int vuosi;
    @Column
    private int aloitusSivu;
    @Column
    private int vikaSivu;
    @Column
    private int lehdenNumero;
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(joinColumns = {
        @JoinColumn(name = "viite_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "kirjoittaja_id")})
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
