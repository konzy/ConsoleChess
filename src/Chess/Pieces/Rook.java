package Chess.Pieces;

import Chess.ChessPiece;
import Chess.Move;

/**
 * The Rook is a piece that can move in columns and rows indefinitely until it hits its own piece, captures, or hits the edge of the board.
 * The Rook can perform a special move with the King, if neither has been moved yet in the game, there are no pieces
 * directly between them, and no tiles are "threatened" by the opponent, then the King can move two spaces towards the Rook,
 * and the Rook will move to the opposite side of the King in a move called castling.  The algebraic notation is O-O for
 * a King side castle and O-O-O for a Queen side castle.
 */
public class Rook extends ChessPiece {

	public Rook(PieceColor color){
		super(PieceType.Rook, color, validMoves(), true);
	}


	private static Move[] validMoves(){
		return new Move[]{	new Move(1, 0, false, false), new Move(0, 1, false, false),
                            new Move(-1, 0, false, false), new Move(0, -1, false, false)};
	}
}
