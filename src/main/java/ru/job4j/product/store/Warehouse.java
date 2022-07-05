package ru.job4j.product.store;

import ru.job4j.product.foood.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private final List<Food> list = new ArrayList<>();

    @Override
    public void add(Food food) {
        list.add(food);
    }

    @Override
    public List<Food> findAll() {
        List<Food> rsl = new ArrayList<>();
        for (Food row : list) {
            rsl.add(row);

        }
        return rsl;
    }
}
