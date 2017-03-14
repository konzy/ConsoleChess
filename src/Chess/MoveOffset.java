package Chess;

/**
 * @deprecated use Location instead, firstMoveOnly and onTakeOnly not used
 * Moves pieces and sets firstMoveOnly and onTakeOnly
 */
@Deprecated
public class MoveOffset {
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
    public MoveOffset(int x, int y, boolean firstMoveOnly, boolean onTakeOnly) {
        this. x = x;
        this. y = y;
        this.firstMoveOnly = firstMoveOnly;
        this.onTakeOnly = onTakeOnly;
    }

    /**
     *
     * @return a deep copy of MoveOffset
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
