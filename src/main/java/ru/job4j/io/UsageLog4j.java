package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        short place = 1;
        double weight = 80.5;
        float height = 180.5f;
        boolean run = true;
        long km = 9L;
        char symbol = 55;
        LOG.debug("User info name : {}, age : {}, place : {}, weight : {}, height : {}, run : {}, km : {}, symbol : {}",
                name, age, place, weight, height, run, km, symbol);
    }
}
