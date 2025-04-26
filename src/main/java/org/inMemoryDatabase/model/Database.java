package org.inMemoryDatabase.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
    private String name;
    private HashMap<String, Table> tableMap = new HashMap<>();

    public Database(String name) {
        this.name = name;
    }

    public boolean createTable(String name) {
        if (tableMap.containsKey(name))  {
            return false; // table already exists
        }

        tableMap.put(name, new Table(name));
        return true;
    }

    public Table getTable(String name) {
        return tableMap.get(name);
    }

    public List<String> listTables() {
        return new ArrayList<>(tableMap.keySet());
    }
}
