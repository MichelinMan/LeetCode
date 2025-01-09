package com.github.michelinman.leetcode.concurrency.printInOrder;

import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FooTest {

    @Test
    void testFooOrder() throws InterruptedException {
        Foo foo = new Foo();
        StringBuilder output = new StringBuilder();
        CountDownLatch latch = new CountDownLatch(3);

        Thread threadA = new Thread(() -> {
            try {
                foo.first(() -> {
                    output.append("first");
                    latch.countDown();
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                foo.second(() -> {
                    output.append("second");
                    latch.countDown();
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                foo.third(() -> {
                    output.append("third");
                    latch.countDown();
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();

        latch.await(); // Wait for all threads to complete

        assertEquals("firstsecondthird", output.toString());
    }
}