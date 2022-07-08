package ru.job4j.parking;

import java.util.List;

public interface Parking {

    boolean add(Car car);
    List<Car> findAll();
}
