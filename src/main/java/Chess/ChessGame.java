package Chess;

import Chess.Pieces.ChessPiece;
import Chess.Pieces.ChessPiece.PieceColor;
import java.util.ArrayList;

/**
 * Methods for general play of chess.
 */
public class ChessGame implements Cloneable {

    public ChessBoard board;
    private PieceColor currentPlayer;


    public enum GameState {
        PLAY,
        CHECKMATE,
        STALEMATE
    }

    /**
     * Starts up the game with initial conditions and displays the board.
     */
    public ChessGame(){
        board = new ChessBoard();
        currentPlayer = PieceColor.White;
    }

    public ChessGame(ChessBoard board) {
        if (board == null) {
            this.board = new ChessBoard(new ArrayList<>());
        } else {
            this.board = board;
        }
        currentPlayer = PieceColor.White;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ChessGame clone = (ChessGame)super.clone();
        clone.board = (ChessBoard)board.clone();
        return clone;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public PieceColor getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PieceColor color) {
        currentPlayer = color;
    }

    /**
     * Takes the input of a piece to be moved from and to a position and moves the piece if it is a valid move.
     * @param from current position of the game piece
     * @param to future position of the game piece
     */
    public boolean playMove(Location from, Location to){
        ChessPiece piece = board.getPieceAtLocation(from);
        Move move = new Move(piece, to);
        if (from != null && piece != null && to != null && board.getAllValidMoves(currentPlayer).contains(move)) {
            board.move(move);
            System.out.println(board);
            System.out.println(currentPlayer.toString() + " Moved " + move.getPiece().charValue() + " from " + from.toString() + " to " + to.toString());
            endTurn();
            return true;
        } else {
            System.out.println("Invalid move!");
            return false;
        }
    }

    /**
     * Ends turn and switches player's colors for next turn's logic.
     */
    private void endTurn(){
        if (currentPlayer == PieceColor.White) currentPlayer = PieceColor.Black;
        else currentPlayer = PieceColor.White;
    }

    /**
     * Returns the game status of the game.
     * @return returns the game status
     */
    public GameState getState(){
        ArrayList<Move> moves = board.getAllValidMoves(currentPlayer);
        boolean inCheck = board.isColorInCheck(currentPlayer);
        if (moves.size() == 0 && inCheck) {
            return GameState.CHECKMATE;
        } else if (moves.size() == 0 && !inCheck) {
            return GameState.STALEMATE;
        }

        return GameState.PLAY;
    }

    @Override
    public String toString() {
        return board + "" + currentPlayer + "\n";
    }
}
