package org.example.multithreading1;

import java.util.concurrent.*;

public class MultithreadingSiteVisitor {
    private SiteVisitCounter siteVisitCounter;
    private ExecutorService executorService;
    private CountDownLatch countDownLatch;
    private long before;

    public MultithreadingSiteVisitor(SiteVisitCounter siteVisitCounter) {
        this.siteVisitCounter = siteVisitCounter;
    }

    public void visitMultithread(int numOfThreads) {
        executorService = Executors.newFixedThreadPool(numOfThreads);
        countDownLatch = new CountDownLatch(numOfThreads);
        before = System.currentTimeMillis();

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
            executorService.shutdown();
        }
    }

    public void getTotalTimeOfHandling() {
        long after = System.currentTimeMillis();
        System.out.println("Общее время обрбаботки всех потоков: " + (after - before));
        System.out.println("Количество посещений: " + siteVisitCounter.getVisitCount());
        before = 0;
    }
}
