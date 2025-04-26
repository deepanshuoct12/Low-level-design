package org.upiSystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Food {
    private String id;
    private String name;
    private Long price;

    public Food(String id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
