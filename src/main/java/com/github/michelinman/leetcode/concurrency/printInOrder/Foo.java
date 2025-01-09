package com.github.michelinman.leetcode.concurrency.printInOrder;

import java.util.concurrent.CountDownLatch;

public class Foo {
    // Latch to ensure second() waits for first() to complete
    private final CountDownLatch secondLatch = new CountDownLatch(1);
    // Latch to ensure third() waits for second() to complete
    private final CountDownLatch thirdLatch = new CountDownLatch(1);

    // Method to be called first
    public void first(Runnable printFirst) throws InterruptedException {
        // Run the provided Runnable
        printFirst.run();
        // Signal that first() is done
        secondLatch.countDown();
    }

    // Method to be called second
    public void second(Runnable printSecond) throws InterruptedException {
        // Wait for first() to complete
        secondLatch.await();
        // Run the provided Runnable
        printSecond.run();
        // Signal that second() is done
        thirdLatch.countDown();
    }

    // Method to be called third
    public void third(Runnable printThird) throws InterruptedException {
        // Wait for second() to complete
        thirdLatch.await();
        // Run the provided Runnable
        printThird.run();
    }
}