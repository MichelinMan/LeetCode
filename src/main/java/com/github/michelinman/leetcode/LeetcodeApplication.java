package com.github.michelinman.leetcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.github.michelinman.leetcode.concurrency.zeroEvenOdd.ZeroEvenOddRunner.runZeroEvenOdd;

@SpringBootApplication
public class LeetCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeetCodeApplication.class, args);

//        foobar();
        runZeroEvenOdd(5);
    }

}