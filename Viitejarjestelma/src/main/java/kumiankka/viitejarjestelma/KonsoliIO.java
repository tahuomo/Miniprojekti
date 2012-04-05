/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kumiankka.viitejarjestelma;

import java.util.Scanner;

/**
 *
 * @author jeuro
 */
public class KonsoliIO implements IO{
    private Scanner scanner = new Scanner(System.in);
    
    public void tulosta(String toPrint) {
        System.out.println(toPrint);
    }

    public int lueLuku(String prompt) {
        System.out.print(prompt+" ");
        int i = 0;
        try {
            i = Integer.parseInt(scanner.nextLine());
        } catch (Exception e){
            return lueLuku(prompt);
        }
        return i;
    }

    public String lueRivi(String prompt) {
        System.out.print(prompt+" ");
        return scanner.nextLine();
    }
}
