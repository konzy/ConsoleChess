package Chess;

/**
 * Used to store an int/int pair to map to tiles on the chessboard.
 */
public class Tuple {
    private int x;
    private int y;

    /**
     * @param x coordinate of the piece
     * @param y coordinate of the piece
     */
    public Tuple(int x, int y){
            this.x = x;
            this.y =y;
    }

    /**
     * returns the x coordinate
     * @return
     */
    public int X(){
        return x;
    }

    /**
     * returns the y coordinate
     * @return
     */
    public int Y(){
        return y;
    }

}
