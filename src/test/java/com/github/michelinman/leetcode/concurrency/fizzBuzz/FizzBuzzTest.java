package com.github.michelinman.leetcode.concurrency.fizzBuzz;

import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBuzzTest {

    @Test
    void testFizzBuzz() throws InterruptedException {
        int n = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n);
        StringBuilder output = new StringBuilder();
        CountDownLatch latch = new CountDownLatch(n);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable printFizz = () -> {
            output.append("fizz ");
            latch.countDown();
        };

        Runnable printBuzz = () -> {
            output.append("buzz ");
            latch.countDown();
        };

        Runnable printFizzBuzz = () -> {
            output.append("fizzbuzz ");
            latch.countDown();
        };

        IntConsumer printNumber = number -> {
            output.append(number).append(" ");
            latch.countDown();
        };

        executor.execute(() -> {
            try {
                fizzBuzz.fizz(printFizz);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.execute(() -> {
            try {
                fizzBuzz.buzz(printBuzz);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.execute(() -> {
            try {
                fizzBuzz.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.execute(() -> {
            try {
                fizzBuzz.number(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        latch.await(1, TimeUnit.SECONDS);
        executor.shutdown();

        assertEquals("1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz ", output.toString());
    }
}