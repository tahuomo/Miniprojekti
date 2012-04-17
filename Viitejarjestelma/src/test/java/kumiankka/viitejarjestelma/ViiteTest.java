package kumiankka.viitejarjestelma;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ViiteTest {
    Viite a;
    ArrayList<Kirjoittaja> kirjoittajat;
    
    public ViiteTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        kirjoittajat = new ArrayList<Kirjoittaja>();
        Kirjoittaja k = new Kirjoittaja("Matti", "Luukkainen");
        kirjoittajat.add(k);
        a = new Viite("article");
        a.setLehdenNimi("Matin sanomat");
        a.setOtsikko("Matin Seikkailut");
        a.setJulkaisija("ACM");
        a.setVuosi(2012);
        a.setLehdenNumero(7);
        a.setAloitusSivu(1);
        a.setVikaSivu(13);
        a.setKirjoittajat(kirjoittajat);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tyhjaKonstruktoriLuoOlion() {
        a = new Viite();

        assertNotNull(a);
    }
    
    @Test
    public void konstruktoriLuoOlion() {        
        assertNotNull(a);
    }
    
    @Test
    public void kirjoittajatTulevat() {
        assertNotNull(a.getKirjoittajat());
    }
    
//    @Test
//    public void toStringToimiiYhdellaKirjoittajalla() {
//        String tulos = "article: M. Luukkainen Matin Seikkailut. Matin sanomat (ACM), "
//                    +  "7:1-13, 2012";
//        assertEquals(tulos, a.toString());
//    }
//    
//    @Test
//    public void toStringToimiiUseammallaKirjoittajalla() {
//        kirjoittajat.add(new Kirjoittaja("Arto", "Vihavainen"));
//        
//        String tulos = "article: M. Luukkainen, A. Vihavainen Matin Seikkailut. Matin sanomat (ACM), "
//                    +  "7:1-13, 2012";
//        assertEquals(tulos, a.toString());
//    }
}
