package com.github.michelinman.leetcode.concurrency.fooBar;

public class FooBarRunner {

    public static void foobar() {
        int n = 2; // Example value for n
        FooBar fooBar = new FooBar(n);

        Thread threadA = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadA.start();
        threadB.start();
    }
}