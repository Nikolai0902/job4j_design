package ru.job4j.product;

import ru.job4j.product.foood.Food;
import ru.job4j.product.store.Store;

import java.util.List;

public class ControlQuality {

    private List<Store> actions;

    public ControlQuality(List<Store> actions) {
        this.actions = actions;
    }

    public void distribution(List<Food> foods) {
        for (Store st : actions) {
            for (Food food : foods) {
                if (st.accept(food)) {
                    st.add(food);
                }
            }
        }
    }
}
