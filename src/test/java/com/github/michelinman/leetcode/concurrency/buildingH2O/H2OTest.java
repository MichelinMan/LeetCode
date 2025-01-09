package com.github.michelinman.leetcode.concurrency.buildingH2O;

import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

class H2OTest {

    @Test
    void testH2OFormation() throws InterruptedException {
        H2O h2o = new H2O();
        StringBuilder output = new StringBuilder();
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable releaseHydrogen = () -> {
            output.append("H");
            latch.countDown();
        };

        Runnable releaseOxygen = () -> {
            output.append("O");
            latch.countDown();
        };

        executor.execute(() -> {
            try {
                h2o.hydrogen(releaseHydrogen);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.execute(() -> {
            try {
                h2o.hydrogen(releaseHydrogen);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.execute(() -> {
            try {
                h2o.oxygen(releaseOxygen);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        latch.await(1, TimeUnit.SECONDS);
        executor.shutdown();

        assertEquals("OHH", output.toString());
    }
}