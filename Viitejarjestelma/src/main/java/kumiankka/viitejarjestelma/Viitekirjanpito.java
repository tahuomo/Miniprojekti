package kumiankka.viitejarjestelma;

import java.util.ArrayList;
import java.util.List;

public class Viitekirjanpito implements Viitehallinta {
    List<Viite> viitteet;

    public Viitekirjanpito() {
        viitteet = new ArrayList<Viite>();
    }

    public void tallennaViite(Viite a) {
        viitteet.add(a);
    }

    public List<Viite> listaaViitteet() {
        return viitteet;
    }
}
