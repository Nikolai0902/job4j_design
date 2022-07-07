package ru.job4j.parking;

import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;

public class ParkingCarTest {

    @Test
    public void when2and1() {
        Parking parking = new ParkingCar(2, 1);
        parking.add(new NormalAuto());
        parking.add(new NormalAuto());
        parking.add(new BigAuto(2));
        List<Car> expect = List.of(new NormalAuto(), new NormalAuto(), new BigAuto(2));
        assertTrue(expect.containsAll(parking.findAll()));
    }

    @Test
    public void when1bigOverSize2() {
        Parking parking = new ParkingCar(2, 1);
        parking.add(new BigAuto(3));
        List<Car> expect = List.of(new BigAuto(3));
        assertTrue(expect.containsAll(parking.findAll()));
    }

    @Test
    public void when1bigSize2() {
        Parking parking = new ParkingCar(2, 1);
        parking.add(new BigAuto(2));
        List<Car> expect = List.of(new BigAuto(2));
        assertTrue(expect.containsAll(parking.findAll()));
    }

    @Test
    public void when2big() {
        Parking parking = new ParkingCar(2, 1);
        parking.add(new BigAuto(2));
        parking.add(new BigAuto(3));
        List<Car> expect = List.of(new BigAuto(2));
        assertTrue(expect.containsAll(parking.findAll()));
    }

    @Test
    public void when2bigVar2() {
        Parking parking = new ParkingCar(2, 1);
        parking.add(new BigAuto(3));
        parking.add(new BigAuto(2));
        List<Car> expect = List.of(new BigAuto(3), new BigAuto(2));
        assertTrue(expect.containsAll(parking.findAll()));
    }

    @Test
    public void when2norm() {
        Parking parking = new ParkingCar(1, 1);
        parking.add(new NormalAuto());
        parking.add(new NormalAuto());
        List<Car> expect = List.of(new NormalAuto());
        assertTrue(expect.containsAll(parking.findAll()));
    }
}