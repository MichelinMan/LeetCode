package com.github.michelinman.leetcode.concurrency.fizzBuzz;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private final int n;
    private int current = 1;
    private final Semaphore semFizz = new Semaphore(0);
    private final Semaphore semBuzz = new Semaphore(0);
    private final Semaphore semFizzBuzz = new Semaphore(0);
    private final Semaphore semNumber = new Semaphore(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                semFizz.acquire();
                printFizz.run();
                releaseNext();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                semBuzz.acquire();
                printBuzz.run();
                releaseNext();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                semFizzBuzz.acquire();
                printFizzBuzz.run();
                releaseNext();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                semNumber.acquire();
                printNumber.accept(i);
                releaseNext();
            }
        }
    }

    private void releaseNext() {
        current++;
        if (current > n) {
            return;
        }
        if (current % 15 == 0) {
            semFizzBuzz.release();
        } else if (current % 3 == 0) {
            semFizz.release();
        } else if (current % 5 == 0) {
            semBuzz.release();
        } else {
            semNumber.release();
        }
    }
}