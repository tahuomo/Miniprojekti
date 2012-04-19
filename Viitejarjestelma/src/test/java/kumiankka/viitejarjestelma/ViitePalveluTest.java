package kumiankka.viitejarjestelma;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ViitePalveluTest {
    private Tiedostonkasittely mockTallentaja;
    private BibTexGeneraattori bibtex;
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
        this.mockTallentaja = mock(Tiedostonkasittely.class);
        this.bibtex = new BibTexGeneraattori();        
        this.tallennusStub = new Viitekirjanpito();
        
        this.viitepalveluA = new Viitepalvelu(this.tallennusStub, this.bibtex, this.mockTallentaja);
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
        assertTrue(this.kirja.getOtsikko().equals("Otsikko")
                && this.kirja.getVuosi() == 2000);
    }

    @Test
    public void kirjanMuutTiedotOikein() {
        assertTrue(this.kirja.getJulkaisija().equals("Julkaisija")
                && this.kirja.getOsoite().equals("Osoite")
                && this.kirja.getPainos().equals("Painos")
                && this.kirja.getSarja().equals("Sarja"));
    }

    @Test
    public void konferenssiJulkaisunOmatTiedotOikein() {
        assertTrue(this.konf.getKirjanNimi().equals("kirja")
                && this.konf.getJulkaisija().equals("julkaisija")
                && this.konf.getOsoite().equals("osoite")
                && this.konf.getSarja().equals("sarja")
                && this.konf.getAloitusSivu() == 1
                && this.konf.getVikaSivu() == 2
                && this.konf.getOrganisaatio().equals("organisaatio"));
    }

    @Test
    public void valinnaisetTiedotLisataanOikein() {
        this.viitepalveluA.lisaaValinnaisetTiedot(1, "Lisatieto");

        assertTrue(this.artikkeli.getKuukausi() == 1
                && this.artikkeli.getLisatieto().equals("Lisatieto"));
    }

    @Test
    public void uniikkiTunnisteKelpaa() {
        assertTrue(this.viitepalveluA.tunnisteKelpaa("uniikkiTunniste"));
    }

    @Test
    public void olemassaOlevaTunnisteEiKelpaa() {
        this.artikkeli.setTunniste("Tunniste");
        this.viitepalveluA.tallennaViite();
        
        assertFalse(this.viitepalveluA.tunnisteKelpaa("Tunniste"));
    }

    @Test
    public void tunnisteGeneroidaanOikein() {
        String tunniste = this.viitepalveluA.generoiTunniste();
        
        assertEquals("L2012", tunniste);
    }
    
    @Test
    public void generoituTunnisteOnUniikki() {
        String tunniste = this.viitepalveluA.generoiTunniste();
        this.viitepalveluA.lisaaViitteenTunniste(tunniste);
        this.viitepalveluA.tallennaViite();
        this.viitepalveluA.teeViite("article");
        this.viitepalveluA.lisaaKirjoittaja("Matti", "Luuk");
        this.viitepalveluA.lisaaViitteenYleisetTiedot("Otsikko", 2012);
        this.viitepalveluA.lisaaArtikkelinTiedot("Lehti", 0, 0, 0, null);
        
        assertEquals(this.viitepalveluA.generoiTunniste(), "L2012a");
    }

    @Test
    public void tiedostonKirjoittajaaKutsutaanOikein() {
        this.viitepalveluA.teeViite("article");
        this.viitepalveluA.lisaaKirjoittaja("Matti", "Luukkainen");
        this.viitepalveluA.lisaaViitteenYleisetTiedot("otsikko", 2012);   
        this.viitepalveluA.lisaaArtikkelinTiedot("Lehti", 0, 0, 0, null);
        this.viitepalveluA.lisaaViitteenTunniste("tunniste");
        this.viitepalveluA.tallennaViite();
        this.viitepalveluA.bibtexTiedostoon("tiedostonimi");
        
        verify(this.mockTallentaja).kirjoitaTiedostoon(eq("\n@article{tunniste,\n" +
                "author = {Luukkainen, Matti},\n" +
                "title = {otsikko},\n" +
                "journal = {Lehti},\n"+
                "year = {2012},\n" +
                "}\n")
                , eq("tiedostonimi"));
    }
}
