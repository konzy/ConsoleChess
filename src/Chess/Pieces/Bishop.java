package Chess.Pieces;

import Chess.ChessBoard;
import Chess.Location;
import Chess.Move;
import Chess.MoveOffset;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * A bishop is a piece that can move diagonally indefinitely until it hits a piece of it's own color, can capture, or hits the edge of the board.
 */
public class Bishop extends ChessPiece {

    public static final String LETTER = "B";


	public Bishop(PieceColor color, Location location){
		super(PieceType.Bishop, color, true, location);
	}

	@Override
    public void setImage() {
        setImage(color, LETTER);
    }

    @Override
    public int value() {
        return 3;
    }

    @Override
    public String getLetter() {
        return LETTER;
    }

    @Override
    public MoveOffset[] moveModifiers() {
        return	new MoveOffset[]{	new MoveOffset(1, 1, false, false), new MoveOffset(1, -1, false, false),
                new MoveOffset(-1, 1, false, false), new MoveOffset(-1, -1, false, false)};
    }

    @Override
    public ArrayList<Move> validMoves(ChessBoard board) {
        return validatedMoves(board, potentialMoves(board), color);
    }
}
