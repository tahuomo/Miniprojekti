package kumiankka.viitejarjestelma;

import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BibTexGeneraattoriTest {
    private Viite viite;
    private Viite tyhjaViite;
    private BibTexGeneraattori bg;
    
    public BibTexGeneraattoriTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        this.bg = new BibTexGeneraattori();
        this.viite = new Viite("tyyppi");
        this.tyhjaViite = new Viite("tyyppi");
        
        List<Kirjoittaja> kirjoittajat = new ArrayList<Kirjoittaja>();
        kirjoittajat.add(new Kirjoittaja("Matti", "Luukkainen"));
        kirjoittajat.add(new Kirjoittaja("Arto", "Vihavainen"));
        
        this.viite.setKirjoittajat(kirjoittajat);
        this.tyhjaViite.setKirjoittajat(kirjoittajat);
        
        this.viite.setAloitusSivu(1);
        this.viite.setJulkaisija("Julkaisija");
        this.viite.setKirjanNimi("Kirjan nimi");
        this.viite.setKuukausi(2);
        this.viite.setLehdenNimi("Lehden nimi");
        this.viite.setLehdenNumero(1);
        this.viite.setLisatieto("Testaaminen on kivaa");
        this.viite.setOrganisaatio("Organisaatio");
        this.viite.setOsoite("Osoite");
        this.viite.setOtsikko("Otsikko");
        this.viite.setPainos("Painos");
        this.viite.setSarja("Sarja");
        this.viite.setTunniste("LV12");
        this.viite.setVikaSivu(3);
        this.viite.setVuosi(2012);
        
        this.tyhjaViite.setAloitusSivu(0);
        this.tyhjaViite.setJulkaisija(null);
        this.tyhjaViite.setKirjanNimi(null);
        this.tyhjaViite.setKuukausi(0);
        this.tyhjaViite.setLehdenNimi(null);
        this.tyhjaViite.setLehdenNumero(0);
        this.tyhjaViite.setLisatieto(null);
        this.tyhjaViite.setOrganisaatio(null);
        this.tyhjaViite.setOsoite(null);
        this.tyhjaViite.setOtsikko("Otsikko");
        this.tyhjaViite.setPainos(null);
        this.tyhjaViite.setSarja(null);
        this.tyhjaViite.setTunniste("VL12");
        this.tyhjaViite.setVikaSivu(0);
        this.tyhjaViite.setVuosi(2012);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void viitteenBibtexStringGeneroidaanOikein() {
        String bibtex = "\n@tyyppi{LV12,\n"
                + "\tauthor = {Luukkainen, Matti and Vihavainen, Arto},\n"
                + "\ttitle = {Otsikko},\n"
                + "\tjournal = {Lehden nimi},\n"                
                + "\tpublisher = {Julkaisija},\n"
                + "\tyear = {2012},\n"
                + "\tmonth = {2},\n"
                + "\tpages = {1--3},\n"
                + "\tvolume = {1},\n"
                + "\taddress = {Osoite},\n"
                + "\torganization = {Organisaatio},\n"
                + "\tbooktitle = {Kirjan nimi},\n"
                + "\tseries = {Sarja},\n"
                + "\tedition = {Painos},\n"
                + "\tnote = {Testaaminen on kivaa},\n"
                + "}\n";
        
        assertEquals(this.bg.teeViitteestaBibtex(this.viite), bibtex);
    }
    
    @Test
    public void tyhjakenttaisenViitteenBibtexStringGeneroidaanOikein() {
        String bibtex = "\n@tyyppi{VL12,\n"
                + "\tauthor = {Luukkainen, Matti and Vihavainen, Arto},\n"
                + "\ttitle = {Otsikko},\n"
                + "\tyear = {2012},\n"
                + "}\n";
        
        assertEquals(this.bg.teeViitteestaBibtex(this.tyhjaViite), bibtex);
    }
    
    @Test
    public void bibtexStringOikeinKunVikaSivuTyhja() {
        this.viite.setVikaSivu(0);
        String bibtex = "\n@tyyppi{LV12,\n"
                + "\tauthor = {Luukkainen, Matti and Vihavainen, Arto},\n"
                + "\ttitle = {Otsikko},\n"
                + "\tjournal = {Lehden nimi},\n"                
                + "\tpublisher = {Julkaisija},\n"
                + "\tyear = {2012},\n"
                + "\tmonth = {2},\n"
                + "\tpages = {1},\n"
                + "\tvolume = {1},\n"
                + "\taddress = {Osoite},\n"
                + "\torganization = {Organisaatio},\n"
                + "\tbooktitle = {Kirjan nimi},\n"
                + "\tseries = {Sarja},\n"
                + "\tedition = {Painos},\n"
                + "\tnote = {Testaaminen on kivaa},\n"
                + "}\n";
        
        assertEquals(this.bg.teeViitteestaBibtex(this.viite), bibtex);
    }
}
