package Chess.Pieces;

import Chess.ChessBoard;
import Chess.Location;
import Chess.Move;
import Chess.MoveOffset;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * A Knight is a piece that can move two spaces in any direction and one space 90 degrees from that direction.  This makes
 * the movement look like an "L" and the piece can "jump" over other pieces to get to that tile.  It can also capture during this movement.
 */

public class Knight extends ChessPiece{

    public static final String LETTER = "N";
    private final ImageView blackImage = new ImageView(picPath + "black_n" + ".png");
    private final ImageView whiteImage = new ImageView(picPath + "white_n" + ".png");

    @Override
    ImageView getBlackImage() {
        return blackImage;
    }

    @Override
    ImageView getWhiteImage() {
        return whiteImage;
    }

	public Knight(ChessPiece.PieceColor color, Location location){
		super(PieceType.Knight, color, false, location);
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
        return new MoveOffset[]{	new MoveOffset(2, 1, false, false),
                new MoveOffset(1, 2, false, false),
                new MoveOffset(2, -1, false, false),
                new MoveOffset(-1, 2, false, false),
                new MoveOffset(-2, 1, false, false),
                new MoveOffset(1, -2, false, false),
                new MoveOffset(-2, -1, false, false),
                new MoveOffset(-1, -2, false, false)};
    }

    @Override
    public ArrayList<Move> validMoves(ChessBoard board) {
        return validatedMoves(board, potentialMoves(board), color);
    }
}
