package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfOne() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        Predicate<Integer> filter = s -> s % 2 != 0 || s == 0;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(Arrays.asList(2)));
    }

    @Test
    public void whenRemoveIfTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        Predicate<Integer> filter = s -> s * 3 > 10;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        Predicate<Integer> filter = s -> s % 2 != 0;
        ListUtils.replaceIf(input, filter, 55);
        assertThat(input, is(Arrays.asList(0, 55, 2, 55, 4)));
    }

    @Test
    public void whenRemoveAllOne() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> element = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.removeAll(input, element);
        assertThat(input, is(Arrays.asList(4)));
    }

    @Test
    public void whenRemoveAllTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> element = new ArrayList<>(Arrays.asList(0));
        ListUtils.removeAll(input, element);
        assertThat(input, is(Arrays.asList(1, 2, 3, 4)));
    }
}