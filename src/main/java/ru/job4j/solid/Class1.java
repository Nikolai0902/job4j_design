package ru.job4j.solid;

import ru.job4j.question.User;

/**
 * Нарушение принципа SRP.
 * Имеет различный функционал.
 */
public interface Class1 {
    public User run();

    private String print() {
        return null;
    }
}
