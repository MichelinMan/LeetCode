package com.github.michelinman.leetcode.concurrency.diningPhilosophers;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiningPhilosophersTest {

    // Test to ensure there are no deadlocks in the dining philosophers problem
    @Test
    void testNoDeadlock() throws InterruptedException {
        // Initialize the DiningPhilosophers instance
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        // Array to keep track of how many times each philosopher eats
        AtomicInteger[] eatCounts = new AtomicInteger[5];
        for (int i = 0; i < 5; i++) {
            eatCounts[i] = new AtomicInteger(0);
        }

        // Define the actions for picking up and putting down forks, and eating
        Runnable pickLeftFork = () -> {};
        Runnable pickRightFork = () -> {};
        Runnable eat = () -> {};
        Runnable putLeftFork = () -> {};
        Runnable putRightFork = () -> {};

        // Create an array of threads for the philosophers
        Thread[] philosophers = new Thread[5];
        for (int i = 0; i < 5; i++) {
            int philosopher = i;
            philosophers[i] = new Thread(() -> {
                try {
                    // Each philosopher attempts to eat 100 times
                    for (int j = 0; j < 100; j++) {
                        diningPhilosophers.wantsToEat(philosopher, pickLeftFork, pickRightFork, () -> eatCounts[philosopher].incrementAndGet(), putLeftFork, putRightFork);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Start all philosopher threads
        for (Thread philosopher : philosophers) {
            philosopher.start();
        }

        // Wait for all philosopher threads to finish
        for (Thread philosopher : philosophers) {
            philosopher.join();
        }

        // Verify that each philosopher has eaten 100 times
        for (AtomicInteger eatCount : eatCounts) {
            assertEquals(100, eatCount.get());
        }
    }

    // Test to ensure there is no starvation in the dining philosophers problem
    @Test
    void testNoStarvation() throws InterruptedException {
        // Initialize the DiningPhilosophers instance
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        // Array to keep track of how many times each philosopher eats
        AtomicInteger[] eatCounts = new AtomicInteger[5];
        for (int i = 0; i < 5; i++) {
            eatCounts[i] = new AtomicInteger(0);
        }

        // Define the actions for picking up and putting down forks, and eating
        Runnable pickLeftFork = () -> {};
        Runnable pickRightFork = () -> {};
        Runnable eat = () -> {};
        Runnable putLeftFork = () -> {};
        Runnable putRightFork = () -> {};

        // Create an array of threads for the philosophers
        Thread[] philosophers = new Thread[5];
        for (int i = 0; i < 5; i++) {
            int philosopher = i;
            philosophers[i] = new Thread(() -> {
                try {
                    // Each philosopher attempts to eat 100 times
                    for (int j = 0; j < 100; j++) {
                        diningPhilosophers.wantsToEat(philosopher, pickLeftFork, pickRightFork, () -> eatCounts[philosopher].incrementAndGet(), putLeftFork, putRightFork);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Start all philosopher threads
        for (Thread philosopher : philosophers) {
            philosopher.start();
        }

        // Wait for all philosopher threads to finish
        for (Thread philosopher : philosophers) {
            philosopher.join();
        }

        // Verify that each philosopher has eaten 100 times
        for (AtomicInteger eatCount : eatCounts) {
            assertEquals(100, eatCount.get());
        }
    }
}