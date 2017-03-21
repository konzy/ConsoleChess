package Chess;

import Chess.Pieces.ChessPiece;

/**
 * Created by konzy on 2/6/2017.
 */
public class Move implements Cloneable {

    private ChessPiece piece;
    private Location to;

    public Move(ChessPiece piece, Location to) {
        this.piece = piece;
        this.to = to;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public Location getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (!piece.equals(move.piece)) return false;
        return to.equals(move.to);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Move clone = (Move) super.clone();
        clone.piece = (ChessPiece) piece.clone();
        clone.to = (Location) to.clone();
        return clone;
    }
}
