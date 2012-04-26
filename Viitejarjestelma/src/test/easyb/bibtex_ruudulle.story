import viitejarjestelma.*
import viitejarjestelma.io.*
import viitejarjestelma.logiikka.*
import viitejarjestelma.tietosailio.*

description 'Kayttaja voi listata bibtexin ruudlle'

scenario "kayttaja voi tulostaa bibtexin ruudulle", {
    given 'komento "bibtex" valittu', {
        io = new StubIO("bibtex", "", "lopeta")
        vk = new Viitekirjanpito()
        vp = new Viitepalvelu(vk, null, null)
        ui = new TekstiUI(io, vp)
    }

    when 'kasky suoritettu ja tyhja syote annettu', {
        ui.run()
    }

    then 'ohjelma listaa bibtexin ruudulle', {
        io.getPrintit().shouldHave("Bibtex:\n")
    }
}