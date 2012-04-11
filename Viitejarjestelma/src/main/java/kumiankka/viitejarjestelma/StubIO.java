package kumiankka.viitejarjestelma;

import java.util.ArrayList;

public class StubIO implements IO {
    private String[] syotteet;
    private int i;
    private ArrayList<String> printit;

    public StubIO(String... values) {
        this.syotteet = values;
        printit = new ArrayList<String>();
    }

    public void tulosta(String toPrint) {
        printit.add(toPrint);
    }

    public int lueLuku(String prompt) {
        tulosta(prompt);
        return Integer.parseInt(syotteet[i++]);
    }

    public ArrayList<String> getPrintit() {
        return printit;
    }

    public String lueRivi(String prompt) {
        tulosta(prompt);
        if (i < syotteet.length) {
            return syotteet[i++];
        }
        return "";
    
    }
    
}