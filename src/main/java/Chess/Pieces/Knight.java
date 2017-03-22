package Chess.Pieces;

import Chess.ChessBoard;
import Chess.Location;
import Chess.Move;
import java.util.ArrayList;

/**
 * A Knight is a piece that can move two spaces in any direction and one space 90 degrees from that direction.  This makes
 * the movement look like an "L" and the piece can "jump" over other pieces to get to that tile.  It can also capture during this movement.
 */

public class Knight extends ChessPiece{

    public static final String LETTER = "N";

    public Knight(ChessPiece.PieceColor color, Location location){
        super(PieceType.Knight, color, false, location);
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
    public Location[] moveModifiers() {
        return new Location[]{	new Location(2, 1),
                new Location(1, 2),
                new Location(2, -1),
                new Location(-1, 2),
                new Location(-2, 1),
                new Location(1, -2),
                new Location(-2, -1),
                new Location(-1, -2)};
    }

    @Override
    public ArrayList<Move> validMoves(ChessBoard board) {
        return validatedMoves(board, potentialMoves(board), color);
    }
}