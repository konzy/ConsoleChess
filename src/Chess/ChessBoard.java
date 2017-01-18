package Chess;

import Chess.Pieces.*;
import java.util.ArrayList;

/**
 * Chess Board is a class for the board that is able to set up the starting positions of the pieces for a game of chess
 * as well as return tuples that correspond to the values of the chess pieces locations including a specific method for
 * the King's location.
 */

public class ChessBoard {
    private Tile[][] board;

    /**
     * Initilizes the chess board of a 8x8 2d Tile array
     */
    public ChessBoard(){
        board = new Tile[8][8];
        initializeBoard();
        fillBoard();
    }

    /**
     * Returns the array of tiles that consists of the board.
     */
    public Tile[][] getBoardArray(){
        return board;
    }

    /**
     * Designates which tiles of the board are black or white
     */
    private void initializeBoard(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                if (j % 2 + i == 0) board[i][j] = new Tile(Tile.TileColor.Black);
                else board[i][j] = new Tile(Tile.TileColor.White);
            }
        }
    }

    // initial project person TODO: Will break on boards with no Kings of 'color'. Should never happen.
    /**
     * Retutrns the location of the King of a specific color.
     * @param color
     * @return a tuple containing the coordinates of the location
     */
    public Tuple getKingLocation(ChessPiece.PieceColor color){
        Tuple location = new Tuple(-1,-1);
        for (int x = 0; x < board.length; x++){
            for (int y = 0; y < board[x].length; y++){
                if (!board[x][y].isEmpty()) {
                    ChessPiece piece = board[x][y].getPiece();
                    if (piece.color() == color && piece instanceof King){
                       location = new Tuple(y, x);
                    }
                }
            }
        }
        return location;
    }

    /**
     * returns an array of tuples containing the positions of the pieces of a color
     * @param color
     * @return location tuple
     */
    public Tuple[] getAllPiecesLocationForColor(ChessPiece.PieceColor color){
        ArrayList<Tuple> locations = new ArrayList<>();
        for (int x = 0; x < board.length; x++){
            for (int y = 0; y < board[x].length; y++){
               if(!board[x][y].isEmpty() && board[x][y].getPiece().color() == color)
                   locations.add(new Tuple(x,y));
            }
        }
        return locations.toArray(new Tuple[0]);//allocate new array automatically.
    }

    /**
     * translates a tuple to the corresponding location on the board
     * @param tuple to be translated
     * @return location of the board
     */
    public Tile getTileFromTuple(Tuple tuple){
        return board[tuple.Y()][tuple.X()];
    }

    /**
     * Populates the board with the chess pieces of a typical starting slate of a board.
     */
    private void fillBoard(){
        // Pawns
        for(int i = 0; i < 8; i++){
            board[1][i].setPiece(new Pawn(ChessPiece.PieceColor.Black));
            board[6][i].setPiece(new Pawn(ChessPiece.PieceColor.White));
        }

        // Rooks
        board[0][0].setPiece(new Rook(ChessPiece.PieceColor.Black));
        board[0][7].setPiece(new Rook(ChessPiece.PieceColor.Black));
        board[7][0].setPiece(new Rook(ChessPiece.PieceColor.White));
        board[7][7].setPiece(new Rook(ChessPiece.PieceColor.White));

        // Knight
        board[0][1].setPiece(new Knight(ChessPiece.PieceColor.Black));
        board[0][6].setPiece(new Knight(ChessPiece.PieceColor.Black));
        board[7][1].setPiece(new Knight(ChessPiece.PieceColor.White));
        board[7][6].setPiece(new Knight(ChessPiece.PieceColor.White));

        // Bishop
        board[0][2].setPiece(new Bishop(ChessPiece.PieceColor.Black));
        board[0][5].setPiece(new Bishop(ChessPiece.PieceColor.Black));
        board[7][2].setPiece(new Bishop(ChessPiece.PieceColor.White));
        board[7][5].setPiece(new Bishop(ChessPiece.PieceColor.White));

        // Queens
        board[0][3].setPiece(new Queen(ChessPiece.PieceColor.Black));
        board[7][3].setPiece(new Queen(ChessPiece.PieceColor.White));

        // Kings
        board[0][4].setPiece(new King(ChessPiece.PieceColor.Black));
        board[7][4].setPiece(new King(ChessPiece.PieceColor.White));
    }
}
