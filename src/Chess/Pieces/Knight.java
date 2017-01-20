package Chess.Pieces;

import Chess.Move;

/**
 * A Knight is a piece that can move two spaces in any direction and one space 90 degrees from that direction.  This makes
 * the movement look like an "L" and the piece can "jump" over other pieces to get to that tile.  It can also capture during this movement.
 */
public class Knight extends ChessPiece{

	public Knight(ChessPiece.PieceColor color){
		super(PieceType.Knight, color, false);
	}

    @Override
    public Move[] moves() {
        return new Move[]{	new Move(2, 1, false, false), new Move(1, 2, false, false),
                new Move(2, -1, false, false), new Move(-1, 2, false, false),
                new Move(2, -1, false, false), new Move(-1, 2, false, false),
                new Move(-2, 1, false, false), new Move(1, -2, false, false),
                new Move(-2, -1, false, false), new Move(-1, -2, false, false),
                new Move(-2, -1, false, false), new Move(-1, -2, false, false)};
    }
}
