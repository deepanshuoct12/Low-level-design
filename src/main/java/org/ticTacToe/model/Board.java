package org.ticTacToe.model;

import lombok.Data;



@Data
public class Board {
    private int size;
    private PlayingPiece board[][];

    public Board(int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int r, int c, PlayingPiece playingPiece) {
        if (r<0 || r>=size || c<0 || c>=size || board[r][c] != null) {
            return false;
        }

        board[r][c] = playingPiece;
        return true;
    }

    public boolean isFreeCellsPresent() {
      for (int i=0;i<board.length;i++) {
          for(int j=0;j<board[i].length;j++) {
              if (board[i][j] == null) {
                 return true;
              }
          }
      }

      return false;
    }

    public void printBoard() {
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[i].length;j++) {
                if(board[i][j] != null) {
                    System.out.print(board[i][j].getPieceType().name() + "  ");
                } else {
                    System.out.print("   ");
                }

                System.out.print(" | ");
            }

            System.out.println();
        }
    }
}
