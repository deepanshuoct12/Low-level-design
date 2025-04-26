package org.inMemoryDatabase.model;

import lombok.Data;

@Data
public class Row {
    private int rowId;
    private String name;
    private int age;

    public Row(int rowId, String name, int age) {
        this.rowId = rowId;
        this.name = name;
        this.age = age;
    }
}
