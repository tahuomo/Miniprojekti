import viitejarjestelma.*
import viitejarjestelma.io.*
import viitejarjestelma.logiikka.*
import viitejarjestelma.tietosailio.*

description 'Kayttaja voi poistaa tietokannassa olevan viitteen'

scenario "kayttaja voi poistaa tietokannassa olevan viitteen", {
    given 'komento "poista" valittu'
    when 'tietokannasta loytyva tunniste syotetty'
    then 'viite poistetaan tietokannasta'
}

scenario "kayttaja ei voi poistaa viitetta jota ei ole", {
    given 'komento "poista" valittu'
    when 'tietokannasta puuttuva tunniste syotetty'
    then 'viitetta ei poisteta tietokannasta'
}