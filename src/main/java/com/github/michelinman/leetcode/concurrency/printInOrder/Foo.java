package com.github.michelinman.leetcode.concurrency.printInOrder;

import java.util.concurrent.CountDownLatch;

public class Foo {
    private final CountDownLatch secondLatch = new CountDownLatch(1);
    private final CountDownLatch thirdLatch = new CountDownLatch(1);

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        secondLatch.countDown(); // Signal that first() is done
    }

    public void second(Runnable printSecond) throws InterruptedException {
        secondLatch.await(); // Wait for first() to complete
        printSecond.run();
        thirdLatch.countDown(); // Signal that second() is done
    }

    public void third(Runnable printThird) throws InterruptedException {
        thirdLatch.await(); // Wait for second() to complete
        printThird.run();
    }
}