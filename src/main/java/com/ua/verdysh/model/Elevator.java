package com.ua.verdysh.model;

public class Elevator {
    public static final int MIN_FLOOR = 1;
    private Person[] peopleInside;
    
    public Elevator(int maxCapacity) {
        peopleInside = new Person[maxCapacity];
    }

    public Person[] getPeopleInside() {
        return peopleInside;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("| ");
        for (Person p : peopleInside) {
            if (p != null) {
                sb.append(p).append(" ");
            } else {
                sb.append(".").append("  ");
            }
        }
        sb.append("|");
        return sb.toString();
    }
}
