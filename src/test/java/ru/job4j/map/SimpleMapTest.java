package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.set.Set;
import ru.job4j.set.SimpleSet;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    @Test
    public void whenPut() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("Nik", 2));
        assertFalse(map.put("Nik", 3));
        assertThat(map.get("Nik"), is(2));
    }

    @Test
    public void whenPutNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Nik", 2);
        map.put("Mike", null);
        assertThat(map.get("Nik"), is(2));
        assertNull(map.get("Mike"));
    }

    @Test
    public void whenPutLf() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 2);
        map.put(2, 3);
        assertThat(map.get(2), is(3));
        map.put(3, 4);
        map.put(4, 5);
        map.put(5, 6);
        map.put(6, 7);
        map.put(7, 8);
        map.put(8, 9);
        map.put(9, 10);
        map.put(10, 11);
        map.put(11, 12);
        assertThat(map.get(11), is(12));
        assertThat(map.get(2), is(3));
    }

    @Test
    public void whenRemove() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Nik", 2);
        map.put("Bim", null);
        assertTrue(map.remove("Nik"));
    }

    @Test
    public void whenRemoveNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Nik", 2);
        map.put("Bim", 3);
        assertTrue(map.remove("Nik"));
        assertFalse(map.remove("Nik"));
        assertFalse(map.remove("Ni"));
        assertThat(map.get("Bim"), is(3));
        assertNull(map.get("Nik"));
    }

    @Test
    public void whenIterator() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Nik", 1);
        map.put("Bim", 2);
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorEx() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Nik", 1);
        map.put("Bim", 2);
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        map.remove("Nik");
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorExNowS() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Nik", 1);
        map.put("Bim", 2);
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        iterator.next();
    }
}