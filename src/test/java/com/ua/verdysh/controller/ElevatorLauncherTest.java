package com.ua.verdysh.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ua.verdysh.model.House;
import com.ua.verdysh.model.HouseElevator;
import com.ua.verdysh.model.Person;

class ElevatorLauncherTest {
    private ElevatorLauncher runner = new ElevatorLauncher(new DataGenerator());
    private static final int MAX_ELEVATOR_CAPACITY = 5;
    
    @Test
    final void process() {
        House house = runner.getHouse();
        HouseElevator elevator = house.getElevator();
        
        assertTrue(elevator.getCurrentFloor() == 1);
        
        int peopleOnFloorCountBefore = house.getHouseUnit().get(1).getPeopleOnFloor().size();
        int peopleInElevatorCountBefore = getPeopleInElevatorCount(elevator);

        runner.process();
        
        assertTrue(elevator.getCurrentFloor() == 2);
        
        int peopleOnFloorCountAfter = house.getHouseUnit().get(1).getPeopleOnFloor().size();
        int peopleInElevatorCountAfter = getPeopleInElevatorCount(elevator);
        
        if (peopleOnFloorCountBefore <= MAX_ELEVATOR_CAPACITY) {
            assertEquals(peopleOnFloorCountBefore, peopleInElevatorCountAfter);
            assertEquals(peopleInElevatorCountBefore, peopleOnFloorCountAfter);
        } else {
            assertTrue(peopleInElevatorCountAfter == MAX_ELEVATOR_CAPACITY);
            assertTrue(peopleOnFloorCountAfter == peopleOnFloorCountBefore - MAX_ELEVATOR_CAPACITY);
        }
        
        Person[] peopleInElevator = house.getElevator().getPeopleInside();
        int maxDesireFloor = 0;
        while (getPeopleInElevatorCount(elevator) != 0) {
            assertTrue(elevator.isUp());
            runner.process();
            if (getMaxDesireFloor(peopleInElevator) != 0) {
                maxDesireFloor = getMaxDesireFloor(peopleInElevator);
            }
        }

        if (maxDesireFloor != 0) {
            assertEquals(maxDesireFloor, elevator.getCurrentFloor());
            assertFalse(elevator.isUp());
        } else {
            assertTrue(elevator.getCurrentFloor() == 2);
            assertTrue(elevator.isUp());
        }
    }
    
    private int getMaxDesireFloor(Person[] peopleInElevator) {
        int max = 0;
        for (Person person : peopleInElevator) {
            if (person != null && person.getDesireFloor() > max) {
                max = person.getDesireFloor();
            }
        }
        return max;
    }
    
    private int getPeopleInElevatorCount(HouseElevator elevator) {
        int result = 0;
        for (Person p : elevator.getPeopleInside()) {
            if (p != null) {
                result++;
            }
        }
        return result;
    }
}
