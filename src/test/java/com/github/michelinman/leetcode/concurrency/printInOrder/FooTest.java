package com.github.michelinman.leetcode.concurrency.printInOrder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FooTest {

    @Test
    void testFooOrder() throws InterruptedException {
        Foo foo = new Foo();
        StringBuilder output = new StringBuilder();

        Thread threadA = new Thread(() -> {
            try {
                foo.first(() -> output.append("first"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                foo.second(() -> output.append("second"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                foo.third(() -> output.append("third"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // start the threads not in the expected order
        threadC.start();
        threadB.start();
        threadA.start();

        // Wait for all threads to finish
        threadA.join();
        threadB.join();
        threadC.join();

        assertEquals("firstsecondthird", output.toString());
    }
}