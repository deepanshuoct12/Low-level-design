package org.ticTacToe;


import org.ticTacToe.model.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class TicTacToeGame {
    private Deque<Player> playerList;
    private Board board;

    public void initializeGame() {
        Player player1 = new Player("player1", new PlayingPieceO(PieceType.O));
        Player player2 = new Player("player2", new PlayingPieceX(PieceType.X));
        board = new Board(3);

        playerList = new ArrayDeque<>();
        playerList.add(player1);
        playerList.add(player2);
    }

   public String startGame() {
        boolean noWinner = true;

        while(noWinner) {
            Player player = playerList.getFirst();
            playerList.removeFirst();

            board.printBoard();

            if (!board.isFreeCellsPresent()) {
                noWinner = false;
                continue;
            }

            System.out.println("Player : " + player.getName() + " Enter row,col : ");
            Scanner scanner = new Scanner(System.in);
            String rowCol = scanner.nextLine();
            String val[] = rowCol.split(",");

            int r = Integer.parseInt(val[0]);
            int c = Integer.parseInt(val[1]);

            boolean pieceAddedSuccessFully = board.addPiece(r, c, player.getPlayingPiece());

            if (!pieceAddedSuccessFully) {
                System.out.println("Invalid move try again");
                playerList.addFirst(player);
                continue;
            }

            playerList.add(player);

            if (checkWinner(r, c, player)) {
                board.printBoard();
                return player.getName();
            }
        }

        return "tie";
   }

    private boolean checkWinner(int r, int c, Player player) {
        boolean row = true;
        boolean col = true;
        boolean diag = true;
        boolean antiDiag = true;

        for (int j=0;j<board.getSize();j++) {
             if (board.getBoard()[r][j] == null || (board.getBoard()[r][j].getPieceType() != player.getPlayingPiece().getPieceType())) {
                row = false;
             }
        }

        for (int i=0;i<board.getSize();i++) {
            if (board.getBoard()[i][c] == null || (board.getBoard()[i][c].getPieceType() != player.getPlayingPiece().getPieceType())) {
                col = false;
            }
        }

        for (int i=0, j=0;i<board.getSize();i++, j++) {
            if (board.getBoard()[i][j] == null || (board.getBoard()[i][j].getPieceType() != player.getPlayingPiece().getPieceType())) {
                diag = false;
            }
        }

        for (int i=0, j=board.getSize()-1;i<board.getSize() && j>=0; i++,j--) {
            if (board.getBoard()[i][j] == null || (board.getBoard()[i][j].getPieceType() != player.getPlayingPiece().getPieceType())) {
                antiDiag = false;
            }
        }

        return row || col || diag || antiDiag;
    }
}
