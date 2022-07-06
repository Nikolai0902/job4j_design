package ru.job4j.product;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.product.foood.Food;
import ru.job4j.product.foood.Fruits;
import ru.job4j.product.foood.Meat;
import ru.job4j.product.foood.Milk;
import ru.job4j.product.store.Shop;
import ru.job4j.product.store.Store;
import ru.job4j.product.store.Trash;
import ru.job4j.product.store.Warehouse;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ControlQualityTest {

    @Test
    public void testWhenMilkShop() {
        List<Store> actions = List.of(new Warehouse(), new Shop(), new Trash());
        List<Food> foods = List.of(
                new Milk("молоко", LocalDate.now().plusDays(5), LocalDate.now().minusDays(5), 100, 10)
        );
        List<Food> expect = List.of(
                new Milk("молоко", LocalDate.now().plusDays(5), LocalDate.now().minusDays(5), 100, 10)
        );
        new ControlQuality(actions).distribution(foods);
        assertThat(actions.get(1).findAll(), is(expect));
    }

    @Test
    public void testWhenShopOver75() {
        List<Store> actions = List.of(new Warehouse(), new Shop(), new Trash());
        Date nowDate = new Date(2000, Calendar.MAY, 9);
        List<Food> foods = List.of(
                new Milk("молоко",
                        LocalDate.now().plusDays(1), LocalDate.now().minusDays(9), 100, 10)
        );
        new ControlQuality(actions).distribution(foods);
        List<Food> expect = List.of(
                new Milk("молоко", LocalDate.now().plusDays(1), LocalDate.now().minusDays(9), 90, 10)
        );
        assertThat(actions.get(1).findAll(), is(expect));
    }

    @Test
    public void testWhenShopFrutis75() {
        List<Store> actions = List.of(new Warehouse(), new Shop(), new Trash());
        Date nowDate = new Date(2000, Calendar.MAY, 9);
        List<Food> foods = List.of(
                new Fruits("арбуз",
                        LocalDate.now().plusDays(4), LocalDate.now().minusDays(12), 100, 10)
        );
        new ControlQuality(actions).distribution(foods);
        List<Food> expect = List.of(
                new Fruits("арбуз", LocalDate.now().plusDays(4), LocalDate.now().minusDays(12), 90, 10)
        );
        assertThat(actions.get(1).findAll(), is(expect));
    }

    @Test
    public void testWhenWarehouse() {
        List<Store> actions = List.of(new Warehouse(), new Shop(), new Trash());
        Date nowDate = new Date(2000, Calendar.MAY, 9);
        List<Food> foods = List.of(
                new Fruits("банан",
                        LocalDate.now().plusDays(10), LocalDate.now().minusDays(1), 100, 10)
        );
        new ControlQuality(actions).distribution(foods);
        List<Food> expect = List.of(
                new Fruits("банан", LocalDate.now().plusDays(10), LocalDate.now().minusDays(1), 100, 10)
        );
        assertThat(actions.get(0).findAll(), is(expect));
    }

    @Test
    public void testWhenTrash() {
        List<Store> actions = List.of(new Warehouse(), new Shop(), new Trash());
        List<Food> foods = List.of(
                new Fruits("банан",
                        LocalDate.now().plusDays(0), LocalDate.now().minusDays(10), 100, 10)
        );
        new ControlQuality(actions).distribution(foods);
        List<Food> expect = List.of(
                new Fruits("банан", LocalDate.now().plusDays(0), LocalDate.now().minusDays(10), 100, 10)
        );
        assertThat(actions.get(2).findAll(), is(expect));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testWhenEx() {
        List<Store> actions = List.of(new Warehouse(), new Shop(), new Trash());
        List<Food> foods = List.of(
                new Fruits("банан",
                        LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 100, 10)
        );
        new ControlQuality(actions).distribution(foods);
    }
}