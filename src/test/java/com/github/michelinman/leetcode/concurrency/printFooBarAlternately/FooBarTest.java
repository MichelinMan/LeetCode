package com.github.michelinman.leetcode.concurrency.printFooBarAlternately;

import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FooBarTest {

    @Test
    void fooBarPrintsFoobarOnce() throws InterruptedException {
        FooBar fooBar = new FooBar(1);
        StringBuilder output = new StringBuilder();
        Thread threadA = new Thread(() -> {
            try {
                fooBar.foo(() -> output.append("foo"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                fooBar.bar(() -> output.append("bar"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start the threads
        threadA.start();
        threadB.start();
        // Wait for the threads to finish
        threadA.join();
        threadB.join();

        assertEquals("foobar", output.toString());
    }

    @Test
    void fooBarPrintsFoobarMultipleTimes() throws InterruptedException {
        FooBar fooBar = new FooBar(3);
        StringBuilder output = new StringBuilder();
        Thread threadA = new Thread(() -> {
            try {
                fooBar.foo(() -> output.append("foo"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                fooBar.bar(() -> output.append("bar"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start the threads
        threadA.start();
        threadB.start();
        // Wait for the threads to finish
        threadA.join();
        threadB.join();

        assertEquals("foobarfoobarfoobar", output.toString());
    }

    @Test
    void fooBarHandlesEdgeCaseWithZero() throws InterruptedException {
        FooBar fooBar = new FooBar(0);
        StringBuilder output = new StringBuilder();
        Thread threadA = new Thread(() -> {
            try {
                fooBar.foo(() -> output.append("foo"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                fooBar.bar(() -> output.append("bar"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start the threads
        threadA.start();
        threadB.start();
        // Wait for the threads to finish
        threadA.join();
        threadB.join();

        assertEquals("", output.toString());
    }

    @Test
    void fooBarHandlesMaximumValue() throws InterruptedException {
        FooBar fooBar = new FooBar(1000);
        AtomicInteger fooCount = new AtomicInteger();
        AtomicInteger barCount = new AtomicInteger();
        Thread threadA = new Thread(() -> {
            try {
                fooBar.foo(fooCount::incrementAndGet);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                fooBar.bar(barCount::incrementAndGet);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start the threads
        threadA.start();
        threadB.start();
        // Wait for the threads to finish
        threadA.join();
        threadB.join();

        assertEquals(1000, fooCount.get());
        assertEquals(1000, barCount.get());
    }
}