package org.elevator.controller;

import org.elevator.Elevator;
import org.elevator.Request;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    public final List<Elevator> elevators;

    /**
     *  new Thread(elevator::run).start(); This line creates a new thread for each Elevator instance. elevator::run: This is a method reference to the run method of the Elevator object.
     *  It tells the Thread to execute the run() method of the elevator object when the thread starts. In Java, the run() method defines the code that will be executed by the thread.
     *  So, each elevator will likely be running some sort of logic (such as moving between floors, picking up passengers, etc.) in its own thread.
     *  new Thread(...).start(): This creates a new thread and immediately starts it. The start() method causes the run() method of the elevator to be executed asynchronously, which means each elevator will work independently in parallel to the others.
     */
    public ElevatorController(int numElevators, int capacity) {
        elevators = new ArrayList<>();
        for (int i=0;i<numElevators;i++) {
            Elevator elevator  = new Elevator(String.valueOf(i), capacity);
            elevators.add(elevator);
            new Thread(elevator::run).start();
        }
    }

    public void requestElevator(int sourceFloor, int destinationFloor) {
          Elevator optimalElevator = findOptimalElevator(sourceFloor, destinationFloor);
          optimalElevator.addRequest(new Request(sourceFloor, destinationFloor));
    }

    private Elevator findOptimalElevator(int sourceFloor, int destinationFloor) {
        Elevator optimalElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int currentFloor = elevator.getCurrentFloor();
            int distance = Math.abs(currentFloor - sourceFloor);
            if (distance < minDistance) {
                minDistance = distance;
                optimalElevator = elevator;
            }
        }

        return optimalElevator;
    }
}
