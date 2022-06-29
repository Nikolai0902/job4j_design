package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void max() {
        MaxMin maxMin = new MaxMin();
        List<Integer> value = List.of(3, 8, 10);
        assertThat(maxMin.max(value, Integer::compare), is(10));
    }

    @Test
    public void min() {
        MaxMin maxMin = new MaxMin();
        List<Integer> value = List.of(3, 8, 10);
        assertThat(maxMin.min(value, Integer::compare), is(3));
    }
}