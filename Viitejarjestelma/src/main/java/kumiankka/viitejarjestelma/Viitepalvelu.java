
package kumiankka.viitejarjestelma;

import java.util.List;

class Viitepalvelu {
    private Viitehallinta viitehallinta;
    private Viite viite;
    
    public Viitepalvelu(Viitehallinta viitehallinta){
        this.viitehallinta = viitehallinta;
    }
    
    public void teeViite(String tyyppi){
        viite = new Viite(tyyppi);
    }
    
    public void lisaaViitteenYleisetTiedot(String otsikko, int julkaisuvuosi){
        viite.setJulkaisija(otsikko);
        viite.setVuosi(julkaisuvuosi);
    }
    
    public void lisaaArtikkelinTiedot(String lehti, int lehdenNumero, int alkusivu, int vikasivu){
        viite.setLehdenNimi(lehti);
        viite.setLehdenNumero(lehdenNumero);
        viite.setAloitusSivu(alkusivu);
        viite.setVikaSivu(vikasivu);
    }
    
    public void lisaaKirjoittaja(String etunimi, String sukunimi){
        Kirjoittaja k = new Kirjoittaja(etunimi, sukunimi);
        k.setViite(viite);
        viite.lisaaKirjoittaja(k); 
    }
    
    public void tallennaViite(){
        viitehallinta.tallennaViite(viite);
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
