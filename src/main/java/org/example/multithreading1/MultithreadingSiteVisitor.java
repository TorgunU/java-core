package org.example.multithreading1;

import java.util.concurrent.*;

public class MultithreadingSiteVisitor {
    private SiteVisitCounter siteVisitCounter;
    private ExecutorService executorService;
    private CountDownLatch countDownLatch;
    private long startTime;
    private long endTime;

    public MultithreadingSiteVisitor(SiteVisitCounter siteVisitCounter) {
        this.siteVisitCounter = siteVisitCounter;
    }

    public void visitMultithread(int numOfThreads) {
        executorService = Executors.newFixedThreadPool(numOfThreads);
        countDownLatch = new CountDownLatch(numOfThreads);
        startTime = System.currentTimeMillis();

        for (int i = 0; i < numOfThreads; i++) {
            executorService.execute(() -> {
                siteVisitCounter.incrementVisitCount();
                System.out.println(Thread.currentThread().getName() + " ready");
                countDownLatch.countDown();
            });
        }
    }

    public void waitUntilAllVisited()  {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            endTime = System.currentTimeMillis();
            executorService.shutdown();
        }
    }

    public long getTotalTimeOfHandling() {
        return endTime - startTime;
    }
}
