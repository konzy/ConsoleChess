package Chess.Pieces;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Move;
import java.util.ArrayList;

/**
 * A Knight is a piece that can move two spaces in any direction and one space 90 degrees from that direction.  This makes
 * the movement look like an "L" and the piece can "jump" over other pieces to get to that tile.  It can also capture during this movement.
 */

public class Knight extends ChessPiece{

    public static final String LETTER = "N";

    public Knight(ChessPiece.PieceColor color, Location location, boolean hasMoved){
        super(PieceType.Knight, color, false, location, hasMoved);
    }

    public Knight(ChessPiece.PieceColor color, Location location){
        super(PieceType.Knight, color, false, location, false);
    }

    @Override
    public void setImage() {
        setImage(color, LETTER);
    }

    @Override
    public double value(ChessGame game) {
        double threatening = game.getPiecesPieceThreatenes(this).size() * 0.50;
        return 3 + favorCenter() + threatening;
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
    public ArrayList<Move> validMoves(ChessGame game) {
        return validatedMoves(game, potentialMoves(game), color);
    }
}