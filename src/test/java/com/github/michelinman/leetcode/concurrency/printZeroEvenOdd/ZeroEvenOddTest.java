package com.github.michelinman.leetcode.concurrency.printZeroEvenOdd;

import org.junit.jupiter.api.Test;

import java.util.function.IntConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZeroEvenOddTest {

    @Test
    void testZeroEvenOddWithTwo() throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(2);
        StringBuilder output = new StringBuilder();
        IntConsumer printNumber = output::append;

        Thread threadZero = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadEven = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadOdd = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadZero.start();
        threadEven.start();
        threadOdd.start();

        threadZero.join();
        threadEven.join();
        threadOdd.join();

        assertEquals("0102", output.toString());
    }

    @Test
    void testZeroEvenOddWithFive() throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        StringBuilder output = new StringBuilder();
        IntConsumer printNumber = output::append;

        Thread threadZero = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadEven = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadOdd = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadZero.start();
        threadEven.start();
        threadOdd.start();

        threadZero.join();
        threadEven.join();
        threadOdd.join();

        assertEquals("0102030405", output.toString());
    }

    @Test
    void testZeroEvenOddWithOne() throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(1);
        StringBuilder output = new StringBuilder();
        IntConsumer printNumber = output::append;

        Thread threadZero = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadEven = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadOdd = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadZero.start();
        threadEven.start();
        threadOdd.start();

        threadZero.join();
        threadEven.join();
        threadOdd.join();

        assertEquals("01", output.toString());
    }

    @Test
    void testZeroEvenOddWithZero() throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(0);
        StringBuilder output = new StringBuilder();
        IntConsumer printNumber = output::append;

        Thread threadZero = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadEven = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadOdd = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadZero.start();
        threadEven.start();
        threadOdd.start();

        threadZero.join();
        threadEven.join();
        threadOdd.join();

        assertEquals("", output.toString());
    }
}