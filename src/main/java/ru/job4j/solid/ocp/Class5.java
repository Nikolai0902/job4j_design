package ru.job4j.solid.ocp;

import java.util.ArrayList;

/**
 * Данный метод нарушает принцип OCP(возвращаемые типы методы), так как метод возвращает конкретную реализацию ArrayList интерфейса List
 * , в дальнейшем мы не сможешь расширять наш код и гибко использовать данный метод
 * , метод должен возвращать List<>, так как взаимодействие должно быть через абстракции
 */
public class Class5 {
    private ArrayList<String> getList() {
        ArrayList<String> res = new ArrayList<String>();
        res.add("String");
        return res;
    }
}
