import viitejarjestelma.*
import viitejarjestelma.io.*
import viitejarjestelma.logiikka.*
import viitejarjestelma.tietosailio.*

description 'Kayttaja voi listata viitteet'

scenario "kayttaja voi listata viitteet", {
    given 'komento "listaa" valittu', {
        io = new StubIO("uusi", "1", "Täydellinen", "2012", "Frontside Ollie", "1", "2", "4", 
                        "UVM", "", "", "Robin", "Packalen", "", "", "tagi", "","uusi", "2", 
                        "Otsikko", "2012", "Kirja", "Julkaisija", 
                        "Osoite", "Kirjasarja", "1", "5", "Organisaatio", "1", "Lisätieto", 
                        "Matti", "Luukkainen", "", "", "scrum", "", "listaa", "", "lopeta")
        vk = new Viitekirjanpito()
        vp = new Viitepalvelu(vk, null, null)
        ui = new TekstiUI(io, vp)
    }

    when 'kasky suoritettu', {
        ui.run()
    }

    then 'ohjelma listaa kaikki viitteet', {
        io.getPrintit().shouldHave("ID: P2012, article:")
        io.getPrintit().shouldHave("ID: L2012, inproceedings:")
    }
}

scenario "kayttajalle kerrotaan jos viitteita ei ole", {
    given 'komento "listaa" valittu', {
        io = new StubIO("listaa", "", "lopeta")
        vk = new Viitekirjanpito()
        vp = new Viitepalvelu(vk, null, null)
        ui = new TekstiUI(io, vp)
    }

    when 'kasky suoritettu', {
        ui.run()
    }

    then 'ohjelma kertoo ettei viitteita ole', {
        io.getPrintit().shouldHave("Viitteitä ei löydetty annetuilla ehdoilla")
    }
}

scenario "kayttaja voi hakea hakuehdon perusteella", {
    given 'komento "listaa" valittu', {
        io = new StubIO("uusi", "1", "Täydellinen", "2012", "Frontside Ollie", "1", "2", "4", 
                        "UVM", "", "", "Robin", "Packalen", "", "", "tagi", "",
                        "uusi", "2", "Otsikko", "2012", "Kirja", "Julkaisija", 
                        "Osoite", "Kirjasarja", "1", "5", "Organisaatio", "1", "Lisätieto", 
                        "Matti", "Luukkainen", "", "", "scrum", "", "listaa", "tag=scrum", 
                        "lopeta")
        vk = new Viitekirjanpito()
        vp = new Viitepalvelu(vk, null, null)
        ui = new TekstiUI(io, vp)
    }

    when 'oikean muotoinen hakuehto annettu', {
        ui.run()
    }

    then 'ohjelma antaa suodatetut hakutulokset', {
        io.getPrintit().shouldHave("ID: L2012, inproceedings:") 
        io.getPrintit().shouldNotHave("ID: P2012, article:") 
    }
}