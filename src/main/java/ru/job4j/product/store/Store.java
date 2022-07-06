package ru.job4j.product.store;

import ru.job4j.product.foood.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Store {
    public void add(Food food);
    public List<Food> findAll();
    boolean accept(Food food);

    public default int getPercentLifeExpired(Food food) {
        int percent = 0;
        LocalDate now = LocalDate.now();
        LocalDate crDate = food.getCreateDate();
        LocalDate expDate = food.getExpiryDate();
        if (now.isAfter(expDate) || now.equals(expDate)) {
            percent = 100;
        } else if (now.isAfter(crDate)
                && now.isBefore(expDate)) {
            long days = crDate.until(expDate, ChronoUnit.DAYS);
            long daysPassed = now.until(expDate, ChronoUnit.DAYS);
            percent = (int) (100 - (daysPassed * 100 / days));
        }
        return percent;
    }
}
