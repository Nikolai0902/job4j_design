package ru.job4j.parking;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingCarTest {


    @Test
    public void when1() {
        Parking parking = new ParkingCar(4, 1);
        assertTrue(parking.add(new NormalAuto()));
        assertTrue(parking.add(new NormalAuto()));
        assertTrue(parking.add(new BigAuto(2)));
        assertTrue(parking.add(new BigAuto(2)));
    }

    @Test
    public void when2and1() {
        Parking parking = new ParkingCar(2, 1);
        assertTrue(parking.add(new NormalAuto()));
        assertTrue(parking.add(new NormalAuto()));
        assertTrue(parking.add(new BigAuto(2)));
        assertFalse(parking.add(new NormalAuto()));
    }

    @Test
    public void when1bigOverSize2() {
        Parking parking = new ParkingCar(2, 1);
        assertTrue(parking.add(new BigAuto(3)));
    }

    @Test
    public void when1bigSize2() {
        Parking parking = new ParkingCar(2, 1);
        assertTrue(parking.add(new BigAuto(2)));
    }

    @Test
    public void when2big() {
        Parking parking = new ParkingCar(2, 1);
        assertTrue(parking.add(new BigAuto(2)));
        assertFalse(parking.add(new BigAuto(3)));
    }

    @Test
    public void when2bigVar2() {
        Parking parking = new ParkingCar(2, 1);
        assertTrue(parking.add(new BigAuto(3)));
        assertTrue(parking.add(new BigAuto(2)));
    }

    @Test
    public void when2norm() {
        Parking parking = new ParkingCar(1, 1);
        assertTrue(parking.add(new NormalAuto()));
        assertFalse(parking.add(new NormalAuto()));

    }
}