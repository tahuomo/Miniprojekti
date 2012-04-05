/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kumiankka.viitejarjestelma;

import java.util.ArrayList;
import java.util.List;

public class Viitekirjanpito implements Viitehallinta {
    List<Artikkeli> viitteet;
    
    public Viitekirjanpito() {
        viitteet = new ArrayList<Artikkeli>();
    }  
    
    public void tallennaViite(Artikkeli a) {
        viitteet.add(a);
    }

    public List<Artikkeli> listaaViitteet() {
        return viitteet;
    }
    
}
