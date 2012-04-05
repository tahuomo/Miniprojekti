package kumiankka.viitejarjestelma;

public class App {

    public static void main(String[] args) {
        KonsoliIO io = new KonsoliIO();
        Viitepalvelu vp = new Viitepalvelu();
        TekstiUI ui = new TekstiUI(io, vp);
        
        ui.run();
    }
}
