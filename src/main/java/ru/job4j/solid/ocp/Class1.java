package ru.job4j.solid.ocp;

/**
 * Программа Выводит грузоподьемность машин марки форд для работы на фабрике.
 * Данная программа не расширяемая, не соблюдается принцип OCP.
 * В случае добавления нового автомобиля марки Ford(например легковая "focus" с другой груз-ю),
 * нужно будет изменять уже существующий код класса Ford и метод workFactory.
 */
public class Class1 {
    public static void main(String[] args) {
        Ford cargo = new Ford();
        workFactory(cargo);
    }

    public static void workFactory(Ford ford) {
        ford.getLoadCapacity();
    }
}

class Ford {
    void getLoadCapacity() {
        System.out.println("Грузоподьемность....");
    }
}
