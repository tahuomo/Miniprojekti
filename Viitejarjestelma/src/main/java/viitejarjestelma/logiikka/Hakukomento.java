
package viitejarjestelma.logiikka;

import java.util.ArrayList;
import java.util.List;

public abstract class Hakukomento {

    private List<Viite> haetut;

    public Hakukomento() {};

    public List<Viite> suorita(List<Viite> kaikkiViitteet, String haettava) {
        haetut = new ArrayList<Viite>();
        for (Viite v : kaikkiViitteet) {
            if (onHaluttu(v, haettava)) {
                haetut.add(v);
            }
        }
        return haetut;
    }

    protected abstract boolean onHaluttu(Viite v, String hakusana);
}
