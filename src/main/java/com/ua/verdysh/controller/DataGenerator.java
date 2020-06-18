package com.ua.verdysh.controller;

import java.util.HashMap;
import java.util.Map;

import com.ua.verdysh.model.Floor;
import com.ua.verdysh.model.House;
import com.ua.verdysh.model.HouseElevator;
import com.ua.verdysh.model.Person;

public class DataGenerator {
    private static final int MAX_HOUSE_SIZE = 20;
    private static final int MIN_HOUSE_SIZE = 5;
    private static final int MAX_PEOPLE_ON_FLOOR = 10;
    private static final int MIN_PEOPLE_ON_FLOOR = 0;
    private static final int MAX_ELEVATOR_CAPACITY = 5;

    public House generateHouse() {
        int houseSize = randomInt(MAX_HOUSE_SIZE, MIN_HOUSE_SIZE);
        Map<Integer, Floor> house = generateFloors(houseSize);
        generatePeople(house);
        return new House(house, new HouseElevator(houseSize, MAX_ELEVATOR_CAPACITY));
    }

    public void generatePersonOnFloor(Floor floor, int amount, int houseSize) {
        for (int count = 0; count < amount; count++) {
            floor.addPersonOnFloor(new Person(getDesireFloor(floor, houseSize)));
        }
    }

    private void generatePeople(Map<Integer, Floor> house) {
        for (Map.Entry<Integer, Floor> entry : house.entrySet()) {
            int peopleOnFloor = randomInt(MAX_PEOPLE_ON_FLOOR, MIN_PEOPLE_ON_FLOOR);
            generatePersonOnFloor(entry.getValue(), peopleOnFloor, house.size());
        }
    }

    private Map<Integer, Floor> generateFloors(int houseSize) {
        Map<Integer, Floor> result = new HashMap<>();
        for (int count = 1; count <= houseSize; count++) {
            result.put(count, new Floor());
        }
        return result;
    }

    private int getDesireFloor(Floor floor, int houseSize) {
        int result = randomInt(houseSize);
        if (result == floor.getFloorNumber()) {
            while (result == floor.getFloorNumber()) {
                result = randomInt(houseSize);
            }
        }
        return result;
    }

    private int randomInt(int max) {
        return 1 + (int) (Math.random() * max);
    }

    private static int randomInt(int max, int min) {
        return min + (int) (Math.random() * (max - min + 1));
    }
}
