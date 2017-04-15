package Chess.Pieces;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Move;
import java.util.ArrayList;

/**
 * A bishop is a piece that can move diagonally indefinitely until it hits a piece of it's own color, can capture, or hits the edge of the board.
 */
public class Bishop extends ChessPiece {

    public static final String LETTER = "B";


    public Bishop(PieceColor color, Location location, boolean hasMoved){
        super(PieceType.Bishop, color, true, location, hasMoved);
    }

    public Bishop(PieceColor color, Location location){
        super(PieceType.Bishop, color, true, location, false);
    }

    @Override
    public void setImage() {
        setImage(color, LETTER);
    }

    @Override
    public double value(ChessGame game) {
        ArrayList<Move> moves = validMoves(game);
        double threatening = game.getPiecesPieceThreatenes(this).size() * 0.90;
        return 3  + moves.size() / 14.0 + threatening;
    }

    @Override
    public String getLetter() {
        return LETTER;
    }

    @Override
    public Location[] moveModifiers() {
        return	new Location[]{	new Location(1, 1), new Location(1, -1),
                new Location(-1, 1), new Location(-1, -1)};
    }

    @Override
    public ArrayList<Move> validMoves(ChessGame game) {

        return validatedMoves(game, potentialMoves(game), color);
    }
}