package Chess.Pieces;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Move;
import GUI.GameBoard;
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
    protected String picPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
    protected boolean hasMoved;

    protected ImageView image;

    /**
     * Creates an abstract chess piece object.
     *
     * @param type the kind of piece this is
     * @param color either whiteImage or blackImage
     * @param repeatableMoves whether moveModifiers extend to the edge of the board
     */
    protected ChessPiece(PieceType type, PieceColor color, boolean repeatableMoves, Location location, boolean hasMoved){
        this.color = color;
        this.repeatableMoves = repeatableMoves;
        this.location = location;
        this.hasMoved = hasMoved;

        charValue = type.name().trim().charAt(0);
        if (type.name().equals(PieceType.Knight.name())) {
            charValue = 'N';
        }
    }

    public boolean hasMoved() {
        return hasMoved;
    }



    public ImageView getImage() {
        return image;
    }

    protected void setImage(PieceColor color, String letter) {
        image = new ImageView(picPath.substring(0, picPath.length() - 14) + "/resources/main/" + color.name().toLowerCase() + "_" + letter.toLowerCase() + ".png");
    }

    public abstract void setImage();

    abstract public double value(ChessGame game);

    @Override
    public int compareTo(Object o) {
        if (o instanceof ChessPiece) {

            if (this.location.y == ((ChessPiece) o).location.y) {
                return this.location.x - ((ChessPiece) o).location.x;
            } else {
                return this.location.y - ((ChessPiece) o).location.y;
            }
        }
        return 0;
    }

    public double favorCenter() {
        double valueAdded = 0;
        int x = location.x;
        int y = location.y;
        if (x == 1 || x == 6) {
            valueAdded += 0.05;
        } else if (x == 2 || x == 5) {
            valueAdded += 0.1;
        } else if (x == 3 || x == 4) {
            valueAdded += 0.15;
        }
        if (y == 1 || y == 6) {
            valueAdded += 0.05;
        } else if (y == 2 || y == 5) {
            valueAdded += 0.1;
        } else if (y == 3 || y == 4) {
            valueAdded += 0.15;
        }
        return valueAdded;
    }

    //im not sure if this needs to be static, but when it wasn't, cloning "this" was not possible, maybe
    public static ArrayList<Move> validatedMoves(ChessGame game, ArrayList<Move> potentialMoves, PieceColor color) {
        ArrayList<Move> validMoves = new ArrayList<>();
        for (Move move : potentialMoves) {
                ChessGame clonedGame = (ChessGame) game.clone();
                Move clonedMove = (Move) move.clone();
                if (clonedGame != null) {
                    clonedGame.getBoard().move(clonedMove);
                    if (!clonedGame.isColorInCheck(color)) {
                        validMoves.add(move);
                    }
                }
        }
        return validMoves;
    }

    public PieceColor getColor() {
        return color;
    }

    public ArrayList<Move> potentialMoves(ChessGame game) {
        ChessBoard board = game.getBoard();
        ArrayList<Move> toLocations = new ArrayList<>();
        for (Location moveOffset : moveModifiers()) {
            Location to = location;
            do {
                to = new Location(to.x + moveOffset.x, to.y + moveOffset.y);
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

    abstract public Location[] moveModifiers();

    public void setLocation(Location location) {
        hasMoved = true;
        this.location = location;
    }

    public int numPiecesThreateningThis(ChessGame game) {
        int result = 0;
        ArrayList<Move> opponentMoves = game.getPotentialMoves(opponent());

        for (Move opponentMove : opponentMoves) {
            Location to = opponentMove.getTo();
            if (location.equals(to)) { // if an opponent can move on top of me, then it means that it's threatening me
                result++;
            }
        }

        return result;
    }

    abstract public ArrayList<Move> validMoves(ChessGame game);



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

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
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