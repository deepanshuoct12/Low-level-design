package org.lrucache;


import java.util.HashMap;
import java.util.Map;

public class LRUCache <K, V> {
    private final int capacity;
    private final Map<K, Node<K,V>> cache;
    private final Node <K, V> head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
    }

    /**
     * for concurrent read
     */
    public synchronized V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node != null) {
            moveToHead(node);
            return node.value;
        }

        return null;
    }

    /**
     * for concurrent write
     */
    public synchronized void put(K key, V value) {
        Node<K, V> node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            node = new Node<>(key, value);
            cache.put(key, node);
            addNodeToHead(node);
            if (cache.size() > capacity) {
                removeTail();
            }
        }
    }

    private void removeTail() {
        Node<K, V> node = tail.prev;
        removeNode(node);
    }

    private void moveToHead(Node<K,V> node) {
        removeNode(node);
        addNodeToHead(node);
    }

    private void addNodeToHead(Node<K, V> node) {
       node.next = head.next;
       node.prev = head;
       if (head.next != null) {
           head.next.prev = node;
       }

       head.next = node;
    }

    private void removeNode(Node<K, V> node) {
        if (node == null)  {
            return;
        }

        if (node.prev != null) {
             node.prev.next = node.next;
        }

         if (node.next != null) {
             node.next.prev = node.prev;
         }
    }
}
