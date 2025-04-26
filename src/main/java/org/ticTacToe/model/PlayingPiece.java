package org.ticTacToe.model;

import lombok.Data;
import org.ticTacToe.model.PieceType;

@Data
public class PlayingPiece {
    private PieceType pieceType;

    public PlayingPiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }
}
