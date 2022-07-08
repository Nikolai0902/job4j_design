package ru.job4j.ood.dip.store;

import ru.job4j.ood.dip.Product;

import java.util.Set;

public interface OrderStore {

    boolean saveInt(Integer integer);
    boolean saveProduct(Integer integer, Product product);
    Set<Product> getProduct(Integer integer);
    Set<Integer> getInt();
}
