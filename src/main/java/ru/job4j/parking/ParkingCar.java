package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.parking.NormalAuto.AUTO_SIZE;

public class ParkingCar implements Parking {

    public int lotNormal;
    public int lotBig;

    public List<Car> placeBig;
    public List<Car> placeNormal;

    public List<Car> getPlaceBig() {
        return new ArrayList<>(placeBig);
    }

    public List<Car> getPlaceNormal() {
        return new ArrayList<>(placeNormal);
    }

    public ParkingCar(int lotNormal, int lotBig) {
        this.lotNormal = lotNormal;
        this.lotBig = lotBig;
        placeNormal = new ArrayList<>(lotNormal);
        placeBig = new ArrayList<>(lotBig);
    }

    @Override
    public boolean add(Car car) {
        boolean result = false;
        if (car.getSize() == AUTO_SIZE && placeNormal.size() < lotNormal) {
            placeNormal.add(car);
            result = true;
        } else if (car.getSize() > AUTO_SIZE && placeBig.size() < lotBig) {
            placeBig.add(car);
            result = true;
        } else if (car.getSize() > AUTO_SIZE && placeBig.size() == lotBig && (lotNormal - placeNormal.size()) >= car.getSize()) {
            for (int j = 1; j <= car.getSize(); j++) {
                placeNormal.add(car);
                result = true;
            }
        }
        return result;
    }

    @Override
    public List<Car> findAll() {
        List<Car> result = new ArrayList<>();
        result.addAll(getPlaceNormal());
        result.addAll(getPlaceBig());
        return result;
    }
}
