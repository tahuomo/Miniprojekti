package viitejarjestelma.logiikka;

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
    private String tunniste;
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
    @Column
    private int kuukausi;
    @Column
    private String osoite;
    @Column
    private String organisaatio;
    @Column
    private String kirjanNimi;
    @Column
    private String sarja;
    @Column
    private String painos;
    @Column
    private String lisatieto;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = {
        @JoinColumn(name = "viite_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "kirjoittaja_id")})
    private List<Kirjoittaja> kirjoittajat;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = {
        @JoinColumn(name = "viite_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "tagi_id")})
    private List<Tagi> tagit;

    public Viite() {
    }

    public Viite(String tyyppi) {
        this.tyyppi = tyyppi;
        this.kirjoittajat = new ArrayList<Kirjoittaja>();
        this.tagit = new ArrayList<Tagi>();
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

    public String getKirjanNimi() {
        return kirjanNimi;
    }

    public void setKirjanNimi(String kirjanNimi) {
        this.kirjanNimi = kirjanNimi;
    }

    public int getKuukausi() {
        return kuukausi;
    }

    public void setKuukausi(int kuukausi) {
        this.kuukausi = kuukausi;
    }

    public String getLisatieto() {
        return lisatieto;
    }

    public void setLisatieto(String lisatieto) {
        this.lisatieto = lisatieto;
    }

    public String getOrganisaatio() {
        return organisaatio;
    }

    public void setOrganisaatio(String organisaatio) {
        this.organisaatio = organisaatio;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getPainos() {
        return painos;
    }

    public void setPainos(String painos) {
        this.painos = painos;
    }

    public String getSarja() {
        return sarja;
    }

    public void setSarja(String sarja) {
        this.sarja = sarja;
    }

    public String getTunniste() {
        return tunniste;
    }

    public void setTunniste(String tunniste) {
        this.tunniste = tunniste;
    }

    public List<Tagi> getTagit() {
        return tagit;
    }

    public void setTagit(List<Tagi> tagit) {
        this.tagit = tagit;
    }
    
    public void lisaaTagi(Tagi tagi){
        this.tagit.add(tagi);
    }

    public String toString() {
        String palautus = "ID: " + tunniste + ", " + tyyppi + ": ";
        for (int i = 0; i < this.kirjoittajat.size(); i++) {
            palautus += this.kirjoittajat.get(i).toString();
            if (i < this.kirjoittajat.size() - 1) {
                palautus += ", ";
            }
        }
        palautus += ": " + otsikko + " - Julkaistu " + vuosi;

        return palautus;
    }
}
