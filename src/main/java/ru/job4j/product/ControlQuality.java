package ru.job4j.product;

import ru.job4j.product.foood.Food;
import ru.job4j.product.store.Store;

import java.util.ArrayList;
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

    public void resort() {
        List<Food> newList = new ArrayList<>();
        actions.forEach(s -> newList.addAll(s.findAll()));
        actions.forEach(Store::clear);
        distribution(newList);
    }
}
