package kumiankka.viitejarjestelma;

import org.hsqldb.Server;

public class App {
    public static Server s;

    public static void main(String[] args) {
        kaynnistaTietokanta();

        KonsoliIO io = new KonsoliIO();
        Viitetietokanta vtk = new Viitetietokanta();
        BibTexGeneraattori bib = new BibTexGeneraattori();
        TiedostonKirjoittaja tk = new TiedostonKirjoittaja();
        Viitepalvelu vp = new Viitepalvelu(vtk, bib, tk);
        TekstiUI ui = new TekstiUI(io, vp);

        ui.run();

        sammutaTietokanta();
    }

    private static void kaynnistaTietokanta() {
        System.out.println("Käynnistetään tietokanta...");
        s = new Server();
        s.setLogWriter(null);
        s.setAddress("localhost");
        s.setDatabaseName(0, "viitteet");
        s.setDatabasePath(0, "file:tietokanta/viitteet");
        s.start();
    }

    private static void sammutaTietokanta() {
        System.out.println("Sammutetaan tietokanta...");
        s.shutdown();
    }
}
