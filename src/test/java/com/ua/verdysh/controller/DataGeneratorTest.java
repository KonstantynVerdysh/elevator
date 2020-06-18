package com.ua.verdysh.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.ua.verdysh.model.Floor;
import com.ua.verdysh.model.House;
import com.ua.verdysh.model.HouseElevator;
import com.ua.verdysh.model.Person;

class DataGeneratorTest {
    private DataGenerator generator = new DataGenerator();
    private static final int MAX_HOUSE_SIZE = 20;
    private static final int MIN_HOUSE_SIZE = 5;
    private static final int MAX_ELEVATOR_CAPACITY = 5;
    private static final int START_CURRENT_FLOOR = 1;
    
    @Test
    final void generateHouse_returnNewHouseObject() {
        House house = generator.generateHouse();
        
        assertTrue(house.getSize() >= MIN_HOUSE_SIZE);
        assertTrue(house.getSize() <= MAX_HOUSE_SIZE);
        
        HouseElevator elevator = house.getElevator();
        
        assertTrue(elevator.getPeopleInside().length == MAX_ELEVATOR_CAPACITY);
        assertTrue(elevator.getCurrentFloor() == START_CURRENT_FLOOR);
        assertFalse(elevator.getCurrentFloor() == 5);
        
        Map<Integer, Floor> houseUnit = house.getHouseUnit();
        
        assertTrue(houseUnit.size() >= MIN_HOUSE_SIZE);
        assertTrue(houseUnit.containsKey(5));
        assertFalse(houseUnit.containsKey(25));
        
        List<Integer> floors = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        List<Integer> peopleOnFloorCount = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        for (Map.Entry<Integer, Floor> entry : houseUnit.entrySet()) {
            List<Person> peopleOnFloor = entry.getValue().getPeopleOnFloor();
            
            assertTrue(peopleOnFloorCount.contains(peopleOnFloor.size()));
            
            for (Person person : peopleOnFloor) {
                
                assertTrue(person.getDesireFloor() > 0);
                assertTrue(floors.contains(person.getDesireFloor()));
            }
        }
    }

    @Test
    final void generatePersonOnFloor_generateNewPersonOnFloor() {
        House house = generator.generateHouse();
        int firstFloorPeopleCountBefore = house.getHouseUnit().get(1).getPeopleOnFloor().size();
        int addPeopleCount = 10;
        
        generator.generatePersonOnFloor(house.getHouseUnit().get(1), addPeopleCount, house.getSize());

        int firstFloorPeopleCountAfter = house.getHouseUnit().get(1).getPeopleOnFloor().size();
        assertEquals(firstFloorPeopleCountBefore + addPeopleCount, firstFloorPeopleCountAfter);
    }

}
