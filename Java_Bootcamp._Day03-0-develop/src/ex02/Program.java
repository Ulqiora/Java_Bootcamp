package ex02;

public class Program {
    public static void main(String[] args){
        try{
            checkArgs(args);
            int arraySize=Integer.parseInt(args[0].substring("--arraySize=".length()));
            int numOfThreads=Integer.parseInt(args[1].substring("--threadsCount=".length()));
            if(arraySize>2000000||arraySize<0) throw new IllegalArgumentException("Incorrect size of array!");
            if(arraySize<numOfThreads) throw new IllegalArgumentException("Incorrect number of threads!");
            TestMultiThreadsAndOneThread.start(arraySize,numOfThreads);
        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    private static void checkArgs(String[] args){
        if(args.length!=2) throw new IllegalArgumentException("Error number of argument!");
        if(!(args[0].startsWith("--arraySize=")&&args[1].startsWith("--threadsCount=")))
            throw new IllegalArgumentException("Incorrect number of threads or size of array!");
    }
}
