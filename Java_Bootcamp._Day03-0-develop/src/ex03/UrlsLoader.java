package ex03;

import java.io.FileInputStream;
import java.util.Scanner;

public class UrlsLoader implements Runnable{
    private final UrlsQueue queue;

    UrlsLoader(UrlsQueue urls){
        queue=urls;
    }

    @Override
    public void run(){
        parse();
    }

    void parse(){
        try {
            Scanner myscanner=new Scanner(new FileInputStream(System.getProperty("user.dir")+"/src/ex03/files_urls.txt"));
            while(myscanner.hasNext()){
                queue.add(new Url(myscanner.nextInt(),myscanner.next()));
                queue.notifyAllForFull();
            }
            myscanner.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(-1);
        }
    }



}
