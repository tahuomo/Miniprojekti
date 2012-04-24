
package viitejarjestelma.logiikka;

import java.util.HashMap;

public class HakukomentoTehdas {
    HashMap<String, Hakukomento> komennot;
    
    public HakukomentoTehdas(){
        komennot = new HashMap<String, Hakukomento>();
        komennot.put("author", new HakuNimella());
        komennot.put("publisher", new HakuJulkaisijalla());
        komennot.put("title", new HakuOtsikolla());
        komennot.put("id", new HakuTunnisteella());
        komennot.put("type", new HakuTyypilla());
        komennot.put("tuntematon", new HakuTuntemattomalla());
    }
    
    public Hakukomento hae(String haettava){
        Hakukomento haku = komennot.get(haettava.toLowerCase());
        if (haku == null){
            return komennot.get("tuntematon");
        }
        return haku;
    }
    
}
