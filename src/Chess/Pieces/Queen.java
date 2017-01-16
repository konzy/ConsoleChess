package Chess.Pieces;

import Chess.ChessPiece;
import Chess.Move;

/**
 * The Queen is a piece that can move in any of the 8 directions indefinitely until it hits its own piece, captures, or hits the edge of the board.
 */
public class Queen extends ChessPiece{

	public Queen(ChessPiece.PieceColor color){
		super(PieceType.Queen, color, validMoves(), true);
	}


	private static Move[] validMoves(){
		return new Move[]{	new Move(1, 0, false, false), new Move(0, 1, false, false),
                          new Move(-1, 0, false, false), new Move(0, -1, false, false),
                          new Move(1, 1, false, false), new Move(1, -1, false, false),
                          new Move(-1, 1, false, false), new Move(-1, -1, false, false)};
	}
}
