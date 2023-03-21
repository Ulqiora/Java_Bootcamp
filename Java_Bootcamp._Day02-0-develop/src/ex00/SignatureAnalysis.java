package ex00;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignatureAnalysis {
    private static final char[] HEX = "0123456789ABCDEF".toCharArray();

    private static final String RESULT_FILE = System.getProperty("user.dir")+"/ex00/result.txt";
    public static void start(String filename, Map<String,String> signatures){
        try {
            OutputStream os = new FileOutputStream(RESULT_FILE);
            InputStream is = new FileInputStream(filename);
            List<Byte> listBytes = new ArrayList<Byte>();
            while(listBytes.size()<16){
                listBytes.add((byte)is.read());
                String signature = createStringByBytes(listBytes);
                if(signatures.containsKey(signature)){
                    System.out.println("PROCESSED");
                    os.write(signatures.get(signature).getBytes());
                    os.write('\n');
                    break;
                }
            }
            os.close();
            is.close();
            if(listBytes.size()==16) System.out.println("UNDEFINED");
        } catch (FileNotFoundException e){
            System.err.println("File "+filename+" not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String createStringByBytes(List<Byte> listBytes){
        StringBuilder result = new StringBuilder(new String());
        for(Byte bit: listBytes){
            int v=bit & 0xFF;
            result.append(HEX[v >>> 4]);
            result.append(HEX[v & 0x0F]);
        }
        return result.toString();
    }

}
