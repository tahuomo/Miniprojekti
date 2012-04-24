
package viitejarjestelma.logiikka;

import java.util.List;

public class HakuNimella extends Hakukomento{

    @Override
    protected boolean onHaluttu(Viite v, String hakusana) {
        List<Kirjoittaja> kirjoittajat = v.getKirjoittajat();
        for (Kirjoittaja k : kirjoittajat){
            if (k.getEtunimi().equalsIgnoreCase(hakusana)) return true;
            if (k.getSukunimi().equalsIgnoreCase(hakusana)) return true;
        }
        return false;
    }
    
}
