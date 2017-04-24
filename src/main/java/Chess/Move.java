package Chess;

import Chess.Pieces.ChessPiece;

/**
 * Created by konzy on 2/6/2017.
 */
public class Move implements Cloneable, Comparable {

    private ChessPiece piece;
    private Location to;
    private double value;

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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
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

    @Override
    public int compareTo(Object o) {
        if (o instanceof Move) {
            return (int)(value - ((Move) o).value);
        }
        return 0;
    }
}
