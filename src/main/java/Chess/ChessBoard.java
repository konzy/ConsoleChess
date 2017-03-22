package Chess;

import Chess.Pieces.*;
import java.util.ArrayList;

/**
 * Chess Board is a class for the board that is able to set up the starting positions of the pieces for a game of chess
 * as well as return coordinates that correspond to the values of the chess pieces locations including a
 * specific method for the King's location.
 */

public class ChessBoard implements Cloneable {
    private ArrayList<ChessPiece> pieces;

    /**
     * Initializes the chess board of a 8x8 2d Tile array
     */
    public ChessBoard(){
        fillBoard();
    }

    public ChessBoard(ArrayList<ChessPiece> board) {


        pieces = (ArrayList<ChessPiece>) board.clone();
    }

    public ChessPiece getPieceAtLocation(Location location) {
        for (ChessPiece piece : pieces) {
            if (piece.getLocation().equals(location)) {
                return piece;
            }
        }
        return null;
    }



    /**
     * @deprecated use the single isInsideBoard
     * Checks both locations to make sure they are
     * @param from
     * @param to
     * @return
     */
    @Deprecated
    public static boolean isInsideBoard(Location from, Location to) {
        return isInsideBoard(from) && isInsideBoard(to);
    }


    /**
     * Checks if a location is inside the dimensions of the board
     * @param location
     * @return
     */
    public static boolean isInsideBoard(Location location) {
        return location.X() >= 0 && location.X() <= 7 && location.Y() >= 0 && location.Y() <= 7;
    }

    public boolean removePiece(Location location) {
        return removePiece(getPieceAtLocation(location));
    }



    /**
     * Removes a piece form the game board given the ChessPiece
     * @param pieceToRemove
     * @return if piece was removed
     */
    public boolean removePiece(ChessPiece pieceToRemove) {
        return pieces.remove(pieceToRemove);
    }



    /**
     * Executes a move on the board given a piece and a target location
     * @param move
     */
    public void move(Move move) {
        ChessPiece piece = move.getPiece();
        Location to = move.getTo();
        removePiece(piece);//remove current piece
        removePiece(to);//remove piece at to location
        piece.setLocation(to);
        pieces.add(piece);
    }


    /**
     * Checks whether the color is is check
     * @param color
     * @return
     */
    public boolean isColorInCheck(ChessPiece.PieceColor color) {
        return getKingPiece(color).numPiecesThreateningThis(this) > 0;
    }


    /**
     * Gets all the moves that are both possible, in a logistic sense, and legal in a rules sense,
     * where it does not allow your king to be in check after the move.
     * @param color
     * @return
     */
    public ArrayList<Move> getAllValidMoves(ChessPiece.PieceColor color) {
        ArrayList<Move> moves = new ArrayList<>();

        for (ChessPiece chessPiece : getAllPiecesLocationForColor(color)) {
            moves.addAll(chessPiece.validMoves(this));
        }

        return moves;
    }

    /**
     *
     * @return A deep copy of ChessBoard
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {

        ChessBoard clone = (ChessBoard)super.clone();
        ArrayList<ChessPiece> clonedPieces = new ArrayList<>();
        for (ChessPiece piece : pieces) {
            clonedPieces.add((ChessPiece)piece.clone());
        }
        clone.pieces = clonedPieces;
        return clone;

    }

    /**
     * Returns the ArrayList of the pieces on the board.
     */
    public ArrayList<ChessPiece> getBoardArrayList(){
        return pieces;
    }

    public ChessPiece getKingPiece(ChessPiece.PieceColor color) {
        for (ChessPiece piece : pieces) {
            if(piece.color() == color && piece instanceof King) {
                return piece;
            }
        }
        return null;
    }

    /**
     * returns an array of coordinates containing the positions of the pieces of a color
     * @param color
     * @return coordinate array of pieces of that color
     */
    public ArrayList<ChessPiece> getAllPiecesLocationForColor(ChessPiece.PieceColor color){
        ArrayList<ChessPiece> piecesLocations = new ArrayList<>();
        for (ChessPiece piece : pieces) {
            if (piece.color() == color) {
                piecesLocations.add(piece);
            }
        }
        return piecesLocations;
    }

    /**
     * Populates the board with the chess pieces of a typical starting slate of a board.
     */
    private void fillBoard(){
        pieces = new ArrayList<>();
        // Pawns
        for(int i = 0; i < 8; i++){
            pieces.add(new Pawn(ChessPiece.PieceColor.Black, new Location(i, 1)));
            pieces.add(new Pawn(ChessPiece.PieceColor.White, new Location(i, 6)));
        }

        // Rooks
        pieces.add(new Rook(ChessPiece.PieceColor.Black, new Location(0, 0)));
        pieces.add(new Rook(ChessPiece.PieceColor.Black, new Location(7, 0)));
        pieces.add(new Rook(ChessPiece.PieceColor.White, new Location(0, 7)));
        pieces.add(new Rook(ChessPiece.PieceColor.White, new Location(7, 7)));

        // Knight
        pieces.add(new Knight(ChessPiece.PieceColor.Black, new Location(1, 0)));
        pieces.add(new Knight(ChessPiece.PieceColor.Black, new Location(6, 0)));
        pieces.add(new Knight(ChessPiece.PieceColor.White, new Location(1, 7)));
        pieces.add(new Knight(ChessPiece.PieceColor.White, new Location(6, 7)));

        // Bishop
        pieces.add(new Bishop(ChessPiece.PieceColor.Black, new Location(2, 0)));
        pieces.add(new Bishop(ChessPiece.PieceColor.Black, new Location(5, 0)));
        pieces.add(new Bishop(ChessPiece.PieceColor.White, new Location(5, 7)));
        pieces.add(new Bishop(ChessPiece.PieceColor.White, new Location(2, 7)));

        // Queens
        pieces.add(new Queen(ChessPiece.PieceColor.Black, new Location(3, 0)));
        pieces.add(new Queen(ChessPiece.PieceColor.White, new Location(3, 7)));

        // Kings
        pieces.add(new King(ChessPiece.PieceColor.Black, new Location(4, 0)));
        pieces.add(new King(ChessPiece.PieceColor.White, new Location(4, 7)));
    }

    public Tile[][] getBoardArray() {
        int len = 8;
        Tile[][] result = new Tile[len][len];
        for (ChessPiece piece : pieces) {
            result[piece.getLocation().X()][piece.getLocation().Y()] = new Tile(Tile.TileColor.Black, piece);
        }
        return result;
    }

    public ArrayList<Move> getPotentialMoves(ChessPiece.PieceColor color) {
        ArrayList<Move> potentialMoves = new ArrayList<>();
        for (ChessPiece piece : pieces) {
            if (piece.color().equals(color)) {
                potentialMoves.addAll(piece.potentialMoves(this));
            }
        }
        return potentialMoves;
    }

    /**
     * A human readable representation of the board
     * @return
     */
    @Override
    public String toString() {
        Tile[][] b = getBoardArray();
        String string = "";

        string += ("      [A][B][C][D][E][F][G][H] \n\n");
        for(int y = 0; y < 8; y++) { //8 represents height of board
            string += ("[" + (8 - y) + "]   ");

            for (int x = 0; x < 8; x++){ //8 represents width of board
                if (b[x][y] != null) {
                    string += (b[x][y].toString());
                } else {
                    string += ("[ ]");

                }
            }

            string += ("   [" + (8 - y) + "]\n");
        }

        string += ("\n      [A][B][C][D][E][F][G][H]\n\n");
        return string;
    }
}
