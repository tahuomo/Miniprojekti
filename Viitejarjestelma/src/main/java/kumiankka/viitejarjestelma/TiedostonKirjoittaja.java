package kumiankka.viitejarjestelma;

import java.io.FileWriter;
import java.io.IOException;

public class TiedostonKirjoittaja {
    private static final String tiedostopaate = ".bib";
    public TiedostonKirjoittaja() {
        
    }
    
    public boolean kirjoitaTiedostoon(String teksti, String tiedostonimi) {
        tiedostonimi += tiedostopaate;
        try {
            FileWriter kirjoittaja = new FileWriter(tiedostonimi);
            kirjoittaja.write(teksti);
            kirjoittaja.close();
        } catch (IOException ex) {
            return false;
        }
        
        return true;
    }
}
