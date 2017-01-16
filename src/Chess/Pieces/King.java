package Chess.Pieces;

import Chess.ChessPiece;
import Chess.Move;

/**
 * The King is a piece that can move one space to any of the eight adjacent tiles, it can also capture on any of the eight.
 * The King can perform a special move with the Rook, if neither has been moved yet in the game, there are no pieces
 * directly between them, and no tiles are "threatened" by the opponent, then the King can move two spaces towards the Rook,
 * and the Rook will move to the opposite side of the King in a move called castling.  The algebraic notation is O-O for
 * a King side castle and O-O-O for a Queen side castle.
 */
public class King extends ChessPiece{

	public King(ChessPiece.PieceColor color){
		super(PieceType.King, color, validMoves(), false);
	}

	private static Move[] validMoves(){
		return new Move[]{	new Move(1, 0, false, false), new Move(0, 1, false, false),
                        	new Move(-1, 0, false, false), new Move(0, -1, false, false),
                        	new Move(1, 1, false, false), new Move(1, -1, false, false),
                        	new Move(-1, 1, false, false), new Move(-1, -1, false, false)};
	}
}
