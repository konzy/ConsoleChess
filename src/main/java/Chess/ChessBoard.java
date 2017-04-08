package Chess;

import Chess.Pieces.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Chess Board is a class for the board that is able to set up the starting positions of the pieces for a game of chess
 * as well as return coordinates that correspond to the values of the chess pieces locations including a
 * specific method for the King's location.
 */

public class ChessBoard implements Cloneable {
    private ArrayList<ChessPiece> pieces;

    /**
     * Creates the starting piece location for a typical game of chess
     */
    public ChessBoard(){
        fillBoard();
    }

    /**
     * Takes an array of ChessPieces to create any valid game
     * @param board
     */
    public ChessBoard(ArrayList<ChessPiece> board) {


        pieces = (ArrayList<ChessPiece>) board.clone();
    }

    /**
     * * Goes through the board and returns the piece at the specified location
     * @param location
     * @return
     */
    public ChessPiece getPieceAtLocation(Location location) {
        for (ChessPiece piece : pieces) {
            if (piece.getLocation().equals(location)) {
                return piece;
            }
        }
        return null;
    }

    public void promote(ChessPiece piece) {
        pieces.remove(piece);
        Queen queen = new Queen(piece.getColor(), piece.getLocation(), true);
        pieces.add(queen);
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
        return location.x >= 0 && location.x <= 7 && location.y >= 0 && location.y <= 7;
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
     *
     * @return A deep copy of ChessBoard
     * @throws CloneNotSupportedException
     */
    public Object clone() {

        ChessBoard clone = null;
        try {
            clone = (ChessBoard)super.clone();
            ArrayList<ChessPiece> clonedPieces = new ArrayList<>();
            for (ChessPiece piece : pieces) {
                clonedPieces.add((ChessPiece)piece.clone());
            }
            clone.pieces = clonedPieces;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;

    }

    /**
     * Returns the ArrayList of the pieces on the board.
     */
    public ArrayList<ChessPiece> getBoardArrayList(){
        return pieces;
    }

    public ChessPiece getKingPiece(ChessPiece.PieceColor color) {
        ArrayList<ChessPiece> clonePieces = (ArrayList<ChessPiece>) pieces.clone();
        for (ChessPiece piece : clonePieces) {
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
            result[piece.getLocation().x][piece.getLocation().y] = new Tile(Tile.TileColor.Black, piece);
        }
        return result;
    }

    /**

     * A human readable representation of the board

     * @return

     */

    @Override

    public String toString() {
        Collections.sort(pieces);
        Iterator iter = pieces.iterator();
        ChessPiece piece = null;
        if (iter.hasNext()) {
            piece = (ChessPiece) iter.next();
        }
        String string = "";

        for(int y = 0; y < 8; y++) { //8 represents height of board
            for (int x = 0; x < 8; x++){ //8 represents width of board
                if (piece != null && piece.getLocation().equals(new Location(x, y))) {
                    String letter = piece.getLetter().toUpperCase();
                    if (piece.getColor().equals(ChessPiece.PieceColor.White)) {
                        letter = letter.toLowerCase();
                    }
                    string += "[" + letter + "]";
                    if (iter.hasNext()) {
                        piece = (ChessPiece) iter.next();
                    } else {
                        piece = null;
                    }
                } else {
                    string += ("[ ]");
                }
            }
            string += "\n";
        }
        return string;
    }
}
