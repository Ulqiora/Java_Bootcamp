package ex00;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SignatureLoader {
    private static final String SIGNATURES = System.getProperty("user.dir")+"src/ex00/signal.txt";
    private static final int TYPE_NAME = 0;
    private static final int SIGNATURE_STR = 1;

    public static Map<String,String> getSignature(){
        try {
            InputStream is = new FileInputStream(SIGNATURES);
            Map<String,String> signaturesMap = new HashMap<String,String>();
            Scanner myScanner = new Scanner(is);
            while (myScanner.hasNextLine()){
                String typeFileWithSignature = myScanner.nextLine();
                String[] typeAndSignature = typeFileWithSignature.split(",", 2);
                signaturesMap.put( typeAndSignature[SIGNATURE_STR].replaceAll(" ", ""),typeAndSignature[TYPE_NAME]);
            }
            myScanner.close();
            return signaturesMap;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
