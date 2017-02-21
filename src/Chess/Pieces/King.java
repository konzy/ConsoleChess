package Chess.Pieces;

import Chess.ChessBoard;
import Chess.Location;
import Chess.Move;
import Chess.MoveOffset;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * The King is a piece that can move one space to any of the eight adjacent tiles, it can also capture on any of the eight.
 * The King can perform a special move with the Rook, if neither has been moved yet in the game, there are no pieces
 * directly between them, and no tiles are "threatened" by the opponentOf, then the King can move two spaces towards the Rook,
 * and the Rook will move to the opposite side of the King in a move called castling.  The algebraic notation is O-O for
 * a King side castle and O-O-O for a Queen side castle.
 */
public class King extends ChessPiece{

    public static final String LETTER = "K";
    private final ImageView blackImage = new ImageView(picPath + "black_k" + ".png");
    private final ImageView whiteImage = new ImageView(picPath + "white_k" + ".png");

    @Override
    ImageView getBlackImage() {
        return blackImage;
    }

    @Override
    ImageView getWhiteImage() {
        return whiteImage;
    }

    public King(ChessPiece.PieceColor color, Location location){
		super(PieceType.King, color,false, location);
	}

    @Override
    public int value() {
        return 4;
    }

    @Override
    public String getLetter() {
        return LETTER;
    }

    @Override
    public MoveOffset[] moveModifiers() {
        return new MoveOffset[]{	new MoveOffset(1, 0, false, false), new MoveOffset(0, 1, false, false),
                new MoveOffset(-1, 0, false, false), new MoveOffset(0, -1, false, false),
                new MoveOffset(1, 1, false, false), new MoveOffset(1, -1, false, false),
                new MoveOffset(-1, 1, false, false), new MoveOffset(-1, -1, false, false)};
    }

    @Override
    public ArrayList<Move> validMoves(ChessBoard board) {
        return validatedMoves(board, potentialMoves(board), color);
    }
}
