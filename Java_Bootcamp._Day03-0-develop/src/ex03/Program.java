package ex03;

import java.io.IOException;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        try {
            initAndStartThreads(checkArgsAndGetThreadsCount(args));
        } catch (Exception e) {
            printErr(e.getMessage());
        }
    }

    private static void initAndStartThreads(int threadsCount) throws IOException, InterruptedException {
        UrlsQueue queue= new UrlsQueue();
        Thread loadedUrls= new Thread(new UrlsLoader(queue));
        loadedUrls.start();
        ArrayList<Thread> dowloaderList=new ArrayList<>();
        for(int i=0;i<threadsCount;i++) {
            dowloaderList.add(new Thread(new DownloaderUrlFiles(queue)));
            dowloaderList.get(i).start();
        }
    }

    private static int checkArgsAndGetThreadsCount(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            printErr("Specify argument using '--threadsCount='");
        }
        int threadsCount = Integer.parseInt(args[0].substring("--threadsCount=".length()));
        if (threadsCount < 1) {
            printErr("Invalid argument threadsCount. Must be greater than 0");
        }
        return threadsCount;
    }

    public static void printErr(String msg) {
        System.err.println(msg);
        System.exit(-1);
    }
}