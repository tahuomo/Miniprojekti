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

    public int lueLuku(String prompt, boolean pakollinen) {
        tulosta(prompt);
        String syote = syotteet[i++];
        if (syote.isEmpty()) return 0;
        return Integer.parseInt(syote);
    }

    public ArrayList<String> getPrintit() {
        return printit;
    }

    public String lueSyote(String prompt, boolean pakollinen) {
        tulosta(prompt);
        String s = syotteet[i++];
        if (s == "") return null;
        return s;
    
    }
    
//    public String LueRivi(String prompt) {
//        tulosta(prompt);
//        if (i < syotteet.length) {
//            return syotteet[i++];
//        }
//        return "";
//    
//    }
    
}