package Chess.Pieces;

import Chess.ChessBoard;
import Chess.Location;
import Chess.Move;
import Chess.MoveOffset;

import java.util.ArrayList;

// TODO: 2/11/2017 castling
// TODO: 2/11/2017 en passant
// TODO: 2/11/2017 pawn promotion - must be done during potentialMoves will require Move, to take a (Piece, Piece) instead of (Piece, Location)

/**
 * A Pawn is a piece that can only move one space toward the opponentOf's side of the board, except for its first move, it can move two spaces.
 * A Pawn can only capture on the diagonal tiles toward the opponentOf's side of the board, and can capture a pawn moving two spaces as if it had only moved one space, called "en passant".
 */

public class Pawn extends ChessPiece {

    public static final String LETTER = "P";

	public Pawn(PieceColor color, Location location){
		super(PieceType.Pawn, color, false, location);
	}

    @Override
    public int value() {
        return 1;
    }

    @Override
    public String getLetter() {
        return LETTER;
    }

    @Override
    public MoveOffset[] moveModifiers() {
        return new MoveOffset[]{}; //unused
    }

    @Override
    public ArrayList<Move> potentialMoves(ChessBoard board) {
        ArrayList<Move> potentialMoves = new ArrayList<>();
        Location moveTo;

        //one space move forward
        if (color == PieceColor.White) {
            moveTo = new Location(location.X(), location.Y() - 1);
        } else {
            moveTo = new Location(location.X(), location.Y() + 1);
        }
        if (board.getPieceAtCoord(moveTo) == null) {
            potentialMoves.add(new Move(this, moveTo));
        }

        //two space move forward
        if ((color == PieceColor.White && location.Y() == 6) ||
                (color == PieceColor.Black && location.Y() == 1)) {
            if (color == PieceColor.White) {
                moveTo = new Location(location.X(), location.Y() - 2);
            } else {
                moveTo = new Location(location.X(), location.Y() + 2);
            }
            if (board.getPieceAtCoord(moveTo) == null) {
                potentialMoves.add(new Move(this, moveTo));
            }
        }

        //capture to right
        if (color == PieceColor.White) {
            moveTo = new Location(location.X() + 1, location.Y() - 1);
        } else {
            moveTo = new Location(location.X() - 1, location.Y() + 1);
        }
        ChessPiece moveToPiece = board.getPieceAtCoord(moveTo);
        if (moveToPiece != null && moveToPiece.color == this.opponent()) {
            potentialMoves.add(new Move(this, moveTo));
        }
        moveToPiece = null;

        //capture to left
        if (color == PieceColor.White) {
            moveTo = new Location(location.X() - 1, location.Y() - 1);
        } else {
            moveTo = new Location(location.X() + 1, location.Y() + 1);
        }
        moveToPiece = board.getPieceAtCoord(moveTo);
        if (moveToPiece != null && moveToPiece.color == this.opponent()) {
            potentialMoves.add(new Move(this, moveTo));
        }
        return potentialMoves;
    }

    @Override
    public ArrayList<Move> validMoves(ChessBoard board) {
        return validatedMoves(board, potentialMoves(board), color);
    }
}
