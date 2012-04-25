
package viitejarjestelma;

import org.junit.*;
import static org.junit.Assert.*;
import viitejarjestelma.logiikka.Tagi;


public class TagiTest {
    Tagi t;
    
    public TagiTest() {
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
     public void tyhjaKonstruktoriToimii() {
         t = new Tagi();
         
         assertNotNull(t);
     }
     
     @Test
     public void oikeaKonstruktoriAsettaaTaginNimen() {
         t = new Tagi("Nimi");
         
         assertEquals("Nimi", t.getNimi());
     }
}
