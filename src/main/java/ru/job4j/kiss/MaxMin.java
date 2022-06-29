package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return maxValue(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return maxValue(value, comparator.reversed());
    }

    private <T> T maxValue(List<T> value, Comparator<T> c) {
        T max = value.get(0);
        for (T val : value) {
            if (c.compare(val, max) > 0) {
                max = val;
            }
        }
        return max;
    }
}
