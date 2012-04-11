package kumiankka.viitejarjestelma;

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
        this.viitepalvelu = new Viitepalvelu(this.tallennusStub);
        
        this.viitepalvelu.teeViite("artikkeli");
        this.viitepalvelu.lisaaViitteenYleisetTiedot("Otsikko", 2012);
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
}
