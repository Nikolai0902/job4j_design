package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;


public class ParkingCar implements Parking {

    public int lotNormal;
    public int lotBig;

    public final List<Car> placeNormal = new ArrayList<>();
    public final List<Car> placeBig = new ArrayList<>();

    public ParkingCar(int lotNormal, int lotBig) {
        this.lotNormal = lotNormal;
        this.lotBig = lotBig;
    }

    @Override
    public boolean add(Car car) {
        return false;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }
}
