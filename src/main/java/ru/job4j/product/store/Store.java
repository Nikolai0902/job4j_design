package ru.job4j.product.store;

import ru.job4j.product.foood.Food;

import java.util.List;

public interface Store {
    public void add(Food food);
    public List<Food> findAll();
}
