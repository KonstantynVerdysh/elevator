package com.ua.verdysh.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ua.verdysh.model.Floor;
import com.ua.verdysh.model.House;

public class UserInterface {
    private static final String EMPTY_ELEVATOR = " |                | ";
    private static final String WHITESPACE = "  ";
    private House house;

    public UserInterface(House house) {
        this.house = house;
    }

    public void prettyFormatter() {
        printStep().stream()
                .forEach(System.out::print);
    }
    
    private List<String> printStep() {
        List<String> result = new ArrayList<>();
        result.add("Press Ctrl+C to exit\n" + String.format("%12s%d%s\n", "***FLOOR ", house.getElevator().getCurrentFloor(), "***"));
        for (Map.Entry<Integer, Floor> entry : house.getHouseUnit().entrySet()) {
            result.add(1, createRow(entry.getValue(), entry.getKey()));
        }
        return result;
    }
    
    private String createRow(Floor floor, int currentFloor) {
        StringBuilder rowBuilder = new StringBuilder();
        if (currentFloor == house.getElevator().getCurrentFloor()) {
            rowBuilder.append(house.getElevator() + WHITESPACE);
            rowBuilder.append(floor).append("\n");
        } else {
            rowBuilder.append(EMPTY_ELEVATOR + WHITESPACE);
            rowBuilder.append(floor).append("\n");
        }
        return rowBuilder.toString();
    }
}
 