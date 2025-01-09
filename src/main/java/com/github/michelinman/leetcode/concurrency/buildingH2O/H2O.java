package com.github.michelinman.leetcode.concurrency.buildingH2O;

import java.util.concurrent.Semaphore;
import java.util.concurrent.CyclicBarrier;

public class H2O {
    private final Semaphore hydrogenSemaphore = new Semaphore(2);
    private final Semaphore oxygenSemaphore = new Semaphore(1);
    private final CyclicBarrier barrier = new CyclicBarrier(3);

    public H2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSemaphore.acquire();
        try {
            barrier.await();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        releaseHydrogen.run();
        hydrogenSemaphore.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenSemaphore.acquire();
        try {
            barrier.await();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        releaseOxygen.run();
        oxygenSemaphore.release();
    }
}