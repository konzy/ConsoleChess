package Chess.Pieces;

import Chess.ChessGame;
import Chess.Location;
import Chess.Move;
import java.util.ArrayList;

// TODO: 4/6/2017 require that king cannot be in check when castling
/**
 * The King is a piece that can move one space to any of the eight adjacent tiles, it can also capture on any of the eight.
 * The King can perform a special move with the Rook, if neither has been moved yet in the game, there are no pieces
 * directly between them, and no tiles are "threatened" by the opponentOf, then the King can move two spaces towards the Rook,
 * and the Rook will move to the opposite side of the King in a move called castling.  The algebraic notation is O-O for
 * a King side castle and O-O-O for a Queen side castle.
 */
public class King extends ChessPiece{

    public static final String LETTER = "K";

    public King(ChessPiece.PieceColor color, Location location, boolean hasMoved){
        super(PieceType.King, color,false, location, hasMoved);
    }

    public King(ChessPiece.PieceColor color, Location location){
        super(PieceType.King, color,false, location, false);
    }

    @Override
    public void setImage() {
        setImage(color, LETTER);
    }

    @Override
    public double value(ChessGame game) {
        return 100;
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

    public static boolean canCastle(ChessPiece piece, ChessGame game) {
        return canCastleKingSide(piece, game) || canCastleQueenSide(piece, game);
    }

    public static boolean canCastleKingSide(ChessPiece piece, ChessGame game) {
        return (piece.color == PieceColor.White &&
                piece instanceof King &&
                !piece.hasMoved &&
                game.getBoard().getPieceAtLocation(new Location(7, 7)) != null &&
                !game.getBoard().getPieceAtLocation(new Location(7, 7)).hasMoved() &&
                game.getBoard().getPieceAtLocation(new Location(6, 7)) == null && //knight
                game.getBoard().getPieceAtLocation(new Location(5, 7)) == null//bishop
        ) || (
                piece.color == PieceColor.Black &&
                piece instanceof King &&
                !piece.hasMoved &&
                game.getBoard().getPieceAtLocation(new Location(7, 0)) != null &&
                !game.getBoard().getPieceAtLocation(new Location(7, 0)).hasMoved() &&
                game.getBoard().getPieceAtLocation(new Location(6, 0)) == null && //knight
                game.getBoard().getPieceAtLocation(new Location(5, 0)) == null //bishop
                );
    }

    public static boolean canCastleQueenSide(ChessPiece piece, ChessGame game) {
        return (piece.color == PieceColor.White &&
                piece instanceof King &&
                !piece.hasMoved &&
                game.getBoard().getPieceAtLocation(new Location(0, 7)) != null &&
                !game.getBoard().getPieceAtLocation(new Location(0, 7)).hasMoved() &&
                game.getBoard().getPieceAtLocation(new Location(1, 7)) == null && //knight
                game.getBoard().getPieceAtLocation(new Location(2, 7)) == null && //bishop
                game.getBoard().getPieceAtLocation(new Location(3, 7)) == null //queen
        ) || (
                piece.color == PieceColor.Black &&
                piece instanceof King &&
                !piece.hasMoved &&
                game.getBoard().getPieceAtLocation(new Location(0, 0)) != null &&
                !game.getBoard().getPieceAtLocation(new Location(0, 0)).hasMoved() &&
                game.getBoard().getPieceAtLocation(new Location(1, 0)) == null && //knight
                game.getBoard().getPieceAtLocation(new Location(2, 0)) == null && //bishop
                game.getBoard().getPieceAtLocation(new Location(3, 0)) == null //queen
                );
    }

    @Override
    public ArrayList<Move> potentialMoves(ChessGame game) {
        ArrayList<Move> potentialMoves = new ArrayList<>();


        if (color == PieceColor.White && canCastleQueenSide(this, game)) {
            potentialMoves.add(new Move(this, new Location(2, 7)));
        }

        if (color == PieceColor.White && canCastleKingSide(this, game)) {
            potentialMoves.add(new Move(this, new Location(6, 7)));
        }

        if (color == PieceColor.Black && canCastleQueenSide(this, game)) {
            potentialMoves.add(new Move(this, new Location(2, 0)));
        }

        if (color == PieceColor.Black && canCastleKingSide(this, game)) {
            potentialMoves.add(new Move(this, new Location(6, 0)));
        }

        ArrayList<Move> standardMoves = super.potentialMoves(game);
        potentialMoves.addAll(standardMoves);
        return potentialMoves;
    }

    @Override
    public ArrayList<Move> validMoves(ChessGame game) {
        return validatedMoves(game, potentialMoves(game), color);
    }
}