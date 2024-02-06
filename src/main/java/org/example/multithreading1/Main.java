package org.example.multithreading1;

public class Main {
    public static void main(String[] args) {
        testAtomic();
    }

    public static void testUnsynchronized() {
        MultithreadingSiteVisitor siteVisitor = new MultithreadingSiteVisitor(
                new UnsynchronizedCounter()
        );
        siteVisitor.visitMultithread(10_000);
        siteVisitor.waitUntilAllVisited();
        siteVisitor.getTotalTimeOfHandling();
    }

    public static void testVolatile() {
        MultithreadingSiteVisitor siteVisitor = new MultithreadingSiteVisitor(
                new UnsynchronizedCounter()
        );
        siteVisitor.visitMultithread(10_000);
        siteVisitor.waitUntilAllVisited();
        siteVisitor.getTotalTimeOfHandling();
    }

    public static void testSynchronized() {
        MultithreadingSiteVisitor siteVisitor = new MultithreadingSiteVisitor(
                new SynchronizedBlockCounter()
        );
        siteVisitor.visitMultithread(10_000);
        siteVisitor.waitUntilAllVisited();
        siteVisitor.getTotalTimeOfHandling();
    }

    public static void testAtomic() {
        MultithreadingSiteVisitor siteVisitor = new MultithreadingSiteVisitor(
                new SynchronizedBlockCounter()
        );
        siteVisitor.visitMultithread(10_000);
        siteVisitor.waitUntilAllVisited();
        double time = siteVisitor.getTotalTimeOfHandling() / 1000.0;
        System.out.println("Время: " + time);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
