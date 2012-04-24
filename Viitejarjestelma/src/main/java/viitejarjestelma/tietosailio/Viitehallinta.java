package viitejarjestelma.tietosailio;

import java.util.List;
import viitejarjestelma.logiikka.Viite;

public interface Viitehallinta {
    public void tallennaViite(Viite a);
    public List<Viite> listaaViitteet();
    public Viite etsiTunniste(String tunniste);
}
