package Chess.Pieces;

import Chess.Pieces.*;
import Chess.Location;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by konzy on 2/17/2017.
 */
public class QueenTest {

    private Queen queen = new Queen(ChessPiece.PieceColor.Black, new Location(1,1));

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        queen = null;
    }

    @Test
    public void value() throws Exception {
        Assert.assertEquals(queen.value(), 9);
    }

    @Test
    public void getLetter() throws Exception {
        Assert.assertEquals(queen.getLetter(), "Q");
    }

    @Test
    public void moveModifiers() throws Exception {

    }

    @Test
    public void validMoves() throws Exception {

    }

}