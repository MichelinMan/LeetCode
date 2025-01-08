package com.github.michelinman.leetcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.github.michelinman.leetcode.foobar.FooBarRunner.foobar;

@SpringBootApplication
public class LeetcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeetcodeApplication.class, args);

        foobar();
    }

}