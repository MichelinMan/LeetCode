package com.github.michelinman.leetcode.concurrency.boundedBlockingQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class BoundedBlockingQueueRunner {
    public static void runBoundedBlockingQueue(int capacity, int numProducers, int numConsumers, List<String> sequence) throws InterruptedException {
        BoundedBlockingQueue queue = new BoundedBlockingQueue(capacity);
        ExecutorService producerPool = Executors.newFixedThreadPool(numProducers);
        ExecutorService consumerPool = Executors.newFixedThreadPool(numConsumers);
        CountDownLatch latch = new CountDownLatch(sequence.size()); // Create a latch to wait for all operations to complete
        Lock lock = new ReentrantLock(); // Create a reentrant lock for synchronization
        Condition notFull = lock.newCondition(); // Condition to wait when the queue is full
        Condition notEmpty = lock.newCondition(); // Condition to wait when the queue is empty

        for (String operation : sequence) {
            if (operation.startsWith("enqueue")) {
                int value = Integer.parseInt(operation.split(" ")[1]);
                producerPool.execute(() -> {
                    lock.lock();
                    try {
                        while (queue.size() == capacity) {
                            notFull.await();
                        }
                        queue.enqueue(value);
                        notEmpty.signal(); // Signal that the queue is not empty
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore the interrupted status
                    } finally {
                        latch.countDown();
                        lock.unlock();
                    }
                });
            } else if (operation.equals("dequeue")) {
                consumerPool.execute(() -> {
                    lock.lock();
                    try {
                        while (queue.size() == 0) {
                            notEmpty.await();
                        }
                        System.out.println("Dequeued: " + queue.dequeue());
                        notFull.signal();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore the interrupted status
                    } finally {
                        latch.countDown();
                        lock.unlock();
                    }
                });
            }
        }

        latch.await(); // Wait for all operations to complete
        producerPool.shutdown();
        consumerPool.shutdown();

        System.out.println("Final size: " + queue.size());
    }

    public static void runBoundedBlockingQueueExamples() throws InterruptedException {
        System.out.println("Example 1:");
        runBoundedBlockingQueue(2, 1, 1,
                List.of("enqueue 1", "dequeue", "dequeue", "enqueue 0", "enqueue 2", "enqueue 3", "enqueue 4", "dequeue")); // [1, 0, 2, 2]
        System.out.println();

        System.out.println("Example 2:");
        runBoundedBlockingQueue(3, 3, 4,
                List.of("enqueue 1", "enqueue 0", "enqueue 2", "dequeue", "dequeue", "dequeue", "enqueue 3")); // [1, 0, 2, 1]
        System.out.println();
    }
}