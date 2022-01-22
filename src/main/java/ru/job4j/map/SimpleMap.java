package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements MapAbs<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int h = key.hashCode();
        int hash = hash(h);
        int i = indexFor(hash);
        if ((float) count >= LOAD_FACTOR * (float) capacity) {
            expand();
        }
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            modCount++;
            count++;
        } else if (table[i].key.hashCode() == h && table[i].key.equals(key)) {
            table[i].value = value;
            modCount++;
        }
        return table[i].key.hashCode() == h && table[i].key.equals(key);
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] tableOld = table;
        capacity = capacity * 2;
        MapEntry<K, V>[] tableNew = new MapEntry[capacity];
        for (MapEntry<K, V> el : tableOld) {
            if (el != null) {
                int h = el.key.hashCode();
                int hash = hash(h);
                int index = indexFor(hash);
                tableNew[index] = el;
            }
        }
        this.table = tableNew;
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(key.hashCode()));
        return table[i] == null ? null : table[i].value;
    }

    @Override
    public boolean remove(K key) {
        int h = key.hashCode();
        int hash = hash(h);
        int i = indexFor(hash);
        boolean rsl = table[i] != null && table[i].key.hashCode() == h && table[i].key.equals(key);
        if (rsl) {
            table[i] = null;
            modCount++;
            count--;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            int point = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                for (int i = point; i < table.length; i++) {
                    if (table[i] != null) {
                        point = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
