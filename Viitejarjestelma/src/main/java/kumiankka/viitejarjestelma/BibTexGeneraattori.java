package kumiankka.viitejarjestelma;

import java.util.List;

public class BibTexGeneraattori {
    private Viite viite;

    public BibTexGeneraattori(Viite viite) {
        this.viite = viite;
    }

    public String teeViitteestaBibtex() {
        String bibtex = "@" + this.viite.getTyyppi() + "{" + this.viite.getTunniste() + ",\n";
        bibtex += this.teeKirjoittajistaBibtex()
                + this.teeOtsikostaBibtex()
                + this.teeJulkaisijastaBibtex()
                + this.teeVuosiluvustaBibtex();
        return bibtex;
    }

    public String teeKirjoittajistaBibtex() {
        String bibtex = "";
        List<Kirjoittaja> kirjoittajat = this.viite.getKirjoittajat();

        for (int i = 0; i < kirjoittajat.size(); i++) {
            bibtex += this.teeKirjoittajastaBibtex(kirjoittajat.get(i));

            if (i < kirjoittajat.size() - 1) {
                bibtex += " and ";
            }
        }
        return bibtex;
    }

    public String teeKirjoittajastaBibtex(Kirjoittaja kirjoittaja) {
        return kirjoittaja.getSukunimi() + ", " + kirjoittaja.getSukunimi();
    }

    public String teeOtsikostaBibtex() {
        String otsikko = this.viite.getOtsikko();

        if (otsikko.isEmpty()) {
            return "";
        }

        return "title = {" + otsikko + "},\n";
    }

    public String teeJulkaisijastaBibtex() {
        String julkaisija = this.viite.getJulkaisija();

        if (julkaisija.isEmpty()) {
            return "";
        }

        return "publisher = {" + julkaisija + "},\n";
    }

    public String teeVuosiluvustaBibtex() {
        int vuosi = this.viite.getVuosi();

        if (vuosi == -1) {
            return "";
        }

        return "year = {" + vuosi + "},\n";
    }

    public String teeKuukaudestaBibtex() {
        int kuukausi = this.viite.getKuukausi();

        if (kuukausi == -1) {
            return "";
        }

        return "month = {" + kuukausi + "},\n";
    }

    public String teeSivunumeroistaBibtex() {
        int alku = this.viite.getAloitusSivu();
        int loppu = this.viite.getVikaSivu();

        if (alku == -1 || loppu == -1) {
            return "";
        }

        return "pages = {" + alku + "--" + loppu + "},\n";
    }

    public String teeLehdenNumerostaBibtex() {
        int lehdenNro = this.viite.getLehdenNumero();

        if (lehdenNro == -1) {
            return "";
        }

        return "number = {" + lehdenNro + "},\n";
    }

    public String teeOsoitteestaBibtex() {
        String osoite = this.viite.getOsoite();

        if (osoite.isEmpty()) {
            return "";
        }

        return "address = {" + osoite + "},\n";
    }

    public String teeOrganisaatiostaBibtex() {
        String organisaatio = this.viite.getOrganisaatio();

        if (organisaatio.isEmpty()) {
            return "";
        }

        return "organization = {" + organisaatio + "},\n";
    }

    public String teeKirjanNimestaBibtex() {
        String kirjanNimi = this.viite.getKirjanNimi();

        if (kirjanNimi.isEmpty()) {
            return "";
        }

        return "booktitle = {" + kirjanNimi + "},\n";
    }

    public String teeSarjastaBibtex() {
        String sarja = this.viite.getSarja();

        if (sarja.isEmpty()) {
            return "";
        }
        
        return "series = {" + sarja + "},\n";
    }
    
    public String teePainoksestaBibtex() {
        String painos = this.viite.getPainos();
        
        if (painos.isEmpty()) {
            return "";
        }
        
        return "edition = {" + painos + "},\n";
    }
    
    public String teeLisatiedostaBibtex() {
        String tieto = this.viite.getLisatieto();
        
        if(tieto.isEmpty()) {
            return "";
        }
        
        return "note = {" + tieto + "},\n";
    }
}
