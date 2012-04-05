/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kumiankka.viitejarjestelma;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ArtikkeliTest {
    Artikkeli a;
    ArrayList<Kirjoittaja> kirjoittajat;
    
    public ArtikkeliTest() {
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
        a = new Artikkeli("Matin Seikkailut", "ACM", "Matin sanomat", 2012, 1, 13, 7, kirjoittajat);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tyhjaKonstruktoriLuoOlion() {
        a = new Artikkeli();

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
    
    @Test
    public void toStringToimii() {
        String tulos = "M. Luukkainen Matin Seikkailut. Matin sanomat (ACM), "
                    +  "7:1-13, 2012";
        assertEquals(tulos, a.toString());
    }
}
