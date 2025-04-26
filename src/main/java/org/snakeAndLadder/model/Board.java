package org.snakeAndLadder.model;

import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
public class Board {
    private Cell [][] cells;

    public Board(int cellSize, int snakeCount, int ladderCount) {
        cells = new Cell[cellSize][cellSize];
        initiaLizeBoard(cellSize);
        addSnakeAndLadder(snakeCount, ladderCount);
    }

    public void addSnakeAndLadder(int snakeCount, int ladderCount) {
        while (snakeCount > 0) {
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);

            if (snakeHead <= snakeTail) {
                continue;
            }

            Jump jump = new Jump();
            jump.setEnd(snakeTail);
            jump.setStart(snakeHead);
            
            Cell cell = getCell(snakeHead);
            cell.setJump(jump);
            snakeCount--;
        }

        while (ladderCount > 0) {
            int ladderHead = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int ladderTail = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);

            if (ladderHead >= ladderTail) {
                continue;
            }

            Jump jump = new Jump();
            jump.setEnd(ladderTail);
            jump.setStart(ladderHead);

            Cell cell = getCell(ladderHead);
            cell.setJump(jump);
            ladderCount--;
        }
    }

    public Cell getCell(int snakeHead) {
        int row = snakeHead / cells.length;
        int col = snakeHead % cells.length;
        return  cells[row][col];
    }

    public void initiaLizeBoard(int cellSize) {
        for (int i=0;i<cellSize;i++) {
            for(int j=0;j<cellSize;j++) {
                Cell cellObj = new Cell();
                cells[i][j] = cellObj;
            }
        }
    }
}
