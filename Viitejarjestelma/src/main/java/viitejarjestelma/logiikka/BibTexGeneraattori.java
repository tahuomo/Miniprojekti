package viitejarjestelma.logiikka;

import viitejarjestelma.logiikka.Kirjoittaja;
import viitejarjestelma.logiikka.Viite;
import java.util.List;

public class BibTexGeneraattori {

    public BibTexGeneraattori() {
    }

    public String teeViitteestaBibtex(Viite viite) {
        String bibtex = "\n@" + viite.getTyyppi() + "{" + viite.getTunniste() + ",\n";
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

        return SkandienKorvaaja.skanditBibtexYstavallisiksi(bibtex);
    }

    private String teeKirjoittajistaBibtex(Viite viite) {
        String bibtex = "\tauthor = {";
        List<Kirjoittaja> kirjoittajat = viite.getKirjoittajat();

        for (int i = 0; i < kirjoittajat.size(); i++) {
            bibtex += this.teeKirjoittajastaBibtex(kirjoittajat.get(i));

            if (i < kirjoittajat.size() - 1) {
                bibtex += " and ";
            }
        }
        return bibtex += "},\n";
    }

    private String teeKirjoittajastaBibtex(Kirjoittaja kirjoittaja) {
        return kirjoittaja.getSukunimi() + ", " + kirjoittaja.getEtunimi();
    }

    private String teeOtsikostaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("title", viite.getOtsikko());
    }

    private String teeJulkaisijastaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("publisher", viite.getJulkaisija());
    }

    private String teeLehdenNimestaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("journal", viite.getLehdenNimi());
    }

    private String teeVuosiluvustaBibtex(Viite viite) {
        return this.intTiedostaBibtex("year", viite.getVuosi());
    }

    private String teeKuukaudestaBibtex(Viite viite) {
        return this.intTiedostaBibtex("month", viite.getKuukausi());
    }

    private String teeSivunumeroistaBibtex(Viite viite) {
        int alku = viite.getAloitusSivu();
        int loppu = viite.getVikaSivu();

        if (alku == 0) {
            return "";
        }
        if (loppu == 0) {
            return this.intTiedostaBibtex("pages", alku);
        }

        return this.stringTiedostaBibtex("pages", alku + "--" + loppu);
    }

    private String teeLehdenNumerostaBibtex(Viite viite) {
        return this.intTiedostaBibtex("volume", viite.getLehdenNumero());
    }

    private String teeOsoitteestaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("address", viite.getOsoite());
    }

    private String teeOrganisaatiostaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("organization", viite.getOrganisaatio());
    }

    private String teeKirjanNimestaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("booktitle", viite.getKirjanNimi());
    }

    private String teeSarjastaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("series", viite.getSarja());
    }

    private String teePainoksestaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("edition", viite.getPainos());
    }

    private String teeLisatiedostaBibtex(Viite viite) {
        return this.stringTiedostaBibtex("note", viite.getLisatieto());
    }

    private String stringTiedostaBibtex(String kentta, String tieto) {
        if (tieto == null) {
            return "";
        }

        return "\t" + kentta + " = {" + tieto + "},\n";
    }

    private String intTiedostaBibtex(String kentta, int tieto) {
        if (tieto == 0) {
            return "";
        }
        return this.stringTiedostaBibtex(kentta, tieto + "");
    }
}
