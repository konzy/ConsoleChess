package Chess;

/**
 * Moves pieces and sets firstMoveOnly and onTakeOnly
 */
public class Move{
    public final int x;
    public final int y;
    public final boolean firstMoveOnly;
    public final boolean onTakeOnly;

    /**
     * @param x coordinate for the piece
     * @param y coordinate for the piece
     * @param firstMoveOnly status for the piece
     * @param onTakeOnly status for the piece
     */
    public Move(int x, int y, boolean firstMoveOnly, boolean onTakeOnly) {
        this. x = x;
        this. y = y;
        this.firstMoveOnly = firstMoveOnly;
        this.onTakeOnly = onTakeOnly;
    }
}
