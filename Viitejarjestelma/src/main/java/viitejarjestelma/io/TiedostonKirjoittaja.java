package viitejarjestelma.io;

import java.io.FileWriter;
import java.io.IOException;
import viitejarjestelma.io.Tiedostonkasittely;

public class TiedostonKirjoittaja implements Tiedostonkasittely {
    private static final String PAATE = ".bib";
    private static final String POLKU = "tietokanta/";
    public TiedostonKirjoittaja() {
        
    }
    
    @Override
    public boolean kirjoitaTiedostoon(String teksti, String tiedostonimi) {
        tiedostonimi = POLKU + tiedostonimi + PAATE;
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
