package com.github.michelinman.leetcode.concurrency.fooBar;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FooBar {
    private final int n;
    private final Lock lock = new ReentrantLock();
    private final Condition fooCondition = lock.newCondition();
    private final Condition barCondition = lock.newCondition();
    private boolean fooTurn = true;

    public FooBar(int n) {
        this.n = n;
    }

    // Method to print "foo" n times, ensuring it alternates with "bar"
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock(); // Acquire the lock
            try {
                // Wait until it's foo's turn
                while (!fooTurn) {
                    fooCondition.await();
                }
                printFoo.run(); // Print "foo"
                fooTurn = false; // Set turn to bar
                barCondition.signal(); // Signal bar thread to proceed
            } finally {
                lock.unlock(); // Release the lock
            }
        }
    }

    // Method to print "bar" n times, ensuring it alternates with "foo"
    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock(); // Acquire the lock
            try {
                // Wait until it's bar's turn
                while (fooTurn) {
                    barCondition.await();
                }
                printBar.run(); // Print "bar"
                fooTurn = true; // Set turn to foo
                fooCondition.signal(); // Signal foo thread to proceed
            } finally {
                lock.unlock(); // Release the lock
            }
        }
    }
}