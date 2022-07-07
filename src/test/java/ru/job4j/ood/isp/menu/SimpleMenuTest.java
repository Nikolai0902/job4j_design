package ru.job4j.ood.isp.menu;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final String SEP = System.lineSeparator();

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."
                ),
                menu.select("Сходить в магазин").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."
                ),
                menu.select("Купить продукты").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2."
                ),
                menu.select("Покормить собаку").get()
        );
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Гулять", STUB_ACTION);
        menu.add("Гулять", "Парк", STUB_ACTION);
        menu.add("Гулять", "Центр города", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Гулять", List.of("Парк", "Центр города"), STUB_ACTION, "1."
                ),
                menu.select("Гулять").get()
        );
    }

    @Test
    public void whenPrint() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Гулять", STUB_ACTION);
        menu.add("Гулять", "Парк", STUB_ACTION);
        menu.add("Гулять", "Центр города", STUB_ACTION);
        MenuPrinter printer = new SimpleMenuPrinter();
        printer.print(menu);
        StringBuilder expect = new StringBuilder()
                .append("----").append("1.Гулять").append(SEP)
                .append("--------").append("1.1.Парк").append(SEP)
                .append("--------").append("1.2.Центр города").append(SEP);
        Assert.assertEquals(expect.toString(), printer.printTest());
    }
}
