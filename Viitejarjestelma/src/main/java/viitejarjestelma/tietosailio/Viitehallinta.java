package viitejarjestelma.tietosailio;

import java.util.List;
import javax.persistence.EntityManager;
import viitejarjestelma.logiikka.Viite;

public interface Viitehallinta {
    public void tallennaViite(Viite a);
    public List<Viite> listaaViitteet();
    public Viite etsiTunniste(String tunniste);
    public boolean poistaViite(String tunniste);
}
