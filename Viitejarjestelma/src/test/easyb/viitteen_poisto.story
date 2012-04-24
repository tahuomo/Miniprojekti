import viitejarjestelma.*
import viitejarjestelma.io.*
import viitejarjestelma.logiikka.*
import viitejarjestelma.tietosailio.*
import static org.mockito.Mockito.*

description 'Kayttaja voi poistaa tietokannassa olevan viitteen'

scenario "kayttaja voi poistaa tietokannassa olevan viitteen", {
    given 'komento "poista" valittu', {
        mockTietokanta = mock(Viitehallinta.class) 
        io = new StubIO("poista", "V2012", "lopeta")
        vp = new Viitepalvelu(mockTietokanta, null, null)
        ui = new TekstiUI(io, vp) 
    }
    when 'tietokannasta loytyva tunniste syotetty', {
        when(mockTietokanta.poistaViite(anyString())).thenReturn(true)
        ui.run()
    }
    then 'viite poistetaan tietokannasta', {
        io.getPrintit().shouldHave("Viite poistettiin!")
    }
}

scenario "kayttaja ei voi poistaa viitetta jota ei ole", {
    given 'komento "poista" valittu', {
        mockTietokanta = mock(Viitehallinta.class) 
        io = new StubIO("poista", "V2012", "lopeta")
        vp = new Viitepalvelu(mockTietokanta, null, null)
        ui = new TekstiUI(io, vp) 
    }
    when 'tietokannasta puuttuva tunniste syotetty', {
        when(mockTietokanta.poistaViite(anyString())).thenReturn(false)
        ui.run()
    }
    then 'viitetta ei poisteta tietokannasta', {
        io.getPrintit().shouldHave("Poistoa ei tehty, tarkista tunniste.")
    }
}