package practice3;

import java.util.HashMap;
import java.util.Map;

public class SynchronizedMap<K, V> {
    private Map<K, V> map;
    public SynchronizedMap() {
        map = new HashMap<>();
    }
    public synchronized V put(K key, V value) {
        return map.put(key, value);
    }
    public synchronized V get(K key) {
        return map.get(key);
    }
    public synchronized boolean containsKey(K key) {
        return map.containsKey(key);
    }
    public synchronized V remove(K key) {
        return map.remove(key);
    }
}