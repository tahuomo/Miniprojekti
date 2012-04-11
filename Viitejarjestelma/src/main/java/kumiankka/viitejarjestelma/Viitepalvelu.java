
package kumiankka.viitejarjestelma;

import java.util.List;

class Viitepalvelu {
    private Viitehallinta viitehallinta;
    
    public Viitepalvelu(Viitehallinta viitehallinta){
        this.viitehallinta = viitehallinta;
    }
    
    

    public String listaaViitteet() {
        List<Viite> viitteet = viitehallinta.listaaViitteet();
        String viitelista = "";
        for (int i = 0; i < viitteet.size(); i++){
            viitelista += i + " " + viitteet.get(i).toString() + "\n";
        }
        
        return viitelista;
    }
}
