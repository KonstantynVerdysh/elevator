package com.ua.verdysh.model;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private static int counter = 0;
    private final int floor = ++counter;
    private List<Person> peopleOnFloor;

    public Floor() {
        peopleOnFloor = new ArrayList<>();
    }

    public List<Person> getPeopleOnFloor() {
        return peopleOnFloor;
    }

    public void addPersonOnFloor(Person person) {
        peopleOnFloor.add(person);
    }

    public static int getCounter() {
        return counter;
    }

    public int getFloor() {
        return floor;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Person p : peopleOnFloor)
            result.append(p.getDesireFloor() + " ");
        return result.toString();
    }
}
