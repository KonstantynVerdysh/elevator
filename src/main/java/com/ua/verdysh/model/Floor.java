package com.ua.verdysh.model;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private static int counter = 0;
    private final int floorNumber = ++counter;
    
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

    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Person p : peopleOnFloor)
            result.append(p.getDesireFloor() + " ");
        return result.toString();
    }
}
