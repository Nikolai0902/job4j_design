package ru.job4j.product.store;

import ru.job4j.product.foood.Food;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.product.MyValues.PERCENT_FOR_WAREHOUSE;

public class Warehouse implements Store {

    private final List<Food> list = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        int percent = getPercentLifeExpired(food);
        return percent < PERCENT_FOR_WAREHOUSE;
    }

    @Override
    public void add(Food food) {
        if (accept(food)) {
            list.add(food);
        }
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(list);
    }

    public void clear() {
        list.clear();
    }
}
