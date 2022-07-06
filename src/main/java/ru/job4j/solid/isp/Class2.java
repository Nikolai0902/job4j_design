package ru.job4j.solid.isp;
/**
 * Наркшен принцип ISP.
 * В BMW и Ural реализован метод который не используется.
 * BMW не может работать на заводе, Ural не может работать в такси.
 */
public interface Class2 {
    public void drive();

    public void workTaxi();

    public void workFactory();
}

class BMW implements Class2 {
    @Override
    public void drive() {
        /* code */
    }

    @Override
    public void workTaxi() {
        /* code */
    }

    @Override
    public void workFactory() {
        throw new RuntimeException();
    }
}

class Ural implements Class2 {
    @Override
    public void drive() {
        /* code */
    }

    @Override
    public void workTaxi() {
        throw new RuntimeException();
    }

    @Override
    public void workFactory() {
        /* code */
    }
}
