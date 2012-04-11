package kumiankka.viitejarjestelma;

import java.util.List;

public interface Viitehallinta {
    public void tallennaViite(Viite a);
    public List<Viite> listaaViitteet();
}
