package com.github.michelinman.leetcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.github.michelinman.leetcode.concurrency.buildingH2O.H2ORunner.runH2O;
import static com.github.michelinman.leetcode.concurrency.fooBar.FooBarRunner.runFooBar;
import static com.github.michelinman.leetcode.concurrency.printInOrder.FooRunner.runFoo;

@SpringBootApplication
public class LeetCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeetCodeApplication.class, args);

//        runFooBar();
//        runZeroEvenOdd(5);
//        runFoo();
        runH2O();
    }

}