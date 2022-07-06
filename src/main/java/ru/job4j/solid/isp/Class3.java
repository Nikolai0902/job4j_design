package ru.job4j.solid.isp;

/**
 * Наркшен принцип ISP.
 * В PilotPassengers реализован метод который не используется.
 * PilotPassengers не может стрелять.
 */
public interface Class3 {
    public void fire();

    public void fly();
}

class PilotArmy implements Class3 {

    @Override
    public void fire() {
        /* code */
    }

    @Override
    public void fly() {
        /* code */
    }
}

class PilotPassengers implements Class3 {

    @Override
    public void fire() {
        throw new RuntimeException();
    }

    @Override
    public void fly() {
        /* code */
    }
}