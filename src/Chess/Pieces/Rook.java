package Chess.Pieces;

import Chess.ChessBoard;
import Chess.Location;
import Chess.Move;
import Chess.MoveOffset;

import java.util.ArrayList;

/**
 * The Rook is a piece that can move in columns and rows indefinitely until it hits its own piece, captures, or hits the edge of the board.
 * The Rook can perform a special move with the King, if neither has been moved yet in the game, there are no pieces
 * directly between them, and no tiles are "threatened" by the opponentOf, then the King can move two spaces towards the Rook,
 * and the Rook will move to the opposite side of the King in a move called castling.  The algebraic notation is O-O for
 * a King side castle and O-O-O for a Queen side castle.
 */
public class Rook extends ChessPiece {

	public Rook(PieceColor color, Location location){
		super(PieceType.Rook, color,true, location);
	}

    @Override
    public int value() {
        return 5;
    }

    @Override
    public MoveOffset[] moveModifiers() {
        return new MoveOffset[]{	new MoveOffset(1, 0, false, false), new MoveOffset(0, 1, false, false),
                new MoveOffset(-1, 0, false, false), new MoveOffset(0, -1, false, false)};
    }

    @Override
    public ArrayList<Move> validMoves(ChessBoard board) {
        return validatedMoves(board, potentialMoves(board), color);
    }
}
