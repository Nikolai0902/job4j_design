package ru.job4j.ood.dip.store;

import ru.job4j.ood.dip.Product;

public interface OrderService {
    boolean add(Product product);
    boolean remove(int id);
    void clear();

}
