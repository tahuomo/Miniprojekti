package viitejarjestelma.io;

public interface IO {
    void tulosta(String toPrint);
    int lueLuku(String prompt, boolean pakollinen);
    String lueSyote(String prompt, boolean pakollinen);
//    public String LueRivi(String prompt);
}
