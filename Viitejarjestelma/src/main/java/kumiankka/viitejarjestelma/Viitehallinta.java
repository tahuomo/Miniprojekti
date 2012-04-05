package kumiankka.viitejarjestelma;


import java.util.List;
import kumiankka.viitejarjestelma.Artikkeli;

public interface Viitehallinta {
    
    public void tallennaViite(Artikkeli a);
    public List<Artikkeli> listaaViitteet();
    
}
