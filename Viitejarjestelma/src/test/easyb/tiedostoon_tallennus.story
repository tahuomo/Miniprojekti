import kumiankka.viitejarjestelma.*
import static org.mockito.Mockito.*

description 'Kayttaja voi tallentaa BibTex-tiedoston'

scenario "tiedoston tallentaminen onnistuu", {
    given 'komento "bibtex" valittu', {   
        mockTallennus = mock(Tiedostonkasittely.class) 
        bibtex = new BibTexGeneraattori()
        io = new StubIO("uusi", "1", "Otsikko", "2012", "Lehti", "", "", "", "", "", "", "Matti", "Luukkainen", "", "", "bibtex", "tiedostonimi", "lopeta")
        vk = new Viitekirjanpito()
        vp = new Viitepalvelu(vk, bibtex, mockTallennus)
        ui = new TekstiUI(io, vp)
    }
    
    when 'validi tiedostonimi syotetty', {
        when(mockTallennus.kirjoitaTiedostoon("\n@article{L2012,\n" +
                "\tauthor = {Luukkainen, Matti},\n" +
                "\ttitle = {Otsikko},\n" +
                "\tjournal = {Lehti},\n"+
                "\tyear = {2012},\n" +
                "}\n"
                , "tiedostonimi")).thenReturn(true)
        ui.run()
    }

    then 'viitteista generoidaan BibTex-tiedosto', {
        io.getPrintit().shouldHave("Valmis!")
    }
}
