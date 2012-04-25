
package viitejarjestelma;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import viitejarjestelma.logiikka.*;

public class HakuTest {
    HakuJulkaisijalla hakuj;
    HakuNimella hakun;
    HakuOtsikolla hakuo;
    HakuTunnisteella hakut;
    HakukomentoTehdas hkt;
    Viite v;
    Viite v2;
    List<Kirjoittaja> kirjoittajat;
    List<Viite> viitteet;
    List<Tagi> tagit;
    
    public HakuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        hkt = new HakukomentoTehdas();
//        hakuj = new HakuJulkaisijalla();
//        hakun = new HakuNimella();
//        hakuo = new HakuOtsikolla();
//        hakut = new HakuTunnisteella();
        
        kirjoittajat = new ArrayList<Kirjoittaja>();
        kirjoittajat.add(new Kirjoittaja("Arto", "Vihavainen"));
        kirjoittajat.add(new Kirjoittaja("Matti", "Luukkainen"));
        
        tagit = new ArrayList<Tagi>();
        tagit.add(new Tagi("hieno"));
        
        
        v = new Viite("article");
        v.setJulkaisija("ACM");
        v.setTunniste("AABB20");
        v.setOtsikko("Testi otsikko on hieno otsikko");
        v.setKirjoittajat(kirjoittajat);
        v.setTagit(tagit);
        
        v2 = new Viite("book");
        v2.setJulkaisija(null);
        v2.setTunniste("ATT20");
        v2.setOtsikko("Eri otsikko");
        v2.setKirjoittajat(new ArrayList<Kirjoittaja>());
        
        viitteet = new ArrayList<Viite>();
        viitteet.add(v);
        viitteet.add(v2);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void tunnisteellaHakuLoytaa() {
         assertEquals(1, hkt.hae("id").suorita(viitteet, "AABB20").size());
     }
     
     @Test
     public void otsikollaHakuLoytaa() {
         assertEquals(1, hkt.hae("title").suorita(viitteet, "otsikko on").size());
     }
     
     @Test
     public void nimellaHakuLoytaa() {
         assertEquals(1, hkt.hae("author").suorita(viitteet, "vihavainen").size());
     }
     
     @Test
     public void julkaisijallaHakuLoytaa() {
         assertEquals(1, hkt.hae("publisher").suorita(viitteet, "acm").size());
     }
     
     @Test
     public void tyyppiHakuLoytaa() {
         assertEquals(1, hkt.hae("type").suorita(viitteet, "article").size());
     }
     
     @Test
     public void tuntematonHakuPalauttaKaikki() {
         assertEquals(2, hkt.hae("asdfaf").suorita(viitteet, "acm").size());
     }
     
     @Test
     public void tagiHakuLoytaa() {
         assertEquals(1, hkt.hae("tag").suorita(viitteet, "hieno").size());
     }
}
