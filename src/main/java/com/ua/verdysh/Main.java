package com.ua.verdysh;

import com.ua.verdysh.controller.DataGenerator;
import com.ua.verdysh.controller.ElevatorLauncher;
import com.ua.verdysh.view.UserInterface;

public class Main {
    public static void main(String[] args) {
        DataGenerator generator = new DataGenerator();
        ElevatorLauncher runner = new ElevatorLauncher(generator);
        UserInterface ui = new UserInterface(runner.getHouse());

        while (true) {
            ui.prettyFormatter();
            runner.process();
            try {
                Thread.sleep(999);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
