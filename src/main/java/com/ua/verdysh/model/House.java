package com.ua.verdysh.model;

import java.util.Map;

import com.ua.verdysh.controller.HouseElevator;

public class House {
    private HouseElevator elevator;
    private Map<Integer, Floor> houseUnit;

    public House(Map<Integer, Floor> house, HouseElevator elevator) {
        this.houseUnit = house;
        this.elevator = elevator;
    }

    public Map<Integer, Floor> getHouseUnit() {
        return houseUnit;
    }

    public int getSize() {
        return houseUnit.size();
    }

    public HouseElevator getElevator() {
        return elevator;
    }
}
