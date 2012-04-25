import viitejarjestelma.*
import viitejarjestelma.io.*
import viitejarjestelma.logiikka.*
import viitejarjestelma.tietosailio.*

description 'Kayttaja voi lisata viitteen valideilla tiedoilla'

scenario "kayttaja voi lisata artikkelin valideilla tiedoilla", {
    given 'komento "uusi" ja "1" valittu', {
        io = new StubIO("uusi", "1", "Täydellinen", "2012", "Frontside Ollie", "1", "2", "4", "UVM", "", "", "Robin", "Packalen", "", "", "", "lopeta")
        vk = new Viitekirjanpito()
        vp = new Viitepalvelu(vk, null, null)
        ui = new TekstiUI(io, vp)        
    }
    
    when 'validit artikkelin tiedot syotetty', {
        ui.run()
    }

    then 'artikkeli lisataan jarjestelmaan', {
        io.getPrintit().shouldHave("Uusi viite lisättiin!")
    }
}

scenario "kayttaja voi lisata konferenssijulkaisun valideilla tiedoilla", {
    given 'komento "uusi" ja "2" valittu', {
        io = new StubIO("uusi", "2", "Otsikko", "2012", "Kirja", "Julkaisija", "Osoite", "Kirjasarja", "1", "5", "Organisaatio", "1", "Lisätieto", "Matti", "Luukkainen", "", "", "", "lopeta")
        vk = new Viitekirjanpito()
        vp = new Viitepalvelu(vk, null, null)
        ui = new TekstiUI(io, vp)        
    }
    
    when 'validit konferenssijulkaisun tiedot syotetty', {
        ui.run()
    }

    then 'konferenssijulkaisu lisataan jarjestelmaan', {
        io.getPrintit().shouldHave("Uusi viite lisättiin!")
    }
}

scenario "kayttaja voi lisata kirjan valideilla tiedoilla", {
    given 'komento "uusi" ja "3" valittu', {
        io = new StubIO("uusi", "3", "Otsikko", "2012", "Julkaisija", "Osoite", "Painos", "Sarja", "1", "Lisätieto", "Arto", "Vihavainen", "", "", "", "lopeta")
        vk = new Viitekirjanpito()
        vp = new Viitepalvelu(vk, null, null)
        ui = new TekstiUI(io, vp)        
    }
    
    when 'validit kirjan tiedot syotetty', {
        ui.run()
    }

    then 'kirja lisataan jarjestelmaan', {
        io.getPrintit().shouldHave("Uusi viite lisättiin!")
    }
}