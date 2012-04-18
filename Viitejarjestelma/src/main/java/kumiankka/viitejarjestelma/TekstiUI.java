package kumiankka.viitejarjestelma;

public class TekstiUI {
    private IO io;
    private Viitepalvelu vp;

    public TekstiUI(IO io, Viitepalvelu vp) {
        this.io = io;
        this.vp = vp;
    }

    public void run() {
        io.tulosta("Viitekirjanpito Hieno Ohjelma versio J.34H");
        io.tulosta("Kirjoita auta näyttääksesi komennot");
        while (true) {
            String komento = io.lueSyote(">", false);
            if (komento == null) continue;

            if (komento.equals("lopeta")) {
                io.tulosta("Ohjelma lopetetaan.");
                break;
            }
            if (komento.equals("uusi")) {
                lisaaViite();
            } else if (komento.equals("listaa")) {
                listaaViitteet();
            } else if (komento.equals("auta")) {
                kerroKomennot();
            }
        }
    }

    private void lisaaViite() {
        String tyyppi = kysyViitteenTyyppi();
        vp.teeViite(tyyppi);
        kysyYleisetTiedot();      
        if (tyyppi.equals("article")){
            kysyArtikkelinTiedot();
        } else if (tyyppi.equals("inproceedings")){
            kysyKjulkaisunTiedot();
        } else {
            kysyKirjanTiedot();
        }
        kysyKirjoittajat();
        kysyTunniste();
        vp.tallennaViite();
        io.tulosta("Uusi viite lisättiin!");
    }
    
    private String kysyViitteenTyyppi(){
        int valinta = -1;
        while (valinta < 1 || valinta > 3) {
            io.tulosta("Valitse viitteen tyyppi:\n");
            io.tulosta("1. artikkeli\n2. konferenssijulkaisu\n3. kirja");
            valinta = io.lueLuku("Valinta:", true);
        }
        if (valinta == 1){
            return "article";
        } else if (valinta == 2){
            return "inproceedings";
        } else {
            return "book";
        }
    }

    private void kysyYleisetTiedot() {
        String otsikko = io.lueSyote("Otsikko:", true);
        int vuosi = io.lueLuku("Julkaisuvuosi:", true);
        vp.lisaaViitteenYleisetTiedot(otsikko, vuosi);
    }
    
    private void kysyKirjanTiedot() {
        String julkaisija = io.lueSyote("Julkaisija:", true);
        String osoite = io.lueSyote("Julkaisijan osoite:", false);
        String painos = io.lueSyote("Painos:", false);
        String sarja = io.lueSyote("Kirjasarja:", false);

        vp.lisaaKirjanTiedot(julkaisija, osoite, painos, sarja);
    }
    
    private void kysyKjulkaisunTiedot() {
        String kirjanNimi = io.lueSyote("Kirjan nimi:", true);
        String julkaisija = io.lueSyote("Julkaisija:", false);
        String osoite = io.lueSyote("Julkaisijan osoite:", false);
        String sarja = io.lueSyote("Kirjasarja:", false);
        int aloitusSivu = io.lueLuku("Ensimmäinen sivu:", false);
        int lopetusSivu = io.lueLuku("Viimeinen sivu:", false);
        String organisaatio = io.lueSyote("Organisaatio:", false);

        vp.lisaaKonferenssijulkaisunTiedot(kirjanNimi, julkaisija
                                           , osoite, sarja,
                                           aloitusSivu, lopetusSivu, organisaatio);
    }

    private void kysyArtikkelinTiedot() {
        String lehdenNimi = io.lueSyote("Lehden nimi:", true);
        int lehdenNumero = io.lueLuku("Lehden numero:", false);
        int aloitusSivu = io.lueLuku("Ensimmäinen sivu:", false);
        int lopetusSivu = io.lueLuku("Viimeinen sivu:", false);
        String julkaisija = io.lueSyote("Julkaisija:", false);

        vp.lisaaArtikkelinTiedot(lehdenNimi, lehdenNumero, aloitusSivu, aloitusSivu, julkaisija);
    }

    private void kysyKirjoittajat() {
        io.tulosta("Anna kirjoittajat, tai anna tyhjä lopettaaksesi");
        while (true) {
            String etunimi = io.lueSyote("Etunimi:", false);
            if (etunimi == null) {
                break;
            }
            String sukunimi = io.lueSyote("Sukunimi:", false);
            if (sukunimi == null) {
                break;
            }
            vp.lisaaKirjoittaja(etunimi, sukunimi);
        }
    }

    private void kerroKomennot() {
        String komennot = "\tauta\t- näyttää komennot\n"
                + "\tuusi\t- lisää uusi viite\n"
                + "\tlistaa\t- listaa viitteet\n"
                + "\tlopeta\t- lopeta ohjelma";
        io.tulosta(komennot);
    }

    private void listaaViitteet() {
        io.tulosta("Viitteet:");
        String viitteet = vp.listaaViitteet();
        if (viitteet.isEmpty()) {
            io.tulosta("Ei vielä lisättyjä viitteitä");
        } 
        else {
            io.tulosta(viitteet);
        }
    }

    private void kysyTunniste() {
        String tunniste = vp.generoiTunniste();
        io.tulosta("Tunnisteeksi generoitiin " + tunniste);
        while (true){
            String syote = io.lueSyote("Syötä oma tai anna tyhjä:",false);
            if (syote == null) break;
            if (vp.tunnisteKelpaa(syote)){
                tunniste = syote;
                break;
            }
            io.tulosta("Tunniste ei uniikki.");
        }
        vp.lisaaViitteenTunniste(tunniste);
    }
}
