package ex02;

import java.util.Random;
import java.util.stream.IntStream;

public class TestMultiThreadsAndOneThread {
    public static void start(int arraySize, int threadsCount){
        int[] array = new int[arraySize];
        generateArray(array);
        System.out.println("Sum: " + IntStream.of(array).sum());
        int parts=calcParts(arraySize,threadsCount);
        int lastPart = arraySize - (parts * (threadsCount - 1));
        FtThread[] threads = new FtThread[threadsCount];
        Printer printer = new Printer(array);
        defineThreads(threads,printer,parts, threadsCount - 1, 0, 0);
        defineThreads(threads,printer,lastPart, 1, parts * (threadsCount - 1), threadsCount - 1);
        startThreads(threads);
        System.out.println("Sum by threads: " + printer.getSumAll());
    }
    public static int calcParts(int arraySize, int threadsCount){
        int res;
        int compare = (int) Math.ceil((double)arraySize / threadsCount);
        if ((double)arraySize / compare > (double)threadsCount - 1) {
            res = (int) Math.ceil((double) arraySize / threadsCount);
        } else {
            res = (int) Math.floor((double) arraySize / threadsCount);
        }
        return res;
    }

    private static void generateArray(int[] array) {
        Random random = new Random();
        for(int i=0;i<array.length;i++){
            array[i]=random.nextInt(3000)-1000;
        }
    }
    private static void defineThreads(FtThread[] threads,Printer printer,int range, int count, int startArray, int countThread) {
        int n = 0;
        for (int j = 0; j < count; j++) {
            int first = range * n++ + startArray;
            int last = first + range - 1;
            threads[countThread++] = new FtThread(countThread, first, last, printer);
        }
    }

    private static void startThreads(FtThread[] threads) {
        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
                threads[i].join();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
