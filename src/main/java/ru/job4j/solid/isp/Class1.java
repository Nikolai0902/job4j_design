package ru.job4j.solid.isp;

/**
 * Наркшен принцип ISP.
 * В Chicken реализован метод который не используется.
 */
public interface Class1 {
    public void fly();

    public void eat();

    public void sing();
}

class Chicken implements Class1 {

    @Override
    public void fly() {
        throw new RuntimeException();
    }

    @Override
    public void eat() {
        /* code */
    }

    @Override
    public void sing() {
        /* code */
    }
}

class Owl implements Class1 {

    @Override
    public void fly() {
        /* code */
    }

    @Override
    public void eat() {
        /* code */
    }

    @Override
    public void sing() {
        /* code */
    }
}