package com.ua.verdysh.model.interfaces;

public interface Movable {
    void move(int min, int max);
    void changeDirection();
    boolean isUp();
}
