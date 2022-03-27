package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        if ((float) count / capacity >= LOAD_FACTOR) {
            MapEntry<K, V>[] newMap = new MapEntry[capacity * 2];
            capacity *= 2;
            for (var el : table) {
                if (el != null) {
                    newMap[getIndex(el.key)] = el;
                }
            }
            table = newMap;
        }
    }

    private int getIndex(K key) {
        return indexFor(hash(key.hashCode()));
    }

    @Override
    public boolean put(K key, V value) {
        expand();
        int index = getIndex(key);
        boolean condition = table[index] == null;
        if (condition) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return condition;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        return table[index] != null ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        boolean condition = table[index] != null;
        if (condition) {
            table[index] = null;
            count--;
            modCount++;
        }
        return condition;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity) {
                    if (table[index] != null) {
                        break;
                    }
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
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
