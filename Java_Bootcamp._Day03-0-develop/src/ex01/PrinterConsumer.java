package ex01;

public class PrinterConsumer implements Runnable{
    private final DataQueue dataQueue;
    private volatile boolean runFlag;

    public PrinterConsumer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
        runFlag = true;
    }

    @Override
    public void run() {
        consume();
    }

    private void consume() {
        while (runFlag) {
            if (dataQueue.isEmpty()) {
                try {
                    dataQueue.waitOnEmpty();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!runFlag) {
                break;
            }
            String message=dataQueue.remove();
            System.out.println(message);
            dataQueue.notifyAllForFull();
        }
    }
    public void stop(){
        runFlag=false;
    }
}
