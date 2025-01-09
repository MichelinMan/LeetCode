package com.github.michelinman.leetcode.concurrency.trafficLight;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficLight {
    private final Lock lock;
    private int currentGreenRoad;

    public TrafficLight() {
        this.lock = new ReentrantLock();
        this.currentGreenRoad = 1; // Initially, the traffic light is green on road A (roadId 1)
    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn the light to green on the current road
            Runnable crossCar    // Use crossCar.run() to let the car cross the intersection
    ) {
        lock.lock();
        try {
            if (roadId != currentGreenRoad) {
                turnGreen.run();
                currentGreenRoad = roadId;
            }
            crossCar.run();
        } finally {
            lock.unlock();
        }
    }
}