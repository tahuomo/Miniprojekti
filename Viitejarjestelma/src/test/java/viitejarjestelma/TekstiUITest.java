package viitejarjestelma;

import viitejarjestelma.logiikka.Viitepalvelu;
import viitejarjestelma.io.IO;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class TekstiUITest {
    Viitepalvelu mockvp;
    IO mockio;
    TekstiUI ui;
    
    public TekstiUITest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        mockvp = mock(Viitepalvelu.class);
        mockio = mock(IO.class);
        ui = new TekstiUI(mockio, mockvp);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void lisaaYleisetTiedot() {
         when(mockio.lueSyote(anyString(), anyBoolean())).thenReturn("Otsikko");
         when(mockio.lueLuku(anyString(), anyBoolean())).thenReturn(2012);
         
         ui.kysyYleisetTiedot();
         
         verify(mockvp).lisaaViitteenYleisetTiedot(eq("Otsikko"), eq(2012));
     
     }
     
     @Test
     public void lisaaKirjanTiedot() {
         when(mockio.lueSyote(anyString(), anyBoolean())).thenReturn("julkaisija")
                 .thenReturn(null).thenReturn(null).thenReturn(null);
         
         ui.kysyKirjanTiedot();
        verify(mockvp).lisaaKirjanTiedot(eq("julkaisija"),anyString(), anyString(), anyString());
    }
     
     @Test
     public void lisaaArtikkelinTiedot(){
         when(mockio.lueSyote(anyString(), anyBoolean())).thenReturn("Lehti").thenReturn(null);
         when(mockio.lueLuku(anyString(), anyBoolean())).thenReturn(1).thenReturn(2).thenReturn(3);
         
         ui.kysyArtikkelinTiedot();
         
         verify(mockvp).lisaaArtikkelinTiedot(eq("Lehti"),eq(1), eq(2), eq(3), anyString());
    }
     
     @Test
     public void lisaaKonfJulkaisunTiedot(){
         when(mockio.lueSyote(anyString(), anyBoolean()))
                 .thenReturn("Kirja")
                 .thenReturn("julkaisija")
                 .thenReturn("osoite")
                 .thenReturn("sarja")
                 .thenReturn("organisatio");
         when(mockio.lueLuku(anyString(), anyBoolean())).thenReturn(1).thenReturn(2);
         
         ui.kysyKjulkaisunTiedot();
         
         verify(mockvp).lisaaKonferenssijulkaisunTiedot(eq("Kirja"),eq("julkaisija")
                                            ,eq("osoite"), eq("sarja"), eq(1), eq(2), eq("organisatio"));
    }
     
     @Test
     public void lisaaValinnaisetTiedot(){
         when(mockio.lueSyote(anyString(), anyBoolean())).thenReturn("lisätiedot");
         when(mockio.lueLuku(anyString(), anyBoolean())).thenReturn(1);
         
         ui.kysyValinnaisetTiedot();
         
         verify(mockvp).lisaaValinnaisetTiedot(eq(1), eq("lisätiedot"));
    }
     
     @Test
     public void kerrotaanOikeatKomennot(){
         String komennot = "\tauta\t- näyttää komennot\n"
                + "\tuusi\t- lisää uusi viite\n"
                + "\tbibtex\t- tallenna viitteet bibtex-tiedostoon\n"
                + "\tlistaa\t- listaa viitteet\n"
                + "\tpoista\t- poista viite\n"
                + "\tlopeta\t- lopeta ohjelma";
         
         ui.kerroKomennot();
         verify(mockio).tulosta(komennot);
     }
     
     @Test
     public void listataanOlemattomatViitteet(){
         when(mockvp.listaaViitteet()).thenReturn("");
         ui.listaaViitteet();
         
         verify(mockio).tulosta(eq("Ei vielä lisättyjä viitteitä"));
         verify(mockvp).listaaViitteet();
     }
     
     @Test
     public void listataanViitteet(){
         when(mockvp.listaaViitteet()).thenReturn("jee jee");
         ui.listaaViitteet();
         
         verify(mockio).tulosta(eq("jee jee"));
         verify(mockvp).listaaViitteet();
     }
     
     @Test
     public void kayttajaEiAnnaTunnistetta(){
         when(mockio.lueSyote(anyString(), anyBoolean())).thenReturn(null);
         when(mockvp.generoiTunniste()).thenReturn("R2-D2");
         
         ui.kysyTunniste();
         
         verify(mockvp).lisaaViitteenTunniste("R2-D2");
        
     }
     
     @Test
     public void kayttajaAntaaToimivanTunnisteen(){
         when(mockio.lueSyote(anyString(), anyBoolean())).thenReturn("C-3PO");
         when(mockvp.generoiTunniste()).thenReturn("R2-D2");
         when(mockvp.tunnisteKelpaa(anyString())).thenReturn(true);
         
         ui.kysyTunniste();
         
         verify(mockvp).lisaaViitteenTunniste("C-3PO");
        
     }
     
     @Test
     public void kayttajaSyottaaVaratunTunnisteen(){
         when(mockio.lueSyote(anyString(), anyBoolean())).thenReturn("C-3PO").thenReturn(null);
         when(mockvp.generoiTunniste()).thenReturn("R2-D2");
         when(mockvp.tunnisteKelpaa(anyString())).thenReturn(false);
         
         ui.kysyTunniste();
         
         verify(mockio).tulosta("Tunniste ei uniikki.");
         verify(mockvp).lisaaViitteenTunniste("R2-D2");
        
     }
     
     @Test
     public void bibtexinKirjoittaminenOngelmitta(){
         when(mockvp.bibtexTiedostoon(anyString())).thenReturn(true);
         when(mockio.lueSyote(anyString(), anyBoolean())).thenReturn("file");
         
         ui.kirjoitaBibtexTiedosto();
         
         verify(mockvp).bibtexTiedostoon("file");
         verify(mockio).tulosta("Valmis!");
     }
     
     @Test
     public void bibtexinKirjoittaminenEiOnnistu(){
         when(mockvp.bibtexTiedostoon(anyString())).thenReturn(false);
         when(mockio.lueSyote(anyString(), anyBoolean())).thenReturn("file");
         
         ui.kirjoitaBibtexTiedosto();
         
         verify(mockvp).bibtexTiedostoon("file");
         verify(mockio).tulosta("Virhe tiedostoa kirjoittaessa.");
     }
     
     @Test
     public void viitteenTyypitOikein() {
         when(mockio.lueLuku(anyString(), anyBoolean())).thenReturn(1).thenReturn(2).thenReturn(3);
        
         assertEquals(ui.kysyViitteenTyyppi(), "article");
         assertEquals(ui.kysyViitteenTyyppi(), "inproceedings");
         assertEquals(ui.kysyViitteenTyyppi(), "book");
    }
     
     @Test
     public void kayttajaAntaaYhdenKirjoittajan(){
         when(mockio.lueSyote(anyString(), anyBoolean()))
                 .thenReturn("Matti")
                 .thenReturn("Luukkainen")
                 .thenReturn(null);
         
         ui.kysyKirjoittajat();
         verify(mockvp, times(1)).lisaaKirjoittaja(anyString(), anyString());
     }
     
     @Test
     public void kayttajaAntaaUseanKirjoittajan(){
         when(mockio.lueSyote(anyString(), anyBoolean()))
                 .thenReturn("Matti")
                 .thenReturn("Luukkainen")
                 .thenReturn("Arto")
                 .thenReturn("Vihavainen")
                 .thenReturn(null);
         
         ui.kysyKirjoittajat();
         verify(mockvp, times(2)).lisaaKirjoittaja(anyString(), anyString());
     }

}
