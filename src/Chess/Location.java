package Chess;

/**
 * Used to store an int/int pair to map to tiles on the chessboard.
 */
// TODO: 3/14/2017 change variables to public and remove getters for them
public class Location implements Cloneable {
    public int x;
    public int y;

    /**
     * @param x coordinate of the piece
     * @param y coordinate of the piece
     */
    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    protected Location(Location location) {
        x = location.x;
        y = location.y;
    }

    public boolean equals(Location location) {
        return this.x == location.x && this.y == location.y;
    }

    /**
     * @deprecated use the public variable
     * returns the x coordinate
     * @return
     */
    @Deprecated
    public int X(){
        return x;
    }

    /**
     * @deprecated use the public variable
     * returns the y coordinate
     * @return
     */
    @Deprecated
    public int Y(){
        return y;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        return y == location.y;
    }

    @Override
    public String toString() {
        return (char)(x + 65) + String.valueOf(8 - y);
    }
}
