package Chess.Pieces;

import Chess.ChessBoard;
import Chess.Location;
import Chess.Move;

import java.util.ArrayList;

/**
 * The Queen is a piece that can move in any of the 8 directions indefinitely until it hits its own piece, captures, or hits the edge of the board.
 */
public class Queen extends ChessPiece{

	public static final String LETTER = "Q";

	public Queen(ChessPiece.PieceColor color, Location location){
		super(PieceType.Queen, color,true, location);
	}

	@Override
	public void setImage() {
		setImage(color, LETTER);
	}

	@Override
	public int value() {
		return 9;
	}

	@Override
	public String getLetter() {
		return LETTER;
	}

	@Override
    public Location[] moveModifiers() {
	    return new Location[]{	new Location(1, 0), new Location(0, 1),
            new Location(-1, 0), new Location(0, -1),
            new Location(1, 1), new Location(1, -1),
            new Location(-1, 1), new Location(-1, -1)};
    }

	@Override
	public ArrayList<Move> validMoves(ChessBoard board) {
		return validatedMoves(board, potentialMoves(board), color);
	}
}
