import viitejarjestelma.*
import viitejarjestelma.io.*
import viitejarjestelma.logiikka.*
import viitejarjestelma.tietosailio.*

description 'Viitteille voi syottaa useita kirjoittajia ja tageja'


scenario "kayttaja voi lisata kirjan usealla kirjoittajalla ja tagilla", {
    given 'komento "uusi" ja "3" valittu', {
        io = new StubIO("uusi", "3", "Otsikko", "2012", "Julkaisija", "Osoite", "Painos", "Sarja", "1", "Lisätieto", "Arto", "Vihavainen", "Matti", "Luukkainen", "", "", "tagi1", "tagi2", "", "lopeta")
        vk = new Viitekirjanpito()
        vp = new Viitepalvelu(vk, null, null)
        ui = new TekstiUI(io, vp)        
    }
    
    when 'validit tiedot syotetty usealla kirjoittajalla ja tagilla', {
        ui.run()
    }

    then 'viitteen lisays onnistuu', {
        io.getPrintit().shouldHave("Uusi viite lisättiin!")
    }
}