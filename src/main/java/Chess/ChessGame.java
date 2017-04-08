package Chess;

import Chess.Pieces.ChessPiece;
import Chess.Pieces.ChessPiece.PieceColor;
import Chess.Pieces.King;
import Chess.Pieces.Pawn;

import java.util.ArrayList;

/**
 * Methods for general play of chess.
 */
public class ChessGame implements Cloneable {

    private ChessBoard board;
    private PieceColor currentPlayer;
    private boolean isTwoPlayer = true;
    private Move previousMove = null;

    public enum GameState {
        PLAY,
        CHECKMATE,
        STALEMATE
    }

    public Move getPreviousMove() {
        return previousMove;
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

    public ChessGame(boolean isTwoPlayer){
        this();
        this.isTwoPlayer = isTwoPlayer;
    }

    public ChessGame(ChessBoard board, boolean isTwoPlayer) {
        this(board);
        this.isTwoPlayer = isTwoPlayer;
    }

    public ArrayList<Move> getPotentialMoves(ChessPiece.PieceColor color) {
        ArrayList<Move> potentialMoves = new ArrayList<>();
        for (ChessPiece piece : board.getBoardArrayList()) {
            if (piece.color().equals(color)) {
                potentialMoves.addAll(piece.potentialMoves(this));
            }
        }
        return potentialMoves;
    }

    /**
     * Gets all the moves that are both possible, in a logistic sense, and legal in a rules sense,
     * where it does not allow your king to be in check after the move.
     * @param color
     * @return
     */
    public ArrayList<Move> getAllValidMoves(ChessPiece.PieceColor color) {
        ArrayList<Move> moves = new ArrayList<>();

        for (ChessPiece chessPiece : getBoard().getAllPiecesLocationForColor(color)) {
            moves.addAll(chessPiece.validMoves(this));
        }

        return moves;
    }

    @Override
    public Object clone() {
        ChessGame clone = null;
        try {
            clone = (ChessGame)super.clone();
            clone.board = (ChessBoard)board.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
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
    public boolean playMove(Location from, Location to) {
        ChessPiece piece = board.getPieceAtLocation(from);
        Move move = new Move(piece, to);
        if (from != null && piece != null && to != null && getAllValidMoves(currentPlayer).contains(move)) {

            enPassantCheck(move);
            castlingCheck(move);
            previousMove = (Move) move.clone();
            board.move(move);
            promotionCheck();

            endTurn();
            return true;
        } else {
            //System.out.println("Invalid move!");
            return false;
        }
    }

    private void enPassantCheck(Move move) {
        if (currentPlayer == PieceColor.White &&
                previousMove != null &&
                previousMove.getPiece() instanceof Pawn &&
                !previousMove.getPiece().hasMoved() &&
                Math.abs(previousMove.getPiece().getLocation().y - previousMove.getTo().y) == 2 &&
                move.getPiece().getLocation().y == 3) {

            Location loc = new Location(move.getTo().x, move.getPiece().getLocation().y);
            board.removePiece(loc);
        } else if (currentPlayer == PieceColor.Black &&
                previousMove != null &&
                previousMove.getPiece() instanceof Pawn &&
                !previousMove.getPiece().hasMoved() &&
                Math.abs(previousMove.getPiece().getLocation().y - previousMove.getTo().y) == 2 &&
                move.getPiece().getLocation().y == 4) {

            Location loc = new Location(move.getTo().x, move.getPiece().getLocation().y);
            board.removePiece(loc);
        }

    }

    private void castlingCheck(Move move) {
        if (King.canCastleKingSide(move.getPiece(), this) &&
                move.getTo().equals(new Location(6, move.getPiece().getLocation().y))) {

            ChessPiece rook = board.getPieceAtLocation(new Location(7, move.getPiece().getLocation().y));

            board.move(new Move(rook, new Location(5, rook.getLocation().y)));

        } else if (King.canCastleQueenSide(move.getPiece(), this) &&
                move.getTo().equals(new Location(2, move.getPiece().getLocation().y))) {

            ChessPiece rook = board.getPieceAtLocation(new Location(0, move.getPiece().getLocation().y));

            board.move(new Move(rook, new Location(3, rook.getLocation().y)));
        }
    }

    private void promotionCheck() {
        ArrayList<ChessPiece> currentPieces = getBoard().getAllPiecesLocationForColor(currentPlayer);

        for (ChessPiece currentPiece : currentPieces) {
            if (currentPiece instanceof Pawn && ((Pawn) currentPiece).readyToPromote()) {
                board.promote(currentPiece);
            }
        }
    }

    public boolean playMove(Move move) {
        if (move == null || move.getPiece() == null || move.getPiece().getLocation() == null || move.getTo() == null) {
            System.out.println("something is fucked up");
        }

        return playMove(move.getPiece().getLocation(), move.getTo());
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
        ArrayList<Move> moves = getAllValidMoves(currentPlayer);
        boolean inCheck = isColorInCheck(currentPlayer);
        if (moves.size() == 0 && inCheck) {
            return GameState.CHECKMATE;
        } else if (moves.size() == 0 && !inCheck) {
            return GameState.STALEMATE;
        }

        return GameState.PLAY;
    }

    /**
     * Checks whether the color is is check
     * @param color
     * @return
     */
    public boolean isColorInCheck(ChessPiece.PieceColor color) {
        return getBoard().getKingPiece(color).numPiecesThreateningThis(this) > 0;
    }

    public ArrayList<ChessPiece> getPiecesWeThreaten(ChessPiece.PieceColor color) {
        ArrayList<ChessPiece> result = new ArrayList<>();
        for (Move move : getAllValidMoves(color)) {
            ChessPiece piece = board.getPieceAtLocation(move.getTo());
            if (piece != null && piece.getColor() != color) {
                result.add(piece);
            }
        }
        return result;
    }

    public double differenceInAdvantage() {
        double currentPlayerScore = 0.0;
        double opponentScore = 0.0;

        for (ChessPiece chessPiece : board.getAllPiecesLocationForColor(currentPlayer)) {
            currentPlayerScore += chessPiece.value() + chessPiece.getLocation().getValue();
        }

        for (ChessPiece chessPiece : board.getAllPiecesLocationForColor(ChessPiece.opponentOf(currentPlayer))) {
            opponentScore += chessPiece.value() + chessPiece.getLocation().getValue();
        }
        return currentPlayerScore - opponentScore;
    }


    @Override
    public String toString() {
        return board + "" + currentPlayer + "\n" + isTwoPlayer + "\n";
    }
}
