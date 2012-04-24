package viitejarjestelma.tietosailio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import viitejarjestelma.logiikka.Viite;

public class Viitekirjanpito implements Viitehallinta {
    private List<Viite> viitteet;

    public Viitekirjanpito() {
        viitteet = new ArrayList<Viite>();
    }

    public void tallennaViite(Viite a) {
        viitteet.add(a);
    }

    public List<Viite> listaaViitteet() {
        return viitteet;
    }


    @Override
    public Viite etsiTunniste(String tunniste) {
        for (Viite v: viitteet){
            if (v.getTunniste().equals(tunniste)) return v;
        }
        return null;
    }

    public List<Viite> getViitteet() {
        return viitteet;
    }

    @Override
    public boolean poistaViite(String tunniste) {
        Viite poistettava = etsiTunniste(tunniste);
        
        if(poistettava == null) {
            return false;
        }
        
        viitteet.remove(poistettava);
        return true;
    }
}
