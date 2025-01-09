package com.github.michelinman.leetcode.concurrency.printZeroEvenOdd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private final int n;
    private int currentNumber = 1;
    private boolean zeroTurn = true;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition zeroCondition = lock.newCondition();
    private final Condition evenCondition = lock.newCondition();
    private final Condition oddCondition = lock.newCondition();

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (!zeroTurn) {
                    zeroCondition.await();
                }
                printNumber.accept(0);
                zeroTurn = false;
                if (currentNumber % 2 == 0) {
                    evenCondition.signal();
                } else {
                    oddCondition.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            lock.lock();
            try {
                while (zeroTurn || currentNumber % 2 != 0) {
                    evenCondition.await();
                }
                printNumber.accept(currentNumber);
                currentNumber++;
                zeroTurn = true;
                zeroCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            lock.lock();
            try {
                while (zeroTurn || currentNumber % 2 == 0) {
                    oddCondition.await();
                }
                printNumber.accept(currentNumber);
                currentNumber++;
                zeroTurn = true;
                zeroCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}