import kumiankka.viitejarjestelma.*

description 'Kayttaja voi listata viitteet'

scenario "kayttaja voi listata kaikki viitteet", {
    given 'komento "listaa" valittu'
    when 'kasky suoritettu'
    then 'ohjelma listaa kaikki viitteet'
}
