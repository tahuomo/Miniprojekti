import kumiankka.viitejarjestelma.*

description 'Kayttaja voi lisata artikkeliviitteen valideilla tiedoilla'

scenario "kayttaja voi lisata artikkelin valideilla tiedoilla", {
    given 'komento "uusi" valittu', {
        io = new StubIO("uusi", "article", "Täydellinen", "2012", "Frontside Ollie", "1", "2", "4", "UVM", "Robin", "Packalen", "", "lopeta")
        vk = new Viitekirjanpito()
        vp = new Viitepalvelu(vk)
        ui = new TekstiUI(io, vp)        
    }
    
    when 'validit artikkelin tiedot syotetty', {
        ui.run()
    }

    then 'artikkeli lisataan jarjestelmaan', {
        io.getPrintit().shouldHave("Uusi viite lisättiin!")
    }
}
