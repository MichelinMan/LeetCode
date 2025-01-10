package com.github.michelinman.leetcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.github.michelinman.leetcode.concurrency.buildingH2O.H2ORunner.runH2O;
import static com.github.michelinman.leetcode.concurrency.diningPhilosophers.DiningPhilosophersRunner.runDinningPhilosophers;
import static com.github.michelinman.leetcode.concurrency.fizzBuzz.FizzBuzzRunner.runFizzBuzz;
import static com.github.michelinman.leetcode.concurrency.htmlParser.HtmlParserRunner.runHtmlParserExamples;
import static com.github.michelinman.leetcode.concurrency.printFooBarAlternately.FooBarRunner.runFooBar;
import static com.github.michelinman.leetcode.concurrency.printInOrder.FooRunner.runFoo;
import static com.github.michelinman.leetcode.concurrency.printZeroEvenOdd.ZeroEvenOddRunner.runZeroEvenOdd;
import static com.github.michelinman.leetcode.concurrency.trafficLight.TrafficLightRunner.*;

@SpringBootApplication
public class LeetCodeApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(LeetCodeApplication.class, args);

//        runFooBar(10);
        runZeroEvenOdd(5);
//        runFoo(new int[]{1, 3, 2});
//        runH2O("OOHHHH");
//        runFizzBuzz(15);
//        runTrafficLightExamples();
//        runDinningPhilosophers(5);
//        runHtmlParserExamples();
    }

}