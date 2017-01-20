package Chess.Pieces;

import Chess.Move;

/**
 * A bishop is a piece that can move diagonally indefinitely until it hits a piece of it's own color, can capture, or hits the edge of the board.
 */
public class Bishop extends ChessPiece {

	public Bishop(PieceColor color){
		super(PieceType.Bishop, color, true);
	}


    @Override
    public Move[] moves() {
        return	new Move[]{	new Move(1, 1, false, false), new Move(1, -1, false, false),
                new Move(-1, 1, false, false), new Move(-1, -1, false, false)};
    }
}
