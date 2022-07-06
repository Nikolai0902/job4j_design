package ru.job4j.product.foood;

import ru.job4j.product.foood.Food;

import java.time.LocalDate;
import java.util.Date;

public class Meat extends Food {
    public Meat(String name, LocalDate expiryDate, LocalDate createDate, int price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
