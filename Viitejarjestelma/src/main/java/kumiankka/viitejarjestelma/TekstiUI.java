package kumiankka.viitejarjestelma;

public class TekstiUI {
    private static boolean PAKOLLINEN = true;
    private static boolean VALINNAINEN = false;
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
            String komento = io.lueSyote(">", VALINNAINEN);
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
            } else if (komento.equals("bibtex")){
                kirjoitaBibtexTiedosto();
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
            valinta = io.lueLuku("Valinta:", PAKOLLINEN);
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
        String otsikko = io.lueSyote("Otsikko:", PAKOLLINEN);
        int vuosi = io.lueLuku("Julkaisuvuosi:", PAKOLLINEN);
        vp.lisaaViitteenYleisetTiedot(otsikko, vuosi);
    }
    
    private void kysyKirjanTiedot() {
        String julkaisija = io.lueSyote("Julkaisija:", PAKOLLINEN);
        String osoite = io.lueSyote("Julkaisijan osoite:", VALINNAINEN);
        String painos = io.lueSyote("Painos:", VALINNAINEN);
        String sarja = io.lueSyote("Kirjasarja:", VALINNAINEN);

        vp.lisaaKirjanTiedot(julkaisija, osoite, painos, sarja);
    }
    
    private void kysyKjulkaisunTiedot() {
        String kirjanNimi = io.lueSyote("Kirjan nimi:", PAKOLLINEN);
        String julkaisija = io.lueSyote("Julkaisija:", VALINNAINEN);
        String osoite = io.lueSyote("Julkaisijan osoite:", VALINNAINEN);
        String sarja = io.lueSyote("Kirjasarja:", VALINNAINEN);
        int aloitusSivu = io.lueLuku("Ensimmäinen sivu:", VALINNAINEN);
        int lopetusSivu = io.lueLuku("Viimeinen sivu:", VALINNAINEN);
        String organisaatio = io.lueSyote("Organisaatio:", VALINNAINEN);

        vp.lisaaKonferenssijulkaisunTiedot(kirjanNimi, julkaisija
                                           , osoite, sarja,
                                           aloitusSivu, lopetusSivu, organisaatio);
    }

    private void kysyArtikkelinTiedot() {
        String lehdenNimi = io.lueSyote("Lehden nimi:", PAKOLLINEN);
        int lehdenNumero = io.lueLuku("Lehden numero:", VALINNAINEN);
        int aloitusSivu = io.lueLuku("Ensimmäinen sivu:", VALINNAINEN);
        int lopetusSivu = io.lueLuku("Viimeinen sivu:", VALINNAINEN);
        String julkaisija = io.lueSyote("Julkaisija:", VALINNAINEN);

        vp.lisaaArtikkelinTiedot(lehdenNimi, lehdenNumero, aloitusSivu, lopetusSivu, julkaisija);
    }
    
    private void kysyValinnaisetTiedot(){
        
    }

    private void kysyKirjoittajat() {
        io.tulosta("Anna kirjoittajat, tai anna tyhjä lopettaaksesi");
        while (true) {
            String etunimi = io.lueSyote("Etunimi:", VALINNAINEN);
            if (etunimi == null) {
                break;
            }
            String sukunimi = io.lueSyote("Sukunimi:", VALINNAINEN);
            if (sukunimi == null) {
                break;
            }
            vp.lisaaKirjoittaja(etunimi, sukunimi);
        }
    }

    private void kerroKomennot() {
        String komennot = "\tauta\t- näyttää komennot\n"
                + "\tuusi\t- lisää uusi viite\n"
                + "\tbibtex\t- tallenna viitteet bibtex-tiedostoon\n"
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
            String syote = io.lueSyote("Syötä oma tai anna tyhjä:", VALINNAINEN);
            if (syote == null) break;
            if (vp.tunnisteKelpaa(syote)){
                tunniste = syote;
                break;
            }
            io.tulosta("Tunniste ei uniikki.");
        }
        vp.lisaaViitteenTunniste(tunniste);
    }

    private void kirjoitaBibtexTiedosto() {
        String tiedostonimi = io.lueSyote("Anna tiedostonimi:", PAKOLLINEN);
        io.tulosta("Tulostetaan tiedostoon...");
        if (vp.bibtexTiedostoon(tiedostonimi)){
            io.tulosta("Valmis!");
        } else {
            io.tulosta("Virhe tiedostoa kirjoittaessa.");
        }
    }
}
