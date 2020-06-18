package com.ua.verdysh.controller;

import java.util.ArrayList;
import java.util.List;

import com.ua.verdysh.model.Floor;
import com.ua.verdysh.model.House;
import com.ua.verdysh.model.Person;

public class DataHandler {
    private DataGenerator generator;
    private House house;

    public DataHandler(DataGenerator generator) {
        this.generator = generator;
        house = generator.generateHouse();
    }

    public House getHouse() {
        return house;
    }

    public void process() {
        HouseElevator elevator = house.getElevator();
        Person[] peopleInElevator = elevator.getPeopleInside();
        
        int currentFloor = elevator.getCurrentFloor();
        Floor floor = house.getHouseUnit().get(currentFloor);
        boolean isUp = elevator.isUp();

        int peopleLeftCount = getLeftElevatorCount(peopleInElevator, currentFloor);
        int[] emptySlot = getSlotIndices(peopleInElevator);
        
        if (emptySlot.length > 0) {
            Person[] peopleGoIn = processPeopleOnFloor(floor, currentFloor, emptySlot.length, isUp);
            for (int count = 0; count < peopleGoIn.length; count++) {
                addPeopleToElevator(peopleInElevator, emptySlot[count], peopleGoIn[count]);
            }
        }
        addNewPeopleOnFloor(floor, peopleLeftCount);
        
        int min = getMin(peopleInElevator);
        int max = getMax(peopleInElevator);
        
        elevator.move(min, max);
    }
    
    private int getMax(Person[] peopleInElevator) {
        int max = 5;
        for (Person person : peopleInElevator) {
            if (person != null && person.getDesireFloor() > max) {
                max = person.getDesireFloor();
            }
        }
        return max;
    }
    
    private int getMin(Person[] peopleInElevator) {
        int min = 0;
        for (Person person : peopleInElevator) {
            if (person != null && person.getDesireFloor() < min) {
                min = person.getDesireFloor();
            }
        }
        return min;
    }

    private int getLeftElevatorCount(Person[] peopleInElevator, int currentFloor) {
        int result = 0;
        for (int count = 0; count < peopleInElevator.length; count++) {
            if (peopleInElevator[count] != null && peopleInElevator[count].getDesireFloor() == currentFloor) {
                peopleInElevator[count] = null;
                result++;
            }
        }
        return result;
    }

    private int[] getSlotIndices(Person[] peopleInElevator) {
        int[] result = new int[0];
        int slotCount = getSlotCount(peopleInElevator);
        if (slotCount > 0) {
            result = new int[slotCount];
            for (int count = 0; count < peopleInElevator.length; count++) {
                if (peopleInElevator[count] == null) {
                    slotCount--;
                    result[slotCount] = count;
                }
            }
        }
        return result;
    }
    
    private int getSlotCount(Person[] peopleInElevator) {
        int result = 0;
        for (Person person : peopleInElevator) {
            if (person == null) {
                result++;
            }
        }
        return result;
    }

    private Person[] processPeopleOnFloor(Floor floor, int currentFloor, int emptySlotCount, boolean isUp) {
        List<Person> peopleToLift = new ArrayList<>();
        List<Person> peopleOnFloor = floor.getPeopleOnFloor();
        for (int count = 0; count < peopleOnFloor.size(); count++) {
            int personDesireFloor = peopleOnFloor.get(count).getDesireFloor();
            if ((isUp && personDesireFloor > currentFloor) || (!isUp && personDesireFloor < currentFloor)) {
                Person personToLift = peopleOnFloor.get(count);
                peopleToLift.add(personToLift);
                peopleOnFloor.remove(personToLift);
                --count;
            }
        }
        return getPeopleByEmptySlot(peopleToLift, peopleOnFloor, emptySlotCount);
    }
    
    private Person[] getPeopleByEmptySlot(List<Person> peopleToLift, List<Person> peopleOnFloor, int emptySlotCount) {
        Person[] result;
        if (peopleToLift.size() > emptySlotCount) {
            result = new Person[emptySlotCount];
            separatePeopleByEmptySlot(result, peopleToLift);
            peopleOnFloor.addAll(peopleToLift);
        } else {
            result = new Person[peopleToLift.size()];
            separatePeopleByEmptySlot(result, peopleToLift);
        }
        return result;
    }

    private void separatePeopleByEmptySlot(Person[] people, List<Person> peopleToLift) {
        for (int count = 0; count < people.length; count++) {
            people[count] = peopleToLift.get(0);
            peopleToLift.remove(0);
        }
    }

    private void addPeopleToElevator(Person[] peopleInElevator, int slot, Person person) {
        peopleInElevator[slot] = person;
    }

    private void addNewPeopleOnFloor(Floor floor, int peopleGoOutCount) {
        generator.generatePersonOnFloor(floor, peopleGoOutCount);
    }
}
