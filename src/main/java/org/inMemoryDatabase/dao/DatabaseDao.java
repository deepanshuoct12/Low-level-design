package org.inMemoryDatabase.dao;

import org.inMemoryDatabase.model.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseDao {
    private HashMap<String, Database> databaseHashMap = new HashMap<>();

   public boolean createDatabase(String name) {
       if (databaseHashMap.containsKey(name)) {
           return false;
       }

       databaseHashMap.put(name, new Database(name));
       return true;
   }

   public Database getDatabase(String name) {
       return databaseHashMap.get(name);
   }

   public List<String> listDatabases() {
       return new ArrayList<>(databaseHashMap.keySet());
   }
}
