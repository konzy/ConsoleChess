package Chess;

import Chess.Pieces.ChessPiece;
import Chess.Pieces.ChessPiece.PieceColor;
import Console.BoardDisplay;

/**
 * Methods for general play of chess.
 */
public class ChessGame implements Cloneable {

    private ChessBoard board;
    private boolean isFinished;
    private PieceColor currentPlayer;

    /**
     * Starts up the game with initial conditions and displays the board.
     */
    public ChessGame(){
        board = new ChessBoard();
        currentPlayer = PieceColor.White;
        isFinished = false;

        BoardDisplay.clearConsole();
        BoardDisplay.printBoard(board);
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

    /**
     * Takes the input of a piece to be moved from and to a position and moves the piece if it is a valid move.
     * @param from current position of the game piece
     * @param to future position of the game piece
     */
    public boolean playMove(Location from, Location to){
        ChessPiece piece = board.getPieceAtCoord(from);
        Move move = new Move(piece, to);
        if (from != null && piece != null && to != null && board.getAllValidMoves(currentPlayer).contains(move)) {
            board.move(move);
            BoardDisplay.printBoard(board);
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
    public boolean isFinished(){
        return isFinished;
    }
}
