package com.github.michelinman.leetcode.concurrency.printInOrder;

public class FooRunner {

    public static void runFoo(int[] nums) {
        Foo foo = new Foo();

        Thread threadA = new Thread(() -> {
            try {
                foo.first(() -> System.out.print("first"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                foo.second(() -> System.out.print("second"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                foo.third(() -> System.out.print("third"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        for (int num : nums) {
            switch (num) {
                case 1:
                    threadA.start();
                    break;
                case 2:
                    threadB.start();
                    break;
                case 3:
                    threadC.start();
                    break;
            }
        }
    }
}