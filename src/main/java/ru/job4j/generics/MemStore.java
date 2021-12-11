package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
    storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        return storage.replace(id, model) != null ? true : false;
    }

    @Override
    public boolean delete(String id) {
        T rsl = findById(id);
        if (rsl != null) {
            storage.remove(id, rsl);
        }
        return rsl != null;
    }

    @Override
    public T findById(String id) {
        T rsl = null;
        for (String i : storage.keySet()) {
            if (id == i) {
                rsl = storage.get(id);
                break;
            }
        }
        return rsl;
    }
}
