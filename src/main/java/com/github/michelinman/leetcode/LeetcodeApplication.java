package com.github.michelinman.leetcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.github.michelinman.leetcode.concurrency.printFooBarAlternately.FooBarRunner.runFooBar;
import static com.github.michelinman.leetcode.concurrency.printInOrder.FooRunner.runFoo;

@SpringBootApplication
public class LeetCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeetCodeApplication.class, args);

        runFooBar(2);
//        runZeroEvenOdd(5);
//        runFoo(new int[]{1, 3, 2});
//        runH2O();
//        runFizzBuzz(15);
//        runBoundedBlockingQueue(5);
//        runTrafficLight();
    }


}