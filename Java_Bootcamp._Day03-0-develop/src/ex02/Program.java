package ex02;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args){
        try{
            checkArgs(args);
            int arraySize=Integer.parseInt(args[0].substring("--arraySize=".length()));
            int numOfThreads=Integer.parseInt(args[1].substring("--threadsCount=".length()));
            int[] array=GenerateRandomArray.NewArray(arraySize,-1000,1000);
            RunOneThread(array);
            RunMultiThreads(array,numOfThreads);
        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    private static void RunMultiThreads(int[] array, int numOfThreads) throws InterruptedException {
        ArrayList<Summator> summatorList=CreateSummators(array,array.length,numOfThreads);
        int result=0,index=0;
        for(Summator summator:summatorList) {
            summator.join();
            System.out.println( "Thread " + index++ + ": from " + summator.getStartInterval() +
                    " to " + summator.getEndInterval() + " sum is " + summator.getResult());
            result+=summator.getResult();
        }
        System.out.println(result);
    }

    private static void RunOneThread(int[] array) {
        int res=0;
        for(int i=0;i<array.length;i++){
            res+=array[i];
        }
        System.out.println("Sum = "+res);
    }


    private static ArrayList<Summator> CreateSummators(int[] array, int arraySize, int numOfThreads) {
        ArrayList<Summator> summators=new ArrayList<Summator>();
        int elemPerPart= CalcNumElemInPart(arraySize,numOfThreads);
        int elemInLastPart = arraySize - (elemPerPart * (numOfThreads - 1));
        int n=0,startArray=0;
        for(int j = 0; j < numOfThreads-1; j++){
            int first = elemPerPart * n++;
            summators.add(new Summator(array,first,first + elemPerPart - 1));
            summators.get(j).start();
        }
        summators.add(new Summator(array,elemPerPart * n,arraySize-1));
        summators.get(numOfThreads-1).start();
        return summators;
    }

    private static int CalcNumElemInPart(int arraySize, int numOfThreads) {
        int compare = (int) Math.ceil((double)arraySize / numOfThreads);
        if ((double)arraySize / compare > (double)numOfThreads - 1)
            return (int) Math.ceil((double) arraySize / numOfThreads);
        return (int) Math.floor((double) arraySize / numOfThreads);
    }


    private static void checkArgs(String[] args){
        if(args.length!=2) throw new IllegalArgumentException("Error number of argument!");
        if(!(args[0].startsWith("--arraySize=")&&args[1].startsWith("--threadsCount=")))
            throw new IllegalArgumentException("Incorrect number of threads or size of array!");
    }
}

