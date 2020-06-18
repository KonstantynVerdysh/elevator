package com.ua.verdysh.model;

public class Person {
    private int desireFloor;

    public Person(int desireFloor) {
        this.desireFloor = desireFloor;
    }

    public int getDesireFloor() {
        return desireFloor;
    }

    @Override
    public String toString() {
        return desireFloor < 10 ? String.valueOf(desireFloor) + " " : String.valueOf(desireFloor);
    }
}
