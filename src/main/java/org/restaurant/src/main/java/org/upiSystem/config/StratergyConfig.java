package org.upiSystem.config;

public enum StratergyConfig {
   PRICE("price"),
   RATING("rating");

   String name;

    StratergyConfig(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
