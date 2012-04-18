package kumiankka.viitejarjestelma;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class ViitePalveluTest {
    private Viitepalvelu viitepalvelu;
    private Viitekirjanpito tallennusStub;
    private Viite viite;

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
        this.viitepalvelu = new Viitepalvelu(this.tallennusStub, null, null);

        this.viitepalvelu.teeViite("artikkeli");
        this.viitepalvelu.lisaaViitteenYleisetTiedot("Otsikko", 2012);
        this.viitepalvelu.lisaaArtikkelinTiedot("Matin sanomat", 1, 2, 12, "ACM");
        this.viitepalvelu.lisaaKirjoittaja("Matti", "Luukkainen");

        this.viite = this.viitepalvelu.getViite();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriLuoOlion() {
        assertNotNull(this.viitepalvelu);
    }

    @Test
    public void teeViiteLuoOlion() {
        assertNotNull(this.viitepalvelu.getViite());
    }

    @Test
    public void teeViiteLuoOikeantyyppisenViitteen() {
        assertEquals(this.viite.getTyyppi(), "artikkeli");
    }

    @Test
    public void viitteenOtsikkoLisataanOikein() {
        assertEquals(this.viite.getOtsikko(), "Otsikko");
    }

    @Test
    public void viitteenVuosiLisataanOikein() {
        assertTrue(this.viite.getVuosi() == 2012);
    }

    @Test
    public void artikkelinLehtiOikein() {
        assertEquals(this.viite.getLehdenNimi(), "Matin sanomat");
    }

    @Test
    public void artikkelinLehdenNumeroOikein() {
        assertTrue(this.viite.getLehdenNumero() == 1);
    }

    @Test
    public void artikkelinAlkusivuOikein() {
        assertTrue(this.viite.getAloitusSivu() == 2);
    }

    @Test
    public void artikkelinVikaSivuOikein() {
        assertTrue(this.viite.getVikaSivu() == 12);
    }

    @Test
    public void artikkelinJulkaisijaOikein() {
        assertEquals(this.viite.getJulkaisija(), "ACM");
    }

    @Test
    public void kirjoittajaLisataanOikein() {
        List<Kirjoittaja> k = this.viite.getKirjoittajat();

        assertTrue(k.size() == 1
                && k.get(0).toString().equals("M. Luukkainen"));
    }

    @Test
    public void viitteenTallennusToimii() {
        this.viitepalvelu.tallennaViite();

        assertEquals(this.viite, this.tallennusStub.viitteet.get(0));
    }

    @Test
    public void olemattomienViitteidenListausToimii() {
        assertEquals(this.viitepalvelu.listaaViitteet(), "");
    }

    @Test
    public void viitteidenListausToimii() {
        this.viitepalvelu.tallennaViite();

        assertEquals(this.viitepalvelu.listaaViitteet(), "[1] " + this.viite.toString() + "\n");
    }
}
