package kumiankka.viitejarjestelma;

public class Kirjoittaja {
    private String etunimi;
    private String sukunimi;
    private Viite viite;

    public Kirjoittaja() {
    }

    public Kirjoittaja(String etunimi, String sukunimi) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
    }

    public void setViite(Viite viite) {
        this.viite = viite;
    }

    public String toString() {
        return etunimi.charAt(0) + ". " + sukunimi;
    }
}
