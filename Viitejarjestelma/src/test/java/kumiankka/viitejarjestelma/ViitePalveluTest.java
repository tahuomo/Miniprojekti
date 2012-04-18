package kumiankka.viitejarjestelma;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class ViitePalveluTest {
    private Viitepalvelu viitepalveluA;
    private Viitepalvelu viitepalveluB;
    private Viitepalvelu viitepalveluC;
    private Viitekirjanpito tallennusStub;
    private Viite artikkeli;
    private Viite kirja;
    private Viite konf;

    public ViitePalveluTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        this.tallennusStub = new Viitekirjanpito();
        this.viitepalveluA = new Viitepalvelu(this.tallennusStub, null, null);
        this.viitepalveluB = new Viitepalvelu(this.tallennusStub, null, null);
        this.viitepalveluC = new Viitepalvelu(this.tallennusStub, null, null);

        this.viitepalveluA.teeViite("article");
        this.viitepalveluA.lisaaViitteenYleisetTiedot("Otsikko", 2012);
        this.viitepalveluA.lisaaArtikkelinTiedot("Matin sanomat", 1, 2, 12, "ACM");
        this.viitepalveluA.lisaaKirjoittaja("Matti", "Luukkainen");
        
        this.viitepalveluB.teeViite("book");
        this.viitepalveluB.lisaaViitteenYleisetTiedot("Otsikko", 2000);
        this.viitepalveluB.lisaaKirjanTiedot("Julkaisija", "Osoite", "Painos", "Sarja");
        this.viitepalveluB.lisaaKirjoittaja("M.", "Luukkainen");
        this.viitepalveluB.lisaaKirjoittaja("A.", "Vihavainen");
        
        this.viitepalveluC.teeViite("inproceedings");
        this.viitepalveluC.lisaaKonferenssijulkaisunTiedot("kirja", "julkaisija", "osoite", "sarja", 1, 2, "organisaatio");

        this.artikkeli = this.viitepalveluA.getViite();
        this.kirja = this.viitepalveluB.getViite();
        this.konf = this.viitepalveluC.getViite();
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriLuoOlion() {
        assertNotNull(this.viitepalveluA);
    }

    @Test
    public void teeViiteLuoOlion() {
        assertNotNull(this.viitepalveluA.getViite());
    }

    @Test
    public void teeViiteLuoOikeantyyppisenViitteen() {
        assertEquals(this.artikkeli.getTyyppi(), "article");
    }

    @Test
    public void viitteenOtsikkoLisataanOikein() {
        assertEquals(this.artikkeli.getOtsikko(), "Otsikko");
    }

    @Test
    public void viitteenVuosiLisataanOikein() {
        assertTrue(this.artikkeli.getVuosi() == 2012);
    }

    @Test
    public void artikkelinLehtiOikein() {
        assertEquals(this.artikkeli.getLehdenNimi(), "Matin sanomat");
    }

    @Test
    public void artikkelinLehdenNumeroOikein() {
        assertTrue(this.artikkeli.getLehdenNumero() == 1);
    }

    @Test
    public void artikkelinAlkusivuOikein() {
        assertTrue(this.artikkeli.getAloitusSivu() == 2);
    }

    @Test
    public void artikkelinVikaSivuOikein() {
        assertTrue(this.artikkeli.getVikaSivu() == 12);
    }

    @Test
    public void artikkelinJulkaisijaOikein() {
        assertEquals(this.artikkeli.getJulkaisija(), "ACM");
    }

    @Test
    public void kirjoittajaLisataanOikein() {
        List<Kirjoittaja> k = this.artikkeli.getKirjoittajat();

        assertTrue(k.size() == 1
                && k.get(0).toString().equals("M. Luukkainen"));
    }

    @Test
    public void viitteenTallennusToimii() {
        this.viitepalveluA.tallennaViite();

        assertEquals(this.artikkeli, this.tallennusStub.viitteet.get(0));
    }

    @Test
    public void olemattomienViitteidenListausToimii() {
        assertEquals(this.viitepalveluA.listaaViitteet(), "");
    }

    @Test
    public void viitteidenListausToimii() {
        this.viitepalveluA.tallennaViite();

        assertEquals(this.viitepalveluA.listaaViitteet(), "[1] " + this.artikkeli.toString() + "\n");
    }
   
    @Test
    public void kirjanYleisetTiedotOikein() {
        assertTrue(this.kirja.getOtsikko().equals("Otsikko") &&
                this.kirja.getVuosi() == 2000);
    }
    
    @Test
    public void kirjanMuutTiedotOikein() {
        assertTrue(this.kirja.getJulkaisija().equals("Julkaisija") &&
                this.kirja.getOsoite().equals("Osoite") &&
                this.kirja.getPainos().equals("Painos") &&
                this.kirja.getSarja().equals("Sarja"));
    }
    
    @Test
    public void konferenssiJulkaisunOmatTiedotOikein() {
        assertTrue(this.konf.getKirjanNimi().equals("kirja") &&
                this.konf.getJulkaisija().equals("julkaisija") &&
                this.konf.getOsoite().equals("osoite") &&
                this.konf.getSarja().equals("sarja")&&
                this.konf.getAloitusSivu() == 1 &&
                this.konf.getVikaSivu() == 2 &&
                this.konf.getOrganisaatio().equals("organisaatio"));
    }
    
    @Test
    public void valinnaisetTiedotLisataanOikein() {
        this.viitepalveluA.lisaaValinnaisetTiedot(1, "Lisatieto");
        
        assertTrue(this.artikkeli.getKuukausi() == 1 &&
                this.artikkeli.getLisatieto().equals("Lisatieto"));
    }
}
