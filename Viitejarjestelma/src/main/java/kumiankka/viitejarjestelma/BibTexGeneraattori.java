package kumiankka.viitejarjestelma;

import java.util.List;

public class BibTexGeneraattori {
    public BibTexGeneraattori() {
    }

    public String teeViitteestaBibtex(Viite viite) {
        String bibtex = "@" + viite.getTyyppi() + "{" + viite.getTunniste() + ",\n";
        bibtex += this.teeKirjoittajistaBibtex(viite)
                + this.teeOtsikostaBibtex(viite)
                + this.teeLehdenNimestaBibtex(viite)
                + this.teeJulkaisijastaBibtex(viite)
                + this.teeVuosiluvustaBibtex(viite)
                + this.teeKuukaudestaBibtex(viite)
                + this.teeSivunumeroistaBibtex(viite)
                + this.teeLehdenNumerostaBibtex(viite)
                + this.teeOsoitteestaBibtex(viite)
                + this.teeOrganisaatiostaBibtex(viite)
                + this.teeKirjanNimestaBibtex(viite)
                + this.teeSarjastaBibtex(viite)
                + this.teePainoksestaBibtex(viite)
                + this.teeLisatiedostaBibtex(viite)
                + "}\n";
        return bibtex;
    }

    public String teeKirjoittajistaBibtex(Viite viite) {
        String bibtex = "author = {";
        List<Kirjoittaja> kirjoittajat = viite.getKirjoittajat();

        for (int i = 0; i < kirjoittajat.size(); i++) {
            bibtex += this.teeKirjoittajastaBibtex(kirjoittajat.get(i));

            if (i < kirjoittajat.size() - 1) {
                bibtex += " and ";
            }
        }
        return bibtex += "},\n";
    }

    public String teeKirjoittajastaBibtex(Kirjoittaja kirjoittaja) {
        return kirjoittaja.getSukunimi() + ", " + kirjoittaja.getEtunimi();
    }

    public String teeOtsikostaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("title", viite.getOtsikko());
    }

    public String teeJulkaisijastaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("publisher", viite.getJulkaisija());
    }

    public String teeLehdenNimestaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("journal", viite.getLehdenNimi());
    }

    public String teeVuosiluvustaBibtex(Viite viite) {
        return this.intTiedostaBibtex("year", viite.getVuosi());
    }

    public String teeKuukaudestaBibtex(Viite viite) {
        return this.intTiedostaBibtex("month", viite.getKuukausi());
    }

    public String teeSivunumeroistaBibtex(Viite viite) {
        int alku = viite.getAloitusSivu();
        int loppu = viite.getVikaSivu();

        if (alku == 0 || loppu == 0) {
            return "";
        }

        return "pages = {" + alku + "--" + loppu + "},\n";
    }

    public String teeLehdenNumerostaBibtex(Viite viite) {
        return this.intTiedostaBibtex("number", viite.getLehdenNumero());
    }

    public String teeOsoitteestaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("address", viite.getOsoite());
    }

    public String teeOrganisaatiostaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("organization", viite.getOrganisaatio());
    }

    public String teeKirjanNimestaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("booktitle", viite.getKirjanNimi());
    }

    public String teeSarjastaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("series", viite.getSarja());
    }

    public String teePainoksestaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("edition", viite.getPainos());
    }

    public String teeLisatiedostaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("note", viite.getLisatieto());
    }

    public String stringTiedostaBibtex(String kentta, String tieto) {
        if (tieto == null) {
            return "";
        }

        return kentta + " = {" + tieto + "},\n";
    }

    public String intTiedostaBibtex(String kentta, int tieto) {
        if (tieto == 0) {
            return "";
        }
        return this.stringTiedostaBibtex(kentta, tieto + "");
    }
}
