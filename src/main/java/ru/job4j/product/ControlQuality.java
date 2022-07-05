package ru.job4j.product;

import ru.job4j.product.foood.Food;
import ru.job4j.product.store.Store;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ControlQuality {

    public void distribution(List<Store> actions, List<Food> foods, Date nowDate) {
        for (Food food : foods) {
            int percent = percentDate(food, nowDate);
            if (percent < 25) {
                actions.get(0).add(food);
            } else if (percent > 25 && percent < 75) {
                actions.get(1).add(food);
            } else if (percent >= 75 && percent < 100) {
                int priceOld = food.getPrice();
                int discount = food.getDiscount();
                food.setPrice(priceOld - (priceOld * discount / 100));
                actions.get(1).add(food);
            } else {
                actions.get(2).add(food);
            }
        }
    }

    private int percentDate(Food food, Date nowDate) {
        int percent = 0;
        Date crDate = food.getCreateDate();
        Date expDate = food.getExpiryDate();
        if (nowDate.after(expDate) || nowDate.equals(expDate)) {
            percent = 100;
        } else if (nowDate.after(crDate)
                && nowDate.before(expDate)) {
            long days = TimeUnit.DAYS.convert(Math.abs(expDate.getTime() - crDate.getTime()), TimeUnit.MILLISECONDS);
            long daysPassed = TimeUnit.DAYS.convert(Math.abs(expDate.getTime() - nowDate.getTime()), TimeUnit.MILLISECONDS);
            percent = (int) (100 - (daysPassed * 100 / days));
        }
        return percent;
    }
}
