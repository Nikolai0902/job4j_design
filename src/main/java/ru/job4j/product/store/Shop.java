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
        if (percent >= PERCENT_FOR_WAREHOUSE && percent < PERCENT_FOR_SHOP
                || percent >= PERCENT_FOR_SHOP && percent < PERCENT_FOR_TRASH) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean add(Food food) {
        boolean result = accept(food);
        if (result) {
            int percent = getPercentLifeExpired(food);
            if (percent >= PERCENT_FOR_SHOP && percent < PERCENT_FOR_TRASH) {
                int priceOld = food.getPrice();
                int discount = food.getDiscount();
                food.setPrice(priceOld - (priceOld * discount / 100));
            }
            list.add(food);
        }
        return result;
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(list);
    }

    public void clear() {
        list.clear();
    }
}
