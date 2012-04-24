package viitejarjestelma.logiikka;

import javax.persistence.*;

@Entity
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