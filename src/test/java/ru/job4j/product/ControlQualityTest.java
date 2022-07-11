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
    public void whenMilkGoShop() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> actions = List.of(warehouse, shop, trash);
        Milk milk = new Milk("молоко", LocalDate.now().plusDays(5), LocalDate.now().minusDays(5), 100, 10);
        List<Food> foods = List.of(milk);
        new ControlQuality(actions).distribution(foods);
        assertThat(shop.findAll(), is(List.of(milk)));
    }

    @Test
    public void whenMilkGoShopDateOver75() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> actions = List.of(warehouse, shop, trash);
        Milk milk = new Milk("молоко", LocalDate.now().plusDays(1), LocalDate.now().minusDays(9), 90, 10);
        List<Food> foods = List.of(milk);
        new ControlQuality(actions).distribution(foods);
        assertThat(shop.findAll(), is(List.of(milk)));
    }

    @Test
    public void whenFruitsGoShopDateEqual75() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> actions = List.of(warehouse, shop, trash);
        Fruits fruits = new Fruits("арбуз", LocalDate.now().plusDays(4), LocalDate.now().minusDays(12), 90, 10);
        List<Food> foods = List.of(fruits);
        new ControlQuality(actions).distribution(foods);
        assertThat(shop.findAll(), is(List.of(fruits)));
    }

    @Test
    public void whenFruitsGoWarehouse() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> actions = List.of(warehouse, shop, trash);
        Fruits fruits = new Fruits("банан", LocalDate.now().plusDays(10), LocalDate.now().minusDays(1), 100, 10);
        List<Food> foods = List.of(fruits);
        new ControlQuality(actions).distribution(foods);
        assertThat(warehouse.findAll(), is(List.of(fruits)));
    }

    @Test
    public void whenFruitsGoTrash() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> actions = List.of(warehouse, shop, trash);
        Fruits fruits = new Fruits("банан", LocalDate.now().plusDays(0), LocalDate.now().minusDays(10), 100, 10);
        List<Food> foods = List.of(fruits);
        new ControlQuality(actions).distribution(foods);
        assertThat(trash.findAll(), is(List.of(fruits)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenException() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> actions = List.of(warehouse, shop, trash);
        Fruits fruits = new Fruits("банан",
                LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 100, 10);
        new ControlQuality(actions).distribution(List.of(fruits));
    }

    @Test
    public void whenFoodGoTrashWarehouseShop() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> actions = List.of(warehouse, shop, trash);
        Milk milk = new Milk("молоко", LocalDate.now().plusDays(5), LocalDate.now().minusDays(5), 100, 10);
        Milk cheese = new Milk("сыр", LocalDate.now().plusDays(1), LocalDate.now().minusDays(9), 90, 10);
        Fruits fruits = new Fruits("банан", LocalDate.now().plusDays(0), LocalDate.now().minusDays(10), 100, 10);
        Fruits apple = new Fruits("яблоко", LocalDate.now().plusDays(10), LocalDate.now().minusDays(1), 100, 10);
        List<Food> foods = List.of(fruits, milk, cheese, apple);
        new ControlQuality(actions).distribution(foods);
        assertThat(shop.findAll(), is(List.of(milk, cheese)));
        assertThat(trash.findAll(), is(List.of(fruits)));
        assertThat(warehouse.findAll(), is(List.of(apple)));
    }

    @Test
    public void whenResortFood() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> actions = List.of(warehouse, shop, trash);
        Milk milk = new Milk("молоко", LocalDate.now().plusDays(5), LocalDate.now().minusDays(5), 100, 10);
        List<Food> foods = List.of(milk);
        ControlQuality controlQuality = new ControlQuality(actions);
        controlQuality.distribution(foods);
        controlQuality.resort();
        assertThat(shop.findAll(), is(List.of(milk)));
    }
}