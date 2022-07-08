package ru.job4j.ood.dip;

import ru.job4j.ood.dip.store.OrderService;
import ru.job4j.ood.dip.store.OrderStore;

public class SimpleOrderService implements OrderService {

    private OrderStore orderStore;

    public SimpleOrderService(OrderStore orderStore) {
        this.orderStore = orderStore;
    }

    public boolean add(Product product) {
        if (orderStore.getInt().contains(product.getId())) {
            return false;
        }
        return orderStore.saveProduct(product.getId(), product);
    }

    public boolean remove(int id) {
        return orderStore.getInt().remove(id);
    }

    public void clear() {
        orderStore.getInt().clear();
    }


}
