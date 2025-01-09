package com.github.michelinman.leetcode.concurrency.buildingH2O;

public class H2ORunner {

    public static void runH2O(String water) {
        H2O h2o = new H2O();

        Runnable releaseHydrogen = () -> System.out.print("H");
        Runnable releaseOxygen = () -> System.out.print("O");

        int hydrogenCount = 0;
        int oxygenCount = 0;

        for (char molecule : water.toCharArray()) {
            if (molecule == 'H') {
                hydrogenCount++;
            } else if (molecule == 'O') {
                oxygenCount++;
            }
        }

        Thread[] hydrogenThreads = new Thread[hydrogenCount];
        Thread[] oxygenThreads = new Thread[oxygenCount];

        for (int i = 0; i < hydrogenCount; i++) {
            hydrogenThreads[i] = new Thread(() -> {
                try {
                    h2o.hydrogen(releaseHydrogen);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        for (int i = 0; i < oxygenCount; i++) {
            oxygenThreads[i] = new Thread(() -> {
                try {
                    h2o.oxygen(releaseOxygen);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        for (Thread thread : hydrogenThreads) {
            thread.start();
        }

        for (Thread thread : oxygenThreads) {
            thread.start();
        }

        try {
            for (Thread thread : hydrogenThreads) {
                thread.join();
            }
            for (Thread thread : oxygenThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}