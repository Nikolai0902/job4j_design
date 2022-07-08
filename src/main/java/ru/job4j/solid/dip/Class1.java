package ru.job4j.solid.dip;

/**
 * Наркшен принцип DIP.
 * Модули верхнего уровня зависят от модулей нижнего уровня.
 * И те и другие не зависят от абстракций.
 */
public class Class1 {
    Client client = new Client();
    void watch() {
        client.job();
    }
}

class Client {
    Game game = new Game();
    void job() {
        game.result();
    }
}

class Game {
    void result() {
        System.out.println("Play game");
    }
}