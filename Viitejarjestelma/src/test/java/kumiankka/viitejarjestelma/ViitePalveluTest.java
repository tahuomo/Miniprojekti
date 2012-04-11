
package kumiankka.viitejarjestelma;

import org.junit.*;
import static org.junit.Assert.*;

public class ViitePalveluTest {
    private Viitekirjanpito tallennusStub;
    
    
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
        tallennusStub = new Viitekirjanpito();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
         assertTrue(true);
     }
}
