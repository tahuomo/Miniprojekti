package kumiankka.viitejarjestelma;

public class App {

    public static void main(String[] args) {
        KonsoliIO io = new KonsoliIO();
        Viitekirjanpito vkp = new Viitekirjanpito();
        Viitepalvelu vp = new Viitepalvelu(vkp);
        TekstiUI ui = new TekstiUI(io, vp);
        
        ui.run();
    }
}
