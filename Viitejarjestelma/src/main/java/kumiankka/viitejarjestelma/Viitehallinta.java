package kumiankka.viitejarjestelma;

import java.util.List;

public interface Viitehallinta {
    public void tallennaViite(Artikkeli a);
    public List<Artikkeli> listaaViitteet();
}
