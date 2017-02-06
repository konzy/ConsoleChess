package Chess;

/**
 * Created by konzy on 2/6/2017.
 */
public class CoordPair {

    Coord from;
    Coord to;

    public CoordPair(Coord from, Coord to) {
        this.from = from;
        this.to = to;
    }

    public Coord getFrom() {
        return from;
    }

    public void setFrom(Coord from) {
        this.from = from;
    }

    public Coord getTo() {
        return to;
    }

    public void setTo(Coord to) {
        this.to = to;
    }
}
