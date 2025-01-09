package com.github.michelinman.leetcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.github.michelinman.leetcode.concurrency.boundedBlockingQueue.BoundedBlockingQueueRunner.runBoundedBlockingQueue;
import static com.github.michelinman.leetcode.concurrency.buildingH2O.H2ORunner.runH2O;
import static com.github.michelinman.leetcode.concurrency.fizzBuzz.FizzBuzzRunner.runFizzBuzz;
import static com.github.michelinman.leetcode.concurrency.fooBar.FooBarRunner.runFooBar;
import static com.github.michelinman.leetcode.concurrency.printInOrder.FooRunner.runFoo;
import static com.github.michelinman.leetcode.concurrency.trafficLight.TrafficLightRunner.runTrafficLight;
import static com.github.michelinman.leetcode.concurrency.zeroEvenOdd.ZeroEvenOddRunner.runZeroEvenOdd;

@SpringBootApplication
public class LeetCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeetCodeApplication.class, args);

//        runFooBar();
//        runZeroEvenOdd(5);
//        runFoo();
//        runH2O();
//        runFizzBuzz(15);
//        runBoundedBlockingQueue(5);
        runTrafficLight();
    }


}