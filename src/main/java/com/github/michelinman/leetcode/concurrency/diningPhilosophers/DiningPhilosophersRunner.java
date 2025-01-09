package com.github.michelinman.leetcode.concurrency.diningPhilosophers;

import java.util.List;

public class DiningPhilosophersRunner {

    // Static method to run the dining philosophers simulation
    public static void runDinningPhilosophers(int n) throws InterruptedException {
        // Initialize the DiningPhilosophers instance
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();

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
                    // Each philosopher attempts to eat 'n' times
                    for (int j = 0; j < n; j++) {
                        diningPhilosophers.wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
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

        // Retrieve and print the logged actions in the specified format
        List<int[]> actions = diningPhilosophers.getActions();
        System.out.print("Output: [");
        for (int i = 0; i < actions.size(); i++) {
            int[] action = actions.get(i);
            System.out.print("[" + action[0] + "," + action[1] + "," + action[2] + "]");
            if (i < actions.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

}