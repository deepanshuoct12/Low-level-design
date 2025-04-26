package org.hashMapImplementation;
import lombok.Data;

@Data
public class Entry <K,V> {
    K key;
    V value;
    Entry next;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
