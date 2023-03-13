package ex03;

import java.io.*;
import java.net.URL;

public class DownloaderUrlFiles implements Runnable {
    private final UrlsQueue queue;
    static private int TOTAL_ID=0;
    private int id;
    DownloaderUrlFiles(UrlsQueue urls){
        queue=urls;
        id=TOTAL_ID;
        TOTAL_ID++;
    }

    @Override
    public void run(){
        try {
            download();
        }catch(Exception e){
            System.err.println(e);
        }
    }

    private void download() {
        while(true){
            try{
                waitNonEmptyQueue();
                Url url = queue.remove();
                System.out.println("Thread-" + id + " start download file number " + url.getId());
                File file=getFile(url.getAddress());
                PrintResultOfDownlod(url, CreateFile(url,file));
                queue.notifyAllForFull();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    private void waitNonEmptyQueue(){
        while (queue.isEmpty()) {
            try{
                queue.waitOnFull();
            } catch (Exception e){
                break;
            }
        }
    }
    private boolean CreateFile(Url url,File file){
        try (BufferedInputStream input = new BufferedInputStream(new URL(url.getAddress()).openStream());
             FileOutputStream output = new FileOutputStream(file)){
            byte[] dataBuffer = new byte[1024];
            int byteRead;
            while ((byteRead = input.read(dataBuffer)) != -1)
                output.write(dataBuffer, 0, byteRead);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
    private void PrintResultOfDownlod(Url url,boolean bool){
        if(bool) System.out.println("Thread-" + id + " finish download file number " + url.getId());
        else System.out.println("Thread-" + id + " file number " + url.getId()+" not downloaded!");
    }
    private File getFile(String str) {
        String[] strings = str.split("/");
        return new File(strings[strings.length - 1]);
    }
}
