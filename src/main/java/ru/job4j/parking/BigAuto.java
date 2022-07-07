package ru.job4j.parking;

import static ru.job4j.parking.NormalAuto.AUTO_SIZE;

public class BigAuto implements Car {

    public int autoSize;

    public BigAuto(int autoSize) {
        if (autoSize <= AUTO_SIZE) {
            throw new IllegalArgumentException();
        }
        this.autoSize = autoSize;
    }

    @Override
    public int getSize() {
        return autoSize;
    }
}
