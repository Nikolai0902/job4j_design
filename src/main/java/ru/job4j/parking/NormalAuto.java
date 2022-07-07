package ru.job4j.parking;

public class NormalAuto implements Car {

    public static final int AUTO_SIZE = 1;

    @Override
    public int getSize() {
        return AUTO_SIZE;
    }
}
