package viitejarjestelma;

import viitejarjestelma.logiikka.SkandienKorvaaja;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SkandienKorvaajaTest {
    public SkandienKorvaajaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void pikkuAOikein() {
        assertEquals(SkandienKorvaaja.skanditBibtexYstavallisiksi("Mä"), "M\\\"{a}");
    }
    
    @Test
    public void isoAOikein() {
        assertEquals(SkandienKorvaaja.skanditBibtexYstavallisiksi("MÄ"), "M\\\"{A}");
    }
    
    @Test
    public void pikkuOOikein() {
        assertEquals(SkandienKorvaaja.skanditBibtexYstavallisiksi("Mö"), "M\\\"{o}");
    }
    
    @Test
    public void isoOOikein() {
        assertEquals(SkandienKorvaaja.skanditBibtexYstavallisiksi("MÖ"), "M\\\"{O}");
    }
    
    @Test
    public void pikkuRuotsOOikein() {
        assertEquals(SkandienKorvaaja.skanditBibtexYstavallisiksi("Må"), "M\\aa");
    }
    
    @Test
    public void isoRuotsOOikein() {
        assertEquals(SkandienKorvaaja.skanditBibtexYstavallisiksi("MÅ"), "M\\AA");
    }
}
