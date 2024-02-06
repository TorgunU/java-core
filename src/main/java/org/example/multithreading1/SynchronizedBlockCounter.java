package org.example.multithreading1;

public class SynchronizedBlockCounter implements SiteVisitCounter {
    private Integer counter = 0;

    @Override
    public synchronized void incrementVisitCount() {
        try {
            Thread.sleep(100);
            counter++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized int getVisitCount() {
        return counter;
    }
}
