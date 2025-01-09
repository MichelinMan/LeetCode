package com.github.michelinman.leetcode.concurrency.boundedBlockingQueue;

public class BoundedBlockingQueueRunner {

    public static void runBoundedBlockingQueue(int capacity) {
        BoundedBlockingQueue queue = new BoundedBlockingQueue(capacity);

        // Example usage with multiple producer and consumer threads
        Runnable producer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.enqueue(i);
                    System.out.println("Enqueued: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int value = queue.dequeue();
                    System.out.println("Dequeued: " + value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}