package org.snakeAndLadder.model;

import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
public class Dice {
    private int diceCount;
    private int min = 1;
    private int max = 6;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    public int rollDice() {
        int sum = 0;
        int totalCount = 0;

        while (totalCount < diceCount) {
            sum = sum + ThreadLocalRandom.current().nextInt(min, max+1);
            totalCount++;
        }

        return sum;
    }
}
