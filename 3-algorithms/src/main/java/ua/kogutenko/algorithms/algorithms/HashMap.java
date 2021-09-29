package ua.kogutenko.algorithms.algorithms;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashMap<T> implements Map {
    HashMap<T> hashMap = new HashMap<>();

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        return hashMap.containsKey(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return hashMap.containsValue(0);
    }

    @Override
    public Object get(Object o) {
        return hashMap.get(o);
    }

    @Override
    public Object put(Object o, Object o2) {
        return hashMap.put(o,o2);
    }

    @Override
    public Object remove(Object o) {
        return hashMap.remove(o);
    }

    @Override
    public void putAll(Map map) {
        hashMap.putAll(map);
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public Set keySet() {
        return hashMap.keySet();
    }

    @Override
    public Collection values() {
        return hashMap.values();
    }

    @Override
    public Set<Entry> entrySet() {
        return hashMap.entrySet();
    }
}
