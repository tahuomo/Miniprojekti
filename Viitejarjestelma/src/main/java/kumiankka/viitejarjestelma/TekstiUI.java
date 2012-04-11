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
            String komento = io.lueRivi(">");

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
    
    private void lisaaViite(){
        String tyyppi = io.lueRivi("Kerro viitetyyppi:");
        vp.teeViite(tyyppi);
        kysyViitteenTiedot();
        kysyArtikkelinTiedot();
        kysyKirjoittajat();
        vp.tallennaViite();
        io.tulosta("Uusi viite lisättiin!");
  
        
  }

    private void kysyViitteenTiedot() {
        String otsikko = io.lueRivi("Otsikko:");
        int vuosi = io.lueLuku("Julkaisuvuosi:");
        vp.lisaaViitteenYleisetTiedot(otsikko, vuosi);  
    }

    private void kysyArtikkelinTiedot() {
        String lehdenNimi = io.lueRivi("Lehden nimi:");
        int lehdenNumero = io.lueLuku("Lehden numero:");
        int aloitusSivu = io.lueLuku("Ensimmäinen sivu:");
        int lopetusSivu = io.lueLuku("Viimeinen sivu:");
        String julkaisija = io.lueRivi("Julkaisija:");
        
        vp.lisaaArtikkelinTiedot(lehdenNimi, lehdenNumero, aloitusSivu, aloitusSivu, julkaisija);
    }

    private void kysyKirjoittajat() {
        io.tulosta("Anna kirjoittajat, tai anna tyhjä lopettaaksesi");
        while (true){
            String etunimi = io.lueRivi("Etunimi:");
            if (etunimi.isEmpty()) break;
            String sukunimi = io.lueRivi("Sukunimi:");
            if (sukunimi.isEmpty()) break;
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
    
    private void listaaViitteet(){
        String viitteet = vp.listaaViitteet();
        if (viitteet.isEmpty()) io.tulosta("Ei vielä lisättyjä viitteitä");
        else io.tulosta(viitteet);
    }
}
