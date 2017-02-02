package Chess;


import Chess.Pieces.ChessPiece;

public class Tile implements Cloneable {

    private ChessPiece piece;
    private TileColor color;

    public enum TileColor{
        White, Black
    }

    /**
     * sets tile color
     * @param color
     */
    public Tile(TileColor color){
        this.color = color;
    }

    /**
     * sets tile color and piece on that tile
     * @param color
     * @param piece
     */
    public Tile(TileColor color, ChessPiece piece){
        this.color = color;
        this.piece = piece;
    }

    /**
     *
     * @return the deep copy of Tile
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * sets the piece
     * @param piece
     */
    public void setPiece(ChessPiece piece){
        this.piece = piece;
    }

    /**
     * returns a piece
     * @return
     */
    public ChessPiece getPiece(){
        return this.piece;
    }

    /**
     * checks if there is a piece on the tile, then outputs the tile appropriately, either with the piece or empty
     * @return
     */
    public String value(){
        if(piece != null){
            return "[" + piece.charValue() + "]";
        } else {
            return "[ ]";
        }
    }

    /**
     * checks if a tile is empty
     * @return
     */
    public boolean isEmpty(){
        return piece == null;
    }

    /**
     * makes a tile empty
     */
    public void empty(){
        piece = null;
    }
}
