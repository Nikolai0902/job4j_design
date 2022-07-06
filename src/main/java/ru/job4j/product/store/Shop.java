package ru.job4j.product.store;

import ru.job4j.product.foood.Food;
import static ru.job4j.product.MyValues.*;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private final List<Food> list = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        int percent = getPercentLifeExpired(food);
        if (percent >= VALUE1 && percent < VALUE2 || percent >= VALUE2 && percent < VALUE3) {
            result = true;
        }
        return result;
    }

    @Override
    public void add(Food food) {
        if (accept(food)) {
            int percent = getPercentLifeExpired(food);
            if (percent >= VALUE2 && percent < VALUE3) {
                int priceOld = food.getPrice();
                int discount = food.getDiscount();
                food.setPrice(priceOld - (priceOld * discount / 100));
            }
            list.add(food);
        }
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(list);
    }
}
