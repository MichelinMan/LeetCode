package com.github.michelinman.leetcode.concurrency.trafficLight;

public class TrafficLightRunner {
    public static void runTrafficLight() {
        TrafficLight trafficLight = new TrafficLight();

        // Example 1
        System.out.println("Example 1:");
        trafficLight.carArrived(1, 1, 2,
                () -> System.out.println("Traffic Light On Road A Is Green"),
                () -> System.out.println("Car 1 Has Passed Road A In Direction 2"));
        trafficLight.carArrived(3, 1, 1,
                () -> System.out.println("Traffic Light On Road A Is Green"),
                () -> System.out.println("Car 3 Has Passed Road A In Direction 1"));
        trafficLight.carArrived(5, 1, 2,
                () -> System.out.println("Traffic Light On Road A Is Green"),
                () -> System.out.println("Car 5 Has Passed Road A In Direction 2"));
        trafficLight.carArrived(2, 2, 4,
                () -> System.out.println("Traffic Light On Road B Is Green"),
                () -> System.out.println("Car 2 Has Passed Road B In Direction 4"));
        trafficLight.carArrived(4, 2, 3,
                () -> System.out.println("Traffic Light On Road B Is Green"),
                () -> System.out.println("Car 4 Has Passed Road B In Direction 3"));

        // Example 2
        System.out.println("\nExample 2:");
        trafficLight.carArrived(1, 1, 2,
                () -> System.out.println("Traffic Light On Road A Is Green"),
                () -> System.out.println("Car 1 Has Passed Road A In Direction 2"));
        trafficLight.carArrived(2, 2, 4,
                () -> System.out.println("Traffic Light On Road B Is Green"),
                () -> System.out.println("Car 2 Has Passed Road B In Direction 4"));
        trafficLight.carArrived(3, 2, 3,
                () -> System.out.println("Traffic Light On Road B Is Green"),
                () -> System.out.println("Car 3 Has Passed Road B In Direction 3"));
        trafficLight.carArrived(5, 1, 1,
                () -> System.out.println("Traffic Light On Road A Is Green"),
                () -> System.out.println("Car 5 Has Passed Road A In Direction 1"));
        trafficLight.carArrived(4, 2, 3,
                () -> System.out.println("Traffic Light On Road B Is Green"),
                () -> System.out.println("Car 4 Has Passed Road B In Direction 3"));
    }
}