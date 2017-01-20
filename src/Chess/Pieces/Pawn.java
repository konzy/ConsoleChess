package Chess.Pieces;

import Chess.Move;

/**
 * A Pawn is a piece that can only move one space toward the opponent's side of the board, except for its first move, it can move two spaces.
 * A Pawn can only capture on the diagonal tiles toward the opponent's side of the board, and can capture a pawn moving two spaces as if it had only moved one space, called "en passant".
 */
public class Pawn extends ChessPiece {

	public Pawn(PieceColor color){
		super(PieceType.Pawn, color, false);
	}

    @Override
    public Move[] moves() {
        return new Move[]{new Move(0, 1, false, false), new Move(0, 2, true, false),
                new Move(1, 1, false, true), new Move(-1, 1, false, true)};
    }
}
