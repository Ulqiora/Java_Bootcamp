package ex00;

import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        Scanner myScanner=new Scanner(System.in);
        while(true) {
            try {
                Map<String, String> signatures = SignatureLoader.getSignature();
                String filename = myScanner.next();
                if(filename.equals("42")) break;
                SignatureAnalysis.start(filename, signatures);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
