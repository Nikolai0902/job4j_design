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
        if (count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int hashCode = key.hashCode();
        int hash = hash(hashCode);
        int index = indexFor(hash);
        boolean result = true;
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
        } else if (table[index].key.hashCode() == hashCode && table[index].key.equals(key)) {
            result = false;
        }
        return result;
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
                int hashCode = el.key.hashCode();
                int hash = hash(hashCode);
                int index = indexFor(hash);
                tableNew[index] = el;
            }
        }
        this.table = tableNew;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        return table[index] != null
                && table[index].key.hashCode() == key.hashCode()
                && table[index].key.equals(key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        int hashCode = key.hashCode();
        int hash = hash(hashCode);
        int index = indexFor(hash);
        boolean rsl = table[index] != null
                && table[index].key.hashCode() == hashCode
                && table[index].key.equals(key);
        if (rsl) {
            table[index] = null;
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
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                    for (int i = point; i < table.length; i++) {
                        if (table[i] != null) {
                            point = i;
                            result = true;
                            break;
                        }
                    }
                return result;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
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
