package viitejarjestelma.logiikka;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Tagi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String nimi;
    @ManyToOne
    @JoinColumn
    private Viite viite;

    public Tagi() {
    }

    public Tagi(String nimi) {
        this.nimi = nimi;
    }

    public void setViite(Viite viite) {
        this.viite = viite;
    }

    public String getNimi() {
        return nimi;
    }
}
