package ru.job4j.parking;


import java.util.List;

public interface Parking {

    public boolean add(Car car);
    public List<Car> findAll();
}
