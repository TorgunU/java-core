package org.example.multithreading1;

public class SynchronizedBlockCounter implements SiteVisitCounter {
    private Integer counter = new Integer(0);

    @Override
    public synchronized void incrementVisitCount() {
        try {
            Thread.sleep(100);
            int value = counter + 1;
            counter = Integer.valueOf(value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        int value = counter + 1;
//        counter = Integer.valueOf(value);
    }

    @Override
    public synchronized int getVisitCount() {
        return counter;
    }
}
