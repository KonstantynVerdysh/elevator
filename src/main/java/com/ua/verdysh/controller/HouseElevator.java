package com.ua.verdysh.controller;

import com.ua.verdysh.model.Elevator;

public class HouseElevator extends Elevator implements Movable {
    private int maxFloor;
    private int currentFloor;
    private boolean isUp;

    public HouseElevator(int maxFloor, int maxCapacity) {
        super(maxCapacity);
        this.maxFloor = maxFloor;
        currentFloor = 1;
        isUp = true;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public boolean isUp() {
        return isUp;
    }

    public void move(int minDesireFloor, int maxDesireFloor) {
        if (!setCurrentFloor(minDesireFloor, maxDesireFloor)) {
            changeDirection();
        }
    }

    public void changeDirection() {
        isUp = !isUp;
    }

    private boolean setCurrentFloor(int minDesireFloor, int maxDesireFloor) {
        if (isUp) {
            if (currentFloor < maxFloor && currentFloor < maxDesireFloor) {
                currentFloor++;
                return true;
            }
        } else {
            if (currentFloor > MIN_FLOOR && currentFloor > minDesireFloor) {
                currentFloor--;
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return isUp ? "↑" + super.toString() + "↑" : "↓" + super.toString() + "↓";
    }
}
