package com.ua.verdysh.controller;

import com.ua.verdysh.view.UserInterface;

public class ApplicationRunner {
    public void run() {
        DataGenerator generator = new DataGenerator();
        DataHandler runner = new DataHandler(generator);
        UserInterface ui = new UserInterface(runner.getHouse());

        while (true) {
            ui.prettyFormatter();
            runner.process();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
