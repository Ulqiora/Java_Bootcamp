package ex01;

import java.util.Queue;

public class MessageGenerator implements Runnable{
    private final DataQueue dataQueue;
    private static int ID_TOTAL=0;
    private int id;
    private static int currentGenerator=0;
    private int count;
    String message;
    public MessageGenerator(int count, String message, DataQueue queue){
        this.dataQueue=queue;
        this.message=message;
        this.count=count;
        id=ID_TOTAL;
        ID_TOTAL++;
    }

    @Override
    public void run() {
        produce();
    }
    private void produce(){
        while (count!=0) {
            while (dataQueue.isFull()||currentGenerator!=id) {
                try {
                    if(!dataQueue.isEmpty()) dataQueue.notifyAllForFull();
//                    System.out.println("Wait ,currentGenerator="+ currentGenerator+",isFull() = "+dataQueue.isFull()+",id = "+id);
                    dataQueue.waitOnFull();
                } catch (InterruptedException e) {
                    break;
                }
            }
            dataQueue.add(message);
            count--;
            currentGenerator=(currentGenerator+1)==ID_TOTAL?0:(currentGenerator+1);
//            System.out.println("ADD "+ message+" , next generator id = " + currentGenerator);
            dataQueue.notifyAllForEmpty();
        }
    }
}
