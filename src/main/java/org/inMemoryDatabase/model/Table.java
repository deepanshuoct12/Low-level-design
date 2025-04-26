package org.inMemoryDatabase.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class Table {
    private String name;
    private AtomicInteger autoIncrementId;
    Map <Integer, Row> rowMap = new HashMap <Integer, Row>();

    /**
     * field --> {value : [row ids]}
     */
    Map<String, Map<Object, Set<Integer>>> indexMap  = new HashMap<>();

    public Table(String name) {
        this.name = name;
    }

    public Row insert(String name, int age) {
        int id = autoIncrementId.incrementAndGet();
        Row row = new Row(id, name, age);
        rowMap.put(id, row);
        return row;
    }

    public void createIndex(String field) {
        indexMap.putIfAbsent(field, new HashMap<>());
        for (Row row : rowMap.values()) {
            Object value = null;
             if (field.equals("age")) {
                 value = row.getAge();
             } else if (field.equals("name")) {
                 value = row.getName();
             }

             indexMap.get(field).putIfAbsent(value, new HashSet<>());
             indexMap.get(field).get(value).add(row.getRowId());
        }
    }

    public void deleteIndex(String field) {
        indexMap.remove(field);
    }

    public void update(int id, String name, int age) {
        Row row = rowMap.get(id);
        if (row == null) {
            return;
        }

        row.setName(name);
        row.setAge(age);
        rowMap.put(id, row);
    }

    public void deleteRow(int id) {
        Row row = rowMap.remove(id);
        if (indexMap.containsKey("name")) {
            indexMap.get("name").get(row.getName()).remove(row.getRowId());
        } else if (indexMap.containsKey("age")) {
            indexMap.get("age").get(row.getAge()).remove(row.getRowId());
        }
    }

    public List<Row> getAllRows() {
        return  new ArrayList<>((Collection) rowMap.values());
    }

    public List<Row> selectQueryByindexField(String field, Object value) {
        List<Row> rows = new ArrayList<>();
        if (indexMap.containsKey(field) && indexMap.get(field).get(value) != null) {
            List<Integer> rowIds = indexMap.get(field).get(value).stream().collect(Collectors.toList());
            for (Integer rowId : rowIds) {
                rows.add(rowMap.get(rowId));
            }

            return rows;
        }

        for (Row row:rowMap.values()) {
            if (field.equals("name") && row.getName().equals(value)) {
                rows.add(row);
            } else if (field.equals("age") && value.equals(row.getAge())) {
                rows.add(row);
            }
        }

        return rows;
    }
}
