package viitejarjestelma.io;

import java.util.Scanner;

public class KonsoliIO implements IO {
    private Scanner scanner = new Scanner(System.in);

    public void tulosta(String toPrint) {
        System.out.println(toPrint);
    }

    public int lueLuku(String prompt, boolean pakollinen) {
        System.out.print(prompt + " ");
        int i = 0;
        String syote = scanner.nextLine();
        try {
            i = Integer.parseInt(syote);
        } catch (Exception e) {
            if (pakollinen){
                tulosta("Tyhjä syöte ei kelpaa!");
                return lueLuku(prompt, pakollinen);
            }
        }
        return i;
    }
    
    

    public String lueSyote(String prompt, boolean pakollinen) {
        System.out.print(prompt + " ");
        String syote = scanner.nextLine();
        if (syote.isEmpty()){
            if (pakollinen){
                tulosta("Tyhjä syöte ei kelpaa!");
                return lueSyote(prompt, pakollinen);
            } else {
                return null;
            }
            
        }
        return syote;
    }

//    @Override
//    public String LueRivi(String prompt) {
//        System.out.print(prompt + " ");
//        return scanner.nextLine();
//    }
}
