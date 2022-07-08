package ru.job4j.ood.dip.store;

import ru.job4j.ood.dip.Order;
import ru.job4j.ood.dip.User;

import java.util.Set;

public interface ShopStore {

    boolean saveUser(User user);
    boolean saveOrder(User user, Order order);
    Set<Order> getOrders(User user);
    Set<User> getUsers();
}
