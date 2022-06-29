package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> vSoftReference = new SoftReference<>(value);
        cache.put(key, vSoftReference);
    }

    public V get(K key) {
        SoftReference<V> vSoftReference = cache.get(key);
        return vSoftReference.get();
    }

    protected abstract V load(K key);
}
