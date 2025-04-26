package org.snakeAndLadder.model;

import lombok.Data;

@Data
public class Player {
    private String name;
    private int currentPosition;

    public Player(String name, int currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
    }
}
