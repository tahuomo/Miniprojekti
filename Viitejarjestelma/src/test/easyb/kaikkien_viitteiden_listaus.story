import kumiankka.viitejarjestelma.*

description 'Kayttaja voi listata viitteet'

scenario "kayttaja voi listata viitteet", {
    given 'komento "listaa" valittu', {
        io = new StubIO("listaa", "lopeta")
        vk = new Viitekirjanpito()
        vp = new Viitepalvelu(vk, null, null)
        ui = new TekstiUI(io, vp)
    }

    when 'kasky suoritettu', {
        ui.run()
    }

    then 'ohjelma listaa kaikki viitteet', {
        io.getPrintit().shouldHave("Viitteet:")
    }
}