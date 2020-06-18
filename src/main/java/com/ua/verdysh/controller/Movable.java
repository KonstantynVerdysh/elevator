package com.ua.verdysh.controller;

public interface Movable {
    void move(int min, int max);
    void changeDirection();
    boolean isUp();
}
