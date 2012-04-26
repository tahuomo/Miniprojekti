package viitejarjestelma.logiikka;

public class SkandienKorvaaja {
    public static String skanditBibtexYstavallisiksi(String teksti) {
        teksti = teksti.replaceAll("ä", "\\\\\"{a}");
        teksti = teksti.replaceAll("ö", "\\\\\"{o}");
        teksti = teksti.replaceAll("Ä", "\\\\\"{A}");
        teksti = teksti.replaceAll("Ö", "\\\\\"{O}");
        teksti = teksti.replaceAll("å", "\\\\aa");
        teksti = teksti.replaceAll("Å", "\\\\AA");
        
        return teksti;
    }
}
