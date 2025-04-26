package org.elevator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Elevator {
    private String id;
    private final int capacity;
    private int currentFloor;
    private Direction currentDirection;
    private final List<Request> requests;

    public Elevator(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.currentFloor = 1;
        this.currentDirection = Direction.UP;
        this.requests = new ArrayList<>();
    }

    public synchronized void addRequest(Request request) {
        if (capacity > requests.size()) {
            System.out.println("Elevator id : " + id + " added request : " + request);
            requests.add(request);
            notifyAll();
        }
    }

    /**
     * The outer loop is an infinite loop, meaning that the method will continue running indefinitely (or until the program is explicitly terminated).
     * This loop keeps the method running, continuously processing requests as long as they exist.
     * Since processRequests() is synchronized, only one thread can execute this loop at a time, and no other thread can access this method concurrently.
     */
    public synchronized void processRequests() {
        while (true) {
            while (!requests.isEmpty()) {
                Request request = getNextRequest();
                processRequest(request);
            }

            try {
                wait();
            } catch (InterruptedException interruptedException) {
                System.out.println(interruptedException.getMessage());
            }
        }
    }

    private void processRequest(Request request) {
        int startFloor = request.getSourceFloor();
        int endFloor = request.getDestinationFloor();

        if (startFloor < endFloor) {
            currentDirection = Direction.UP;
            for (int i=startFloor;i<=endFloor;i++) {
                currentFloor = i;
                System.out.println("Elevator id " + id + " reached floor " + currentFloor);
                try {
                    Thread.sleep(1000); // simulating movement
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (startFloor > endFloor) {
            currentDirection = Direction.DOWN;
            for (int i = startFloor; i >=  endFloor ; i--) {
                currentFloor = i;
                System.out.println("Elevator id " + id + " reached floor " + currentFloor);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * We have used "while" and not "if" because, The while (requests.isEmpty()) loop ensures that the condition is rechecked every time
     * the thread wakes up. If the list is still empty (due to a spurious wakeup or another thread modifying the list),
     * the thread will go back to waiting, and the condition is checked again before proceeding.
     * So, the use of while ensures that the thread is only allowed to continue if there is at least one item in the list.
     * If the list is still empty after the thread wakes up, it will immediately go back to waiting.
     */
    private synchronized Request getNextRequest() {
        while (requests.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException interruptedException) {
                System.out.println("interrupted thread : " + interruptedException.getMessage());
            }
        }

        return requests.remove(0);
    }

    public void run() {
        processRequests();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }
}
