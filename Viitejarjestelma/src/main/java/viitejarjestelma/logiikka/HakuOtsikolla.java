
package viitejarjestelma.logiikka;

public class HakuOtsikolla extends Hakukomento {

    @Override
    protected boolean onHaluttu(Viite v, String hakusana) {
        String otsikko = v.getOtsikko().toLowerCase();
        if (otsikko.contains(hakusana.toLowerCase())) return true;
        return false; 
    }
    
}
