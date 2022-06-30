package ru.job4j.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактная структура данных типа кеш.
 * Хранение данных в карте cache.
 * Программа считывает текстовые файлы из системы
 * и выдает текст при запросе имени файла.
 * @param <K> относительный путь к файлу в директории.
 * @param <V> содержиоме файла.
 */
public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> vSoftReference = new SoftReference<>(value);
        cache.put(key, vSoftReference);
    }

    /**
     * Метод вазвращет содержиоме файла если оно существует в кеш.
     * Если нет вызывет метод load.
     * В данном методе реализована работа с безопасной ссылкой
     * через сильную ссылку (строка 36).
     * @param key относительный путь к файлу в директории
     * @return содержиоме файла.
     */
    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }

    protected abstract V load(K key);
}
