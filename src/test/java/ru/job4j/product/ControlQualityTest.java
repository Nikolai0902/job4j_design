package ru.job4j.product;

import org.junit.Test;
import ru.job4j.product.foood.Food;
import ru.job4j.product.foood.Fruits;
import ru.job4j.product.foood.Meat;
import ru.job4j.product.foood.Milk;
import ru.job4j.product.store.Shop;
import ru.job4j.product.store.Store;
import ru.job4j.product.store.Trash;
import ru.job4j.product.store.Warehouse;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ControlQualityTest {

    @Test
    public void testWhenShop() {
        List<Store> actions = List.of(new Warehouse(), new Shop(), new Trash());
        Date nowDate = new Date(2000, Calendar.MAY, 5);
        List<Food> foods = List.of(
                new Milk("молоко",
                        new Date(2000, Calendar.MAY, 10),
                        new Date(2000, Calendar.MAY, 1), 100, 10)
        );
        new ControlQuality().distribution(actions, foods, nowDate);
        assertThat(actions.get(1).findAll().get(0).getName(), is("молоко"));
    }

    @Test
    public void testWhenShopOver75() {
        List<Store> actions = List.of(new Warehouse(), new Shop(), new Trash());
        Date nowDate = new Date(2000, Calendar.MAY, 9);
        List<Food> foods = List.of(
                new Milk("молоко",
                        new Date(2000, Calendar.MAY, 10),
                        new Date(2000, Calendar.MAY, 1), 100, 10)
        );
        new ControlQuality().distribution(actions, foods, nowDate);
        assertThat(actions.get(1).findAll().get(0).getName(), is("молоко"));
        assertThat(actions.get(1).findAll().get(0).getPrice(), is(90));
    }

    @Test
    public void testWhenWarehouse() {
        List<Store> actions = List.of(new Warehouse(), new Shop(), new Trash());
        Date nowDate = new Date(2000, Calendar.MAY, 2);
        List<Food> foods = List.of(
                new Fruits("арбуз",
                        new Date(2000, Calendar.MAY, 10),
                        new Date(2000, Calendar.MAY, 1), 100, 10)
        );
        new ControlQuality().distribution(actions, foods, nowDate);
        assertThat(actions.get(0).findAll().get(0).getName(), is("арбуз"));
    }

    @Test
    public void testWhenTrash() {
        List<Store> actions = List.of(new Warehouse(), new Shop(), new Trash());
        Date nowDate = new Date(2000, Calendar.MAY, 10);
        List<Food> foods = List.of(
                new Meat("свинина",
                        new Date(2000, Calendar.MAY, 10),
                        new Date(2000, Calendar.MAY, 1), 100, 10)
        );
        new ControlQuality().distribution(actions, foods, nowDate);
        assertThat(actions.get(2).findAll().get(0).getName(), is("свинина"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testWhenEx() {
        List<Store> actions = List.of(new Warehouse(), new Shop(), new Trash());
        Date nowDate = new Date(2000, Calendar.MAY, 10);
        List<Food> foods = List.of(
                new Meat("свинина",
                        new Date(2000, Calendar.MAY, 1),
                        new Date(2000, Calendar.MAY, 10), 100, 10)
        );
        new ControlQuality().distribution(actions, foods, nowDate);
    }
}