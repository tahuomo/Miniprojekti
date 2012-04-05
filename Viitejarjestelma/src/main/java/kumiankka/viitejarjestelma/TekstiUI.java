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
                //kysytään tiedot ja lisätään viite palveluun
            } else if (komento.equals("listaa")) {
                io.tulosta(vp.listaaViitteet());
            } else if (komento.equals("auta")) {
                kerroKomennot();
            }

        }
    }

    private void kerroKomennot() {
        String komennot = "\tauta\t- näyttä komennot\n"
                + "\tuusi\t- lisää uusi viite\n"
                + "\tlistaa\t- listaa viitteet\n"
                + "\tlopeta\t- lopeta ohjelma";
        io.tulosta(komennot);
    }
}
