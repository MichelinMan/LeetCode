package com.github.michelinman.leetcode.concurrency.diningPhilosophers;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

class DiningPhilosophers {
    // Array of semaphores representing the forks
    Semaphore[] forks;

    // List to store the actions of each philosopher
    @Getter
    private final List<int[]> actions = new ArrayList<>();

    // Constructor initializes the semaphores for each fork
    public DiningPhilosophers() {
        forks = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    // Method to simulate a philosopher wanting to eat
    public synchronized void wantsToEat(int philosopher,
                                        Runnable pickLeftFork,
                                        Runnable pickRightFork,
                                        Runnable eat,
                                        Runnable putLeftFork,
                                        Runnable putRightFork) throws InterruptedException {
        // Acquire the left fork (semaphore)
        forks[philosopher].acquire();

        // Acquire the right fork (semaphore)
        forks[(philosopher + 1) % 5].acquire();

        // Pick up the left fork
        pickLeftFork.run();
        logAction(philosopher, 1, 1); // Log pick left fork

        // Pick up the right fork
        pickRightFork.run();
        logAction(philosopher, 2, 1); // Log pick right fork

        // Eat
        eat.run();
        logAction(philosopher, 0, 3); // Log eat

        // Put down the left fork
        putLeftFork.run();
        logAction(philosopher, 1, 2); // Log put left fork

        // Release the left fork (semaphore)
        forks[philosopher].release();

        // Put down the right fork
        putRightFork.run();
        logAction(philosopher, 2, 2); // Log put right fork

        // Release the right fork (semaphore)
        forks[(philosopher + 1) % 5].release();
    }

    private void logAction(int philosopher, int fork, int action) {
        actions.add(new int[]{philosopher, fork, action});
    }
}