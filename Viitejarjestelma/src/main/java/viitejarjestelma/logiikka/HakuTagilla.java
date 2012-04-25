
package viitejarjestelma.logiikka;

public class HakuTagilla extends Hakukomento {

    @Override
    protected boolean onHaluttu(Viite v, String hakusana) {
        for (Tagi t : v.getTagit()){
            if (t.getNimi().equalsIgnoreCase(hakusana)){
                return true;
            }
        }
        return false;
    }
    
}
