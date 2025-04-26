package org.hashMapImplementation;

public class MyHashMap <K,V> {
    private static final int INITIAL_CAPACITY = 1<<4; //16
    private static final int MAXIMUM_CAPACITY = 1<<30;

    Entry[] hashTable;

    public MyHashMap() {
        hashTable = new Entry[INITIAL_CAPACITY];
    }

    public MyHashMap(int capacity) {
       int tableSize = tableSizeFor(capacity);
       hashTable = new Entry[tableSize];
    }

    /**
     * let say it has capacity as 15 then, then this function round it off to 16, i.e next number in 2 raise to power something
     */
    int tableSizeFor(int capacity) {
        int n = capacity - 1;
        n = n | n>>>1;
        n = n | n>>>4;
        n = n | n>>>8;
        n = n | n>>>16;
        return n < 0 ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public void put(K key, V value) {
         int hashCode = key.hashCode() % hashTable.length;
         Entry node = hashTable[hashCode];

         if (node == null) {
             Entry entry = new Entry(key, value);
             hashTable[hashCode] = entry;
         } else {
             Entry previousNode = node;
             while(node != null) {
                 if (node.key.equals(key)) {
                     node.value = value;
                     return;
                 }
                 node = node.next;
             }

             Entry newNode = new Entry(key,value);
             previousNode.next = newNode;
         }
    }

    public V get(K key) {
        int hashCode = key.hashCode() % hashTable.length;
        Entry node = hashTable[hashCode];
        while (node != null) {
            if (node.key == key) {
                return (V)node.value;
            }
        }

        return null;
    }
}
