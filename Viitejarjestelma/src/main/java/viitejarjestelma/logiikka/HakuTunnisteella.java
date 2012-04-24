
package viitejarjestelma.logiikka;

public class HakuTunnisteella extends Hakukomento {

    @Override
    protected boolean onHaluttu(Viite v, String hakusana) {
         if (v.getTunniste().equals(hakusana)) return true;
         return false;
    }
    
}
