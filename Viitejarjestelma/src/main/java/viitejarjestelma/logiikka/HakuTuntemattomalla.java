
package viitejarjestelma.logiikka;


public class HakuTuntemattomalla extends Hakukomento{

    @Override
    protected boolean onHaluttu(Viite v, String hakusana) {
        //palautetaan koko lista, jos tuntematon komento
        return true;
    }
    
}
