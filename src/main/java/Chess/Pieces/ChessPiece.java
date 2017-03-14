package Chess.Pieces;

import Chess.ChessBoard;
import Chess.Location;
import Chess.Move;
import Chess.MoveOffset;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import static Chess.ChessBoard.isInsideBoard;

/**
 * ChessPiece is an abstract class that all other pieces extend.  It defines the movement of the piece from the current
 * location.
 */

public abstract class ChessPiece implements Comparable, Cloneable {
    protected PieceColor color;
    private char charValue;
    private boolean repeatableMoves;
    protected Location location;
    protected String picPath = "/main/java/GUI/assets/";
    protected ImageView image;

    /**
     * Creates an abstract chess piece object.
     *
     * @param type the kind of piece this is
     * @param color either whiteImage or blackImage
     * @param repeatableMoves whether moveModifiers extend to the edge of the board
     */
    protected ChessPiece(PieceType type, PieceColor color, boolean repeatableMoves, Location location){
        this.color = color;
        this.repeatableMoves = repeatableMoves;
        this.location = location;
        charValue = type.name().trim().charAt(0);
        if (type.name().equals(PieceType.Knight.name())) {
            charValue = 'N';
        }
    }

    public ImageView getImage() {
        return image;
    }

    protected void setImage(PieceColor color, String letter) {
        image = new ImageView(picPath + color.name().toLowerCase() + "_" + letter.toLowerCase() + ".png");
    }

    public abstract void setImage();

    abstract public int value();

    @Override
    public int compareTo(Object o) {
        if (o instanceof ChessPiece) {
            if (this.location.Y() == ((ChessPiece) o).location.Y()) {
                return this.location.X() - ((ChessPiece) o).location.X();
            } else {
                return this.location.Y() - ((ChessPiece) o).location.Y();
            }
        }
        return 0;
    }


    //im not sure if this needs to be static, but when it wasn't, cloning "this" was not possible, maybe
    public static ArrayList<Move> validatedMoves(ChessBoard board, ArrayList<Move> potentialMoves, PieceColor color) {
        ArrayList<Move> validMoves = new ArrayList<>();
        for (Move move : potentialMoves) {
            try {
                ChessBoard clonedBoard = (ChessBoard) board.clone();
                Move clonedMove = (Move) move.clone();
                if (clonedBoard != null) {
                    clonedBoard.move(clonedMove);
                    if (!clonedBoard.isColorInCheck(color)) {
                        validMoves.add(move);
                    }
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return validMoves;
    }

    public PieceColor getColor() {
        return color;
    }

    public ArrayList<Move> potentialMoves(ChessBoard board) {
        ArrayList<Move> toLocations = new ArrayList<>();
        for (MoveOffset moveOffset : moveModifiers()) {
            Location to = location;
            do {
                to = new Location(to.X() + moveOffset.x, to.Y() + moveOffset.y);
                if (isInsideBoard(to) && (board.getPieceAtLocation(to) == null || board.getPieceAtLocation(to).color() != color)) {
                    toLocations.add(new Move(this, to));
                }
                // to continue with loop, last coord must be inside the board, blank and the current piece must be able to repeat its moves
            } while (isInsideBoard(to) && board.getPieceAtLocation(to) == null && repeatableMoves);
        }
        return toLocations;
    }

    public abstract String getLetter();

    /**
     *
     * @return a deep copy of the ChessPiece
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        ChessPiece clone = (ChessPiece)super.clone();
        clone.location = (Location) location.clone();
        return super.clone();
    }

    /**
     * Valid pieces in Chess
     */
    public enum PieceType {
        Pawn, Rook, Knight, Bishop, Queen, King
    }

    /**
     * Colors of pieces in Chess
     */

    public enum PieceColor{
        White,
        Black
    }


    /**
     * @return an array of Moves that are valid for the piece
     */
    abstract public MoveOffset[] moveModifiers();


    public void setLocation(Location location) {
        this.location = location;
    }

    public int numPiecesThreateningThis(ChessBoard board) {
        int result = 0;
        ArrayList<Move> opponentMoves = board.getPotentialMoves(opponent());

        for (Move opponentMove : opponentMoves) {
            Location to = opponentMove.getTo();
            if (location.equals(to)) { // if an opponent can move on top of me, then it means that it's threatening me
                result++;
            }
        }

        return result;
    }

    abstract public ArrayList<Move> validMoves(ChessBoard board);



    public Location getLocation() {
        return location;
    }

    /**
     * @return color of the piece eg. White or Black
     */
    public PieceColor color(){ return color; }

    /**
     * @return the single letter character value of the piece eg. "P" for Pawn
     */
    public char charValue(){ return charValue; }

    /**
     * @return whether the piece can continue movement until it is either blocked by another piece, can capture,
     * or the side of the board
     */
    public boolean repeatableMoves(){ return repeatableMoves; }

    /**
     * gets the opposite color of the parameter's color
     * @param color the color of a piece
     * @return the opposite color
     */
    public static PieceColor opponentOf(PieceColor color) {
        return (color == PieceColor.Black) ? PieceColor.White : PieceColor.Black;
    }

    public PieceColor opponent() {
        return opponentOf(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChessPiece that = (ChessPiece) o;

        if (charValue != that.charValue) return false;
        if (repeatableMoves != that.repeatableMoves) return false;
        if (color != that.color) return false;
        return location.equals(that.location);
    }
}
