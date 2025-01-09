package com.github.michelinman.leetcode.concurrency.boundedBlockingQueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoundedBlockingQueueTest {

    @Test
    void testEnqueueDequeue() throws InterruptedException {
        BoundedBlockingQueue queue = new BoundedBlockingQueue(2);

        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
    }

    @Test
    void testSize() throws InterruptedException {
        BoundedBlockingQueue queue = new BoundedBlockingQueue(2);

        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());

        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    void testBlockingOnFullQueue() throws InterruptedException {
        BoundedBlockingQueue queue = new BoundedBlockingQueue(2);

        queue.enqueue(1);
        queue.enqueue(2);

        Thread producer = new Thread(() -> {
            try {
                queue.enqueue(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        Thread.sleep(100); // Ensure the producer thread is blocked

        assertEquals(2, queue.size());
        queue.dequeue();
        Thread.sleep(100); // Allow the producer thread to enqueue

        assertEquals(2, queue.size());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @Test
    void testBlockingOnEmptyQueue() throws InterruptedException {
        BoundedBlockingQueue queue = new BoundedBlockingQueue(2);

        Thread consumer = new Thread(() -> {
            try {
                queue.dequeue();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        consumer.start();
        Thread.sleep(100); // Ensure the consumer thread is blocked

        queue.enqueue(1);
        Thread.sleep(100); // Allow the consumer thread to dequeue

        assertEquals(0, queue.size());
    }

    @Test
    void testCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new BoundedBlockingQueue(0));
    }
}