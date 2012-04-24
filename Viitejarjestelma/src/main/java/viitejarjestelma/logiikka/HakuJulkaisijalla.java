
package viitejarjestelma.logiikka;

public class HakuJulkaisijalla extends Hakukomento {

    @Override
    protected boolean onHaluttu(Viite v, String hakusana) {
        String julkaisija = v.getJulkaisija();
        
        if (julkaisija == null) return false;
        if (julkaisija.equalsIgnoreCase(hakusana)) return true;
        
        return false; 
    }
    
}
