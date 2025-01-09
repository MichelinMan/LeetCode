package com.github.michelinman.leetcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.github.michelinman.leetcode.concurrency.boundedBlockingQueue.BoundedBlockingQueueRunner.runBoundedBlockingQueue;
import static com.github.michelinman.leetcode.concurrency.buildingH2O.H2ORunner.runH2O;
import static com.github.michelinman.leetcode.concurrency.fizzBuzz.FizzBuzzRunner.runFizzBuzz;
import static com.github.michelinman.leetcode.concurrency.printFooBarAlternately.FooBarRunner.runFooBar;
import static com.github.michelinman.leetcode.concurrency.printInOrder.FooRunner.runFoo;
import static com.github.michelinman.leetcode.concurrency.trafficLight.TrafficLightRunner.runTrafficLight;
import static com.github.michelinman.leetcode.concurrency.printZeroEvenOdd.ZeroEvenOddRunner.runZeroEvenOdd;

@SpringBootApplication
public class LeetCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeetCodeApplication.class, args);

//        runFooBar(2);
        runZeroEvenOdd(5);
//        runFoo(new int[]{1, 3, 2});
//        runH2O();
//        runFizzBuzz(15);
//        runBoundedBlockingQueue(5);
//        runTrafficLight();
    }


}