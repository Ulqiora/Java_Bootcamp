package ex02;

public class Summator extends Thread {
    private final int[] array;

    private final int endInterval;
    private int result=0;
    private final int startInterval;
    Summator(int[] array,int startInterval,int endInterval){
        this.array=array;
        this.startInterval=startInterval;
        this.endInterval=endInterval;
    }
    @Override
    public void run() {
        for (int i = startInterval; i <= endInterval; i++) {
            result += array[i];
        }
    }
    public int getStartInterval() {
        return startInterval;
    }

    public int getEndInterval() {
        return endInterval;
    }

    public int getResult() {
        return result;
    }
}
