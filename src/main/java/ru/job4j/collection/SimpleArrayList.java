package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList() {
        this.container = (T[]) new Object[10];
    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = addT(value);
        }
        container[size++] = value;
    }

    public T[] addT(T value) {
        return Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = container[Objects.checkIndex(index, size)];
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = container[Objects.checkIndex(index, size)];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[size] = null;
        size--;
        return rsl;
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, size)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int i;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[i++];
            }
        };
    }

    @Override
    public String toString() {
        return "SimpleArrayList{" + "container="
                + Arrays.deepToString(container) + ", size="
                + size + ", modCount=" + modCount + '}';
    }
}
