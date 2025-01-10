package com.github.michelinman.leetcode.concurrency.printZeroEvenOdd;

import java.util.function.IntConsumer;

public class ZeroEvenOddRunner {

    public static void runZeroEvenOdd(int n) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);
        IntConsumer printNumber = System.out::print;

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

        try {
            threadZero.join();
            threadEven.join();
            threadOdd.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}