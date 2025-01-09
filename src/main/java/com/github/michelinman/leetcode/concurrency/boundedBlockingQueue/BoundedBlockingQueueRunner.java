package com.github.michelinman.leetcode.concurrency.boundedBlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

public class BoundedBlockingQueueRunner {

    public static List<Integer> runBoundedBlockingQueue(int capacity, int producerCount, int consumerCount, String[] operations, int[][] values) {
        BoundedBlockingQueue queue = new BoundedBlockingQueue(capacity);
        List<Integer> output = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(operations.length);

        IntConsumer producer = (int value) -> {
            try {
                queue.enqueue(value);
                System.out.println("Enqueued: " + value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        };

        Runnable consumer = () -> {
            try {
                int value = queue.dequeue();
                System.out.println("Dequeued: " + value);
                synchronized (output) {
                    output.add(value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        };

        int[] producerIndex = {0};

        for (String operation : operations) {
            if (operation.equals("enqueue")) {
                new Thread(() -> producer.accept(values[producerIndex[0]++][0])).start();
            } else if (operation.equals("dequeue")) {
                new Thread(consumer).start();
            }
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return output;
    }

    public static void runBoundedBlockingQueueExamples() {
        System.out.println("Example 1:");
        List<Integer> output1 = runBoundedBlockingQueue(2, 1, 1,
                new String[]{"enqueue","dequeue","dequeue","enqueue","enqueue","enqueue","enqueue","dequeue"},
                new int[][]{{2}, {1}, {}, {}, {0}, {2}, {3}, {4}});
        System.out.println(output1); // Output: [1,0,2,2]

        System.out.println("Example 2:");
        List<Integer> output2 = runBoundedBlockingQueue(3, 3, 4,
                new String[]{"enqueue","enqueue","enqueue","dequeue","dequeue","dequeue","enqueue"},
                new int[][]{{3}, {1}, {0}, {2}, {}, {}, {}, {3}});
        System.out.println(output2); // Output: [1,0,2,1]
    }
}
