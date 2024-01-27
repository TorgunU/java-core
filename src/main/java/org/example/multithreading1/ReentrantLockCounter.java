package org.example.multithreading1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements  SiteVisitCounter {
    private int counter;
    private ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void incrementVisitCount() {
        reentrantLock.lock();
        try {
            Thread.sleep(100);
            counter++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override
    public int getVisitCount() {
        reentrantLock.lock();
        try {
            return counter;
        } finally {
            reentrantLock.unlock();
        }
    }
}
