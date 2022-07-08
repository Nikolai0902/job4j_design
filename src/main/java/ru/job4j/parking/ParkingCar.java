package ru.job4j.parking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ParkingCar implements Parking {

    public int lotNormal;
    public int lotBig;

    public List<Car> placeBig;
    public List<Car> placeNormal;

    public ParkingCar(int lotNormal, int lotBig) {
        this.lotNormal = lotNormal;
        this.lotBig = lotBig;
        placeNormal = new ArrayList<>(lotNormal);
        placeBig = new ArrayList<>(lotBig);
    }

    @Override
    public boolean add(Car car) {
        boolean result = false;
        if (car.getSize() == 1 && placeNormal.size() < lotNormal) {
            placeNormal.add(car);
            result = true;
        } else if (car.getSize() > 1 && placeBig.size() < lotBig) {
            placeBig.add(car);
            result = true;
        } else if (car.getSize() > 1 && placeBig.size() == lotBig && (lotNormal - placeNormal.size()) >= car.getSize()) {
            int i = 1;
            while (i <= car.getSize()) {
                placeNormal.add(new NormalAuto());
                i++;
                result = true;
            }
        }
        return result;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }
}
