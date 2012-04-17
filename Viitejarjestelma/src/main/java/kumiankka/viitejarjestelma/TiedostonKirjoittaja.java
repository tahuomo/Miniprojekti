package kumiankka.viitejarjestelma;

import java.io.FileWriter;
import java.io.IOException;

public class TiedostonKirjoittaja {    
    public TiedostonKirjoittaja() {
        
    }
    
    public boolean kirjoitaTiedostoon(String teksti, String tiedostonimi) {
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
