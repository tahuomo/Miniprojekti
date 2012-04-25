package viitejarjestelma.logiikka;

import viitejarjestelma.tietosailio.Viitehallinta;
import java.util.List;
import viitejarjestelma.io.Tiedostonkasittely;

public class Viitepalvelu {
    private Viitehallinta viitehallinta;
    private Viite viite;
    private BibTexGeneraattori bibgen;
    private Tiedostonkasittely tiedostonkirjoittaja;
    private HakukomentoTehdas hakukomennot;
    private List<Viite> viimeHaku;

    public Viitepalvelu(Viitehallinta viitehallinta, BibTexGeneraattori bibgen, Tiedostonkasittely tiedostonkirjoittaja) {
        this.viitehallinta = viitehallinta;
        this.bibgen = bibgen;
        this.tiedostonkirjoittaja = tiedostonkirjoittaja;
        this.hakukomennot = new HakukomentoTehdas();
    }

    public void teeViite(String tyyppi) {
        viite = new Viite(tyyppi);
    }

    public void lisaaViitteenYleisetTiedot(String otsikko, int julkaisuvuosi) {
        viite.setOtsikko(otsikko);
        viite.setVuosi(julkaisuvuosi);
    }

    public void lisaaArtikkelinTiedot(String lehti, int lehdenNumero, int alkusivu, int vikasivu, String julkaisija) {
        viite.setLehdenNimi(lehti);
        viite.setLehdenNumero(lehdenNumero);
        viite.setAloitusSivu(alkusivu);
        viite.setVikaSivu(vikasivu);
        viite.setJulkaisija(julkaisija);
    }

    public void lisaaKirjanTiedot(String julkaisija, String osoite, String painos, String sarja) {
        viite.setJulkaisija(julkaisija);
        viite.setOsoite(osoite);
        viite.setPainos(painos);
        viite.setSarja(sarja);
    }
    
    public void lisaaKonferenssijulkaisunTiedot(String kirjanNimi, String julkaisija, String osoite, String sarja, int aloitusSivu, int lopetusSivu, String organisaatio) {
        viite.setKirjanNimi(kirjanNimi);
        viite.setJulkaisija(julkaisija);
        viite.setOsoite(osoite);
        viite.setSarja(sarja);
        viite.setAloitusSivu(aloitusSivu);
        viite.setVikaSivu(lopetusSivu);
        viite.setOrganisaatio(organisaatio);
    }
    
    public void lisaaValinnaisetTiedot(int kuukausi, String lisatieto) {
        viite.setKuukausi(kuukausi);
        viite.setLisatieto(lisatieto);
    }
    
    public void lisaaKirjoittaja(String etunimi, String sukunimi) {
        Kirjoittaja k = new Kirjoittaja(etunimi, sukunimi);
        k.setViite(viite);
        viite.lisaaKirjoittaja(k);
    }
    
    public void lisaaTagi(String nimi) {
        Tagi tagi = new Tagi(nimi);
        tagi.setViite(viite);
        viite.lisaaTagi(tagi);
    }

    public void lisaaViitteenTunniste(String tunniste) {
        viite.setTunniste(tunniste);
    }

    public void tallennaViite() {
        viitehallinta.tallennaViite(viite);
    }

//    public String listaaViitteet() {
//        List<Viite> viitteet = viitehallinta.listaaViitteet();
//        String viitelista = "";
//        for (int i = 0; i < viitteet.size(); i++) {
//            viitelista += "[" + (i + 1) + "] " + viitteet.get(i).toString() + "\n";
//        }
//
//        return viitelista;
//    }

    public Viite getViite() {
        return viite;
    }
    
    public boolean poistaViite(String tunniste) {
        return viitehallinta.poistaViite(tunniste);
    }

    public boolean tunnisteKelpaa(String tunniste) {
        if (viitehallinta.etsiTunniste(tunniste) == null) {
            return true;
        }
        return false;
    }

    public String generoiTunniste() {
        String tunniste = "";
        for (Kirjoittaja k : viite.getKirjoittajat()) {
            tunniste += k.getSukunimi().charAt(0);
        }
        tunniste = tunniste.toUpperCase();
        tunniste = tunniste.replaceAll("Ä", "A");
        tunniste = tunniste.replaceAll("Ö", "O");
        tunniste += viite.getVuosi();

        while (!tunnisteKelpaa(tunniste)) {
            tunniste += "a";
        }
        return tunniste;

    }
    
    public String nykyinenViite(){
        return viite.toString();
    }

    public boolean bibtexTiedostoon(String tiedostonimi) {
        List<Viite> viitteet = viitehallinta.listaaViitteet();
        return tiedostonkirjoittaja.kirjoitaTiedostoon(viitteetBibtexiksi(viitteet), tiedostonimi);
    }
    
    public String bibtexRuudulle(){
        if (viimeHaku == null) return viitteetBibtexiksi(viitehallinta.listaaViitteet());
        String bibtexString = viitteetBibtexiksi(viimeHaku);
        viimeHaku = null;
        return bibtexString;
    }
    
    private String viitteetBibtexiksi(List<Viite> viitteet) {
        
        String bibtex = "";
        for (Viite v : viitteet) {
            bibtex += bibgen.teeViitteestaBibtex(v);
        }
        return bibtex;
    }

    public String listaaViitteet(String hakuehto) {
        List<Viite> haunTulos = viitehallinta.listaaViitteet();
        
        if (hakuehto != null){
            String[] parametrit = hakuehto.split("=");
            if (parametrit.length == 2) {
                haunTulos = hakukomennot.hae(parametrit[0]).suorita(haunTulos, parametrit[1]);
            }
        } 
        viimeHaku = haunTulos;
        String viitelista = "";
        for (int i = 0; i < haunTulos.size(); i++) {
            viitelista += "[" + (i + 1) + "] " + haunTulos.get(i).toString() + "\n";
        }
        return viitelista;
    }
}
