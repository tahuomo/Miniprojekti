package kumiankka.viitejarjestelma;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Kirjoittaja implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String etunimi;
    @Column
    private String sukunimi;
    @ManyToOne
    @JoinColumn
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
