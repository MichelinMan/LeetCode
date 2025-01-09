package com.github.michelinman.leetcode.concurrency.trafficLight;

import java.util.ArrayList;
import java.util.List;

public class TrafficLightRunner {
    public static List<String> runTrafficLight(int[] cars, int[] directions, int[] arrivalTimes) {
        TrafficLight trafficLight = new TrafficLight();
        List<String> output = new ArrayList<>();

        for (int i = 0; i < cars.length; i++) {
            int carId = cars[i];
            int direction = directions[i];
            int arrivalTime = arrivalTimes[i];

            trafficLight.carArrived(arrivalTime, carId, direction,
                    () -> output.add("Traffic Light On Road " + (direction <= 2 ? "A" : "B") + " Is Green"),
                    () -> output.add("Car " + carId + " Has Passed Road " + (direction <= 2 ? "A" : "B") + " In Direction " + direction));
        }

        return output;
    }

    public static void runTrafficLightExample1() {
        System.out.println("Example 1:");
        int[] cars = {1, 3, 5, 2, 4};
        int[] directions = {2, 1, 2, 4, 3};
        int[] arrivalTimes = {10, 20, 30, 40, 50};
        runTrafficLight(cars, directions, arrivalTimes).forEach(System.out::println);
    }

    public static void runTrafficLightExample2() {
        System.out.println("Example 2:");
        int[] cars = new int[]{1, 2, 3, 4, 5};
        int[] directions = new int[]{2, 4, 3, 3, 1};
        int[] arrivalTimes = new int[]{10, 20, 30, 40, 40};
        runTrafficLight(cars, directions, arrivalTimes).forEach(System.out::println);
    }
}