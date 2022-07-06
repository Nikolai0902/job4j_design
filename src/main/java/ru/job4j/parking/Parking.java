package ru.job4j.parking;

import ru.job4j.collection.list.List;

public interface Parking {

    public void add(Car car);
    public List<Car> findAll();
}
