package ru.job4j.solid.lsp;

/**
 * Нарушение LSP: в наследнике не соблюдены условия базового класса (инвариант).
 * Инвариант - это условие, которое постоянно на протяжении существования объекта.
 */
public class Class3 {
    private int cars;
    private int drivers;

    public Class3(int cars, int drivers) {
        this.cars = cars;
        this.drivers = drivers;
    }

    public void validate() {
        if (this.cars < 10 || this.drivers < 10) {
            throw new IllegalArgumentException("Cars or Drivers are not enough!");
        }
        System.out.println("Ready for operation!");
    }
}

class Class33 extends Class3 {

    public Class33(int cars, int drivers) {
        super(cars, drivers);
    }

    @Override
    public void validate() {
        System.out.println("Ride!");
    }
}