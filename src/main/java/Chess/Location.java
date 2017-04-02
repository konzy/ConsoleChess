package Chess;

/**
 * Used to store an int/int pair to map to tiles on the chessboard.
 */

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

    public double getValue() {
        if (x == 0 || y == 0 || x == 7 || y == 7) {
            return 1.0;
        } else if (x == 1 || y == 1 || x == 6 || y == 6) {
            return 1.05;
        } else if (x == 2 || y == 2 || x == 5 || y == 5) {
            return 1.1;
        } else if (x == 3 || y == 3 || x == 4 || y == 4) {
            return 1.15;
        }
        return 1.0;
    }

    public boolean equals(Location location) {
        return this.x == location.x && this.y == location.y;
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