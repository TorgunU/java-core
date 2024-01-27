package org.example.multithreading1;

public class UnsynchronizedCounter implements SiteVisitCounter {
    private int counter;

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
            counter++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
