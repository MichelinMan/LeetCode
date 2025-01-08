package com.github.michelinman.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FooBar {
    private int n;
    private final Lock lock = new ReentrantLock();
    private final Condition fooCondition = lock.newCondition();
    private final Condition barCondition = lock.newCondition();
    private boolean fooTurn = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (!fooTurn) {
                    fooCondition.await();
                }
                printFoo.run();
                fooTurn = false;
                barCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (fooTurn) {
                    barCondition.await();
                }
                printBar.run();
                fooTurn = true;
                fooCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}