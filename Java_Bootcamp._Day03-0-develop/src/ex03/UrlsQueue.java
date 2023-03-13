package ex03;

import java.util.LinkedList;
import java.util.Queue;

public class UrlsQueue {
    private final Queue<Url> queue = new LinkedList<>();
    private final Object EMPTY_QUEUE = new Object();
    private final Object FULL_QUEUE = new Object();

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
    public void add(Url url) {
        synchronized (queue) {
            queue.add(url);
        }
    }
    public Url remove() {
        synchronized (queue) {
            return queue.poll();
        }
    }
    public boolean isEmpty() {
        synchronized (queue) {
            return queue.isEmpty();
        }
    }
}
