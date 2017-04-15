package Chess.Pieces;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Move;
import java.util.ArrayList;

/**
 * A Pawn is a piece that can only move one space toward the opponent's side of the board, except for its first move,
 * it can move two spaces.
 * A Pawn can only capture on the diagonal tiles toward the opponent's side of the board, and can capture a pawn moving
 * two spaces as if it had only moved one space, called "en passant".
 */
public class Pawn extends ChessPiece {

    public static final String LETTER = "P";

    public Pawn(PieceColor color, Location location, boolean hasMoved){
        super(PieceType.Pawn, color, false, location, hasMoved);
    }

    public Pawn(PieceColor color, Location location){
        super(PieceType.Pawn, color, false, location, false);
    }

    public boolean readyToPromote() {
        return color == PieceColor.Black && location.y == 7 ||
                color == PieceColor.White && location.y == 0;
    }

    @Override
    public void setImage() {
        setImage(color, LETTER);
    }

    @Override
    public double value(ChessGame game) {
        double addedValue = location.y;
        if (color == PieceColor.White) {
            addedValue = -addedValue;
        }
        addedValue *= 0.98;

        double connectedPawns = 0;
        ChessPiece p1 = game.getBoard().getPieceAtLocation(new Location(location.x + 1, location.y + 1));
        ChessPiece p2 = game.getBoard().getPieceAtLocation(new Location(location.x - 1, location.y + 1));
        ChessPiece p3 = game.getBoard().getPieceAtLocation(new Location(location.x + 1, location.y - 1));
        ChessPiece p4 = game.getBoard().getPieceAtLocation(new Location(location.x - 1, location.y - 1));
        if (p1 != null && p1.getColor() == color) {
            connectedPawns++;
        }
        if (p2 != null && p2.getColor() == color) {
            connectedPawns++;
        }
        if (p3 != null && p3.getColor() == color) {
            connectedPawns++;
        }
        if (p4 != null && p4.getColor() == color) {
            connectedPawns++;
        }

        connectedPawns *= 0.98;

        double threatening = game.getPiecesPieceThreatenes(this).size() * 0.90;

        return 1 + addedValue + connectedPawns + threatening;
    }

    @Override
    public String getLetter() {
        return LETTER;
    }

    @Override
    public Location[] moveModifiers() {
        return null; //unused
    }

    @Override
    public ArrayList<Move> potentialMoves(ChessGame game) {
        ChessBoard board = game.getBoard();
        Move previousMove = game.getPreviousMove();
        ArrayList<Move> potentialMoves = new ArrayList<>();
        Location moveTo;

        //one space move forward
        if (color == PieceColor.White) {
            moveTo = new Location(location.x, location.y - 1);
        } else {
            moveTo = new Location(location.x, location.y + 1);
        }
        ChessPiece moveToPiece = board.getPieceAtLocation(moveTo);
        if (moveToPiece == null) {
            potentialMoves.add(new Move(this, moveTo));
        }

        //two space move forward
        if ((color == PieceColor.White && location.y == 6) ||
                (color == PieceColor.Black && location.y == 1)) {
            if (color == PieceColor.White) {
                moveTo = new Location(location.x, location.y - 2);
            } else {
                moveTo = new Location(location.x, location.y + 2);
            }
            if (board.getPieceAtLocation(moveTo) == null && potentialMoves.size() == 1) {
                potentialMoves.add(new Move(this, moveTo));
            }
        }

        //capture to right
        if (color == PieceColor.White) {
            moveTo = new Location(location.x + 1, location.y - 1);

        } else {
            moveTo = new Location(location.x - 1, location.y + 1);
        }
        moveToPiece = board.getPieceAtLocation(moveTo);
        if (moveToPiece != null && moveToPiece.color == this.opponent()) {
            potentialMoves.add(new Move(this, moveTo));
        }

        //capture to left
        if (color == PieceColor.White) {
            moveTo = new Location(location.x - 1, location.y - 1);
        } else {
            moveTo = new Location(location.x + 1, location.y + 1);
        }
        moveToPiece = board.getPieceAtLocation(moveTo);
        if (moveToPiece != null && moveToPiece.color == this.opponent()) {
            potentialMoves.add(new Move(this, moveTo));
        }

        //white en passant
        if (color == PieceColor.White) {
            if (previousMove != null &&
                    previousMove.getPiece() instanceof Pawn &&
                    !previousMove.getPiece().hasMoved() &&
                    Math.abs(previousMove.getPiece().getLocation().y - previousMove.getTo().y) == 2 &&
                    getLocation().y == 3) {
                potentialMoves.add(new Move(this, new Location(previousMove.getTo().x, location.y - 1)));
            }
        }

        //black en passant
        if (color == PieceColor.Black) {
            if (previousMove != null &&
                    previousMove.getPiece() instanceof Pawn &&
                    !previousMove.getPiece().hasMoved() &&
                    Math.abs(previousMove.getPiece().getLocation().y - previousMove.getTo().y) == 2 &&
                    getLocation().y == 4) {
                potentialMoves.add(new Move(this, new Location(previousMove.getTo().x, location.y + 1)));
            }
        }

        return potentialMoves;
    }

    @Override
    public ArrayList<Move> validMoves(ChessGame game) {
        return validatedMoves(game, potentialMoves(game), color);
    }
}