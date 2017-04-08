package Chess;

import Chess.Pieces.ChessPiece;

/**
 * Created by konzy on 2/6/2017.
 */
public class Move implements Cloneable {

    ChessPiece piece;
    Location to;

    public Move(ChessPiece piece, Location to) {
        this.piece = piece;
        this.to = to;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(Location from) {
        this.piece = piece;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
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
    public Object clone() {
        Move clone = null;
        try {
            clone = (Move) super.clone();
            clone.piece = (ChessPiece) piece.clone();
            clone.to = (Location) to.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}
