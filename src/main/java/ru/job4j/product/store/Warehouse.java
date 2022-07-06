package ru.job4j.product.store;

import ru.job4j.product.foood.Food;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.product.MyValues.VALUE1;
import static ru.job4j.product.MyValues.VALUE3;

public class Warehouse implements Store {

    private final List<Food> list = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        int percent = getPercentLifeExpired(food);
        return percent < VALUE1;
    }

    @Override
    public void add(Food food) {
        if (accept(food)) {
            list.add(food);
        }
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
