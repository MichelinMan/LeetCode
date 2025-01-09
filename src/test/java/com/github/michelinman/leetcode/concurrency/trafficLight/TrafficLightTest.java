package com.github.michelinman.leetcode.concurrency.trafficLight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class TrafficLightTest {
    private TrafficLight trafficLight;

    @BeforeEach
    void setUp() {
        trafficLight = new TrafficLight();
    }

    @Test
    void testCarArrivedOnRoadA() {
        Runnable turnGreen = mock(Runnable.class);
        Runnable crossCar = mock(Runnable.class);

        trafficLight.carArrived(1, 1, 1, turnGreen, crossCar);

        verify(turnGreen, never()).run();
        verify(crossCar, times(1)).run();
    }

    @Test
    void testCarArrivedOnRoadB() {
        Runnable turnGreen = mock(Runnable.class);
        Runnable crossCar = mock(Runnable.class);

        trafficLight.carArrived(2, 2, 3, turnGreen, crossCar);

        verify(turnGreen, times(1)).run();
        verify(crossCar, times(1)).run();
    }

    @Test
    void testMultipleCarsOnSameRoad() {
        Runnable turnGreen = mock(Runnable.class);
        Runnable crossCar = mock(Runnable.class);

        trafficLight.carArrived(1, 1, 1, turnGreen, crossCar);
        trafficLight.carArrived(3, 1, 2, turnGreen, crossCar);

        verify(turnGreen, never()).run();
        verify(crossCar, times(2)).run();
    }

    @Test
    void testSwitchingRoads() {
        Runnable turnGreen = mock(Runnable.class);
        Runnable crossCar = mock(Runnable.class);

        trafficLight.carArrived(1, 1, 1, turnGreen, crossCar);
        trafficLight.carArrived(2, 2, 3, turnGreen, crossCar);
        trafficLight.carArrived(3, 1, 2, turnGreen, crossCar);

        verify(turnGreen, times(2)).run();
        verify(crossCar, times(3)).run();
    }
}