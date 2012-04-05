package kumiankka.viitejarjestelma;

public class Kirjoittaja {
    private String etunimi;
    private String sukunimi;

    public Kirjoittaja() {
    }

    public Kirjoittaja(String etunimi, String sukunimi) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
    }

    public String toString() {
        return etunimi.charAt(0) + ". " + sukunimi;
    }
}
