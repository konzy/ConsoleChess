package Chess;

import Chess.Pieces.ChessPiece;
import Chess.Pieces.ChessPiece.PieceColor;
import Console.BoardDisplay;
import java.util.ArrayList;

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

    public ChessGame deepCopy() {
        try {
            return (ChessGame)this.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
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
    public void playMove(Coord from, Coord to){
        if(isValidMove(from, to)) {
            Tile fromTile = board.getBoardArray()[from.Y()][from.X()];
            ChessPiece pieceToMove = fromTile.getPiece();

            Tile toTile = board.getBoardArray()[to.Y()][to.X()];
            toTile.setPiece(pieceToMove);

            fromTile.empty();
            endTurn();
            BoardDisplay.printBoard(board);
        } else
            System.out.println("Invalid move!");
    }

    /**
     * Checks to see if the current player color is in the position of a check mate.
     * @param color Color of the current player.
     * @return Returns the check mate status.
     */
    public boolean isColorCheckMate(PieceColor color){
        Coord kingLocation = board.getKingLocation(color);
        ChessPiece king = board.getTileFromCoordinate(kingLocation).getPiece();
        Move[] possibleMoves = allPossibleMovesForPiece(king, kingLocation);
        boolean checkMate = true;
        for (Move move : possibleMoves){
            int newX = kingLocation.X() + move.x;
            int newY = kingLocation.Y() + move.y;
            Coord newLocation = new Coord(newX, newY);

            //(made by Original creator) TODO check if king can move here (eg. is own piece taking spot)

            if (!isLocationOpponent(newLocation, color)){
                checkMate = false;
                break;
            }
        }
        return checkMate;
    }

    /**
     * Checks that the move in question doesn't put you in check.
     * @param kingColor
     * @return
     */
    public boolean isKinginCheck(PieceColor kingColor){
        Coord kingLocation = board.getKingLocation(kingColor);
        return isLocationOpponent(kingLocation, kingColor);
    }

    /**
     * Checks if the given location contains a piece of the opponent's color
     * @param location
     * @param color
     * @return returns true if the piece is the opponents color
     */
    private boolean isLocationOpponent(Coord location, PieceColor color){
        PieceColor opponentColor = ChessPiece.opponent(color);
        Coord[] piecesLocation = board.getAllPiecesLocationForColor(opponentColor);
        boolean isCheck = false;
        for(Coord from: piecesLocation){
            if(isValidMove(from, location)){
                isCheck = true;
                break;
            }
    }
        return isCheck;
    }

    /**
     * Ends turn and switches player's colors for next turn's logic.
     */
    private void endTurn(){
        if (currentPlayer == PieceColor.White) currentPlayer = PieceColor.Black;
        else currentPlayer = PieceColor.White;
    }

    /**
     * Tests to see if the move for the piece at the from coordinates can make a move to the "to" coordinates.
     * @param from
     * @param to
     * @return returns true if the path is a legal move and false if it is not
     */
    public boolean isValidMove(Coord from, Coord to){

        if (!isInsideBoard(from, to)) {
            return false;
        }

        Tile fromTile = board.getTileFromCoordinate(from);
        Tile toTile = board.getTileFromCoordinate(to);
        ChessPiece fromPiece = fromTile.getPiece();
        ChessPiece toPiece = toTile.getPiece();

        if (fromPiece == null){
            return false;
        } else if (fromPiece.color() != currentPlayer) {
            return false;
        } else if (toPiece != null && toPiece.color() == currentPlayer) {//null pointer if null not evaluated first
            return false;
        } else if (isPossibleMoveForPiece(from, to)){
            toTile.setPiece(fromPiece);//temporarily play the move
            fromTile.empty();
            if (isKinginCheck(currentPlayer)){//check that moves doesn't put oneself in check
                toTile.setPiece(toPiece);
                fromTile.setPiece(fromPiece);//revert
                return false;
            }
            // if move ends in check mate, finish game
            if (isColorCheckMate(ChessPiece.opponent(currentPlayer)))
                isFinished = true;

            toTile.setPiece(toPiece);
            fromTile.setPiece(fromPiece);//revert
            return true;//conditional path for legal move
        }
        return false;
    }

    private boolean isInsideBoard(Coord from, Coord to) {
        return !(from.X() < 0 || from.X() > 7 || from.Y() < 0 || from.X() > 7 ||
                to.X() < 0 || to.X() > 7 || to.Y() < 0 || to.X() > 7);
    }

    /**
     * Tests if the move is valid for the given color
     * @param takeColor
     * @param locationToTake
     * @return true if valid/false if not
     */
    public boolean canColorTakeLocation(PieceColor takeColor, Coord locationToTake){
        Coord[] locations = board.getAllPiecesLocationForColor(takeColor);
        boolean canTake = false;
        for (Coord coordinate: locations){
            if (isValidMove(coordinate, locationToTake)) {
                canTake = true;
                break;
            }
        }
        return canTake;
    }

    /**
     * calculates and returns an array of all possible moves for a given piece based off of its location
     * @param piece
     * @param currentLocation
     * @return array of moves.
     */
    public Move[] allPossibleMovesForPiece(ChessPiece piece, Coord currentLocation){

        if (piece == null || piece.moves() == null) {
            return new Move[0];
        }


        Move[] moves = piece.moves();
        ArrayList<Move> possibleMoves = new ArrayList<>();

        for(Move move: moves){
            int currentX = currentLocation.X();
            int currentY = currentLocation.Y();

            int newX = currentX + move.x;
            int newY = currentY + move.y;

            Coord newLocation = new Coord(newX, newY);

            if (isPossibleMoveForPiece(currentLocation, newLocation)) possibleMoves.add(move);
        }

        return possibleMoves.toArray(new Move[0]);//allocate new array automatically.
    }

    /**
     * Checks to see if a move is valid to be taken from a perspective of movement of the piece or replacing a
     * opposite color piece if the
     * @param from
     * @param to
     * @return returns true if valid and false if it isn't
     */
    private boolean isPossibleMoveForPiece(Coord from, Coord to){

        if (!isInsideBoard(from, to)) {
            return false;
        }

        ChessPiece fromPiece = board.getTileFromCoordinate(from).getPiece();
        Move[] validMoves = fromPiece.moves();
        boolean repeatableMoves = fromPiece.repeatableMoves();
        int xMove = from.X() - to.X();
        int yMove = from.Y() - to.Y();

        // Reverse values for black, such that lowering y-values are treated as moving forward
        // only relevant in the case of pawns, but added for abstract movement assignment for pieces.
        if (currentPlayer == PieceColor.Black) {
            yMove = -yMove;
        }

        boolean validMove = false;
        if(!repeatableMoves){
            for (Move move : validMoves) {
                if (move.x == xMove && move.y == yMove) {
                    if (move.onTakeOnly){// if move is only legal on take (pawns)
                        Tile toTile = board.getTileFromCoordinate(to);
                        if (toTile.isEmpty()) break;

                        ChessPiece toPiece = toTile.getPiece();
                        validMove = fromPiece.color() != toPiece.color();// if different color, valid move
                        break;
                    // handling first move only for pawns
                    } else if (move.firstMoveOnly && (fromPiece.color() == PieceColor.White && from.Y() != 6
                               || fromPiece.color() == PieceColor.Black && from.Y() != 1)) {
                        break;
                    } else {
                        validMove = true;
                        break;
                    }
                }
            }
        } else {
            for(int i = 0; i <= 8; i++){// Max number of repetitions
                for(Move move : validMoves) {
                    if (move.x * i == xMove && move.y * i == yMove) {
                        validMove = true;
                        break;
                    }
                }
            }
        }
        return validMove;
    }

    /**
     * Returns the game status of the game.
     * @return returns the game status
     */
    public boolean isFinished(){
        return isFinished;
    }
}
