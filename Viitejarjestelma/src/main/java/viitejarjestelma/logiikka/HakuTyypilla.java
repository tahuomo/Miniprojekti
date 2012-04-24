
package viitejarjestelma.logiikka;

public class HakuTyypilla extends Hakukomento {

    @Override
    protected boolean onHaluttu(Viite v, String hakusana) {
        if (v.getTyyppi().equalsIgnoreCase(hakusana)) return true;
        return false;
    }
    
}
