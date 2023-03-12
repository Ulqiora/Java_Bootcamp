package ex01;

import java.util.LinkedList;
import java.util.Queue;

public class DataQueue {
    private final Queue<String> queue = new LinkedList<>();
    private final int maxSize;
    private final Object FULL_QUEUE = new Object();
    private final Object EMPTY_QUEUE = new Object();
    DataQueue(int maxSize) {
        this.maxSize = maxSize;
    }
    public boolean isFull(){ return maxSize==queue.size(); }
    public void waitOnFull() throws InterruptedException {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.wait();
        }
    }
    public void notifyAllForFull() {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.notifyAll();
        }
    }
    public void waitOnEmpty() throws InterruptedException {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.wait();
        }
    }
    public void notifyAllForEmpty() {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.notify();
        }
    }
    public void add(String message) {
        synchronized (queue) {
            queue.add(message);
        }
    }
    public String remove() {
        synchronized (queue) {
            return queue.poll();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
    public int size(){return queue.size();}
}
