package org.snakeAndLadder;

import org.snakeAndLadder.model.Board;
import org.snakeAndLadder.model.Cell;
import org.snakeAndLadder.model.Dice;
import org.snakeAndLadder.model.Player;

import java.util.ArrayDeque;
import java.util.Deque;

public class SnakeAndLadderGame {
    private Board board;
    private Deque<Player> players;
    private Dice dice;
    private Player winner;

    public void initGame(int cellSize, int snakeCount, int ladderCount) {
        board = new Board(cellSize, snakeCount, ladderCount);

        Player player1 = new Player("Abhi", 0);
        Player player2 = new Player("Ravi", 0);
        players = new ArrayDeque<>();
        players.add(player1);
        players.add(player2);

        dice = new Dice(1);
        winner = null;
    }

    public void startGame() {
        while (winner == null) {
            Player currentPlayer = players.getFirst();
            players.removeFirst();
            players.add(currentPlayer);

            System.out.println("Player turn is : " + currentPlayer.getName() + " , current position is : " + currentPlayer.getCurrentPosition());
            int newPosition = currentPlayer.getCurrentPosition() + dice.rollDice();
            newPosition   = jumpCheck(newPosition);

            System.out.println("Player turn is : " + currentPlayer.getName() + " , new position is : " + currentPlayer.getCurrentPosition());
            currentPlayer.setCurrentPosition(newPosition);
            if (newPosition >= board.getCells().length *  board.getCells().length - 1) { // no change
                winner = currentPlayer;
            }
       }

        System.out.println("winner is : " + winner.getName());
    }

    private int jumpCheck(int newPosition) {
        if (newPosition >= board.getCells().length *  board.getCells().length - 1) { // no change
            return newPosition;
        }

        Cell cellObj = board.getCell(newPosition);
        if (cellObj.getJump() != null && cellObj.getJump().getStart() == newPosition) {
            String jumpDoneBy = (cellObj.getJump().getStart() >=  cellObj.getJump().getEnd()) ? "SNAKE" : "LADDER";
            System.out.println("Jump done by : " + jumpDoneBy);
            newPosition = cellObj.getJump().getEnd();
        }

        return newPosition;
    }
}
