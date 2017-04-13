package Chess.Pieces;

import Chess.Pieces.*;
import Chess.Location;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by konzy on 2/16/2017.
 */
public class KnightTest {

    private Knight knight = new Knight(ChessPiece.PieceColor.Black, new Location(1,1));

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        knight = null;
    }

    @Test
    public void constructors() throws Exception {
        Knight knight1 = new Knight(ChessPiece.PieceColor.Black, new Location(0, 0));
        Assert.assertNotNull(knight1);
        Knight knight2 = new Knight(ChessPiece.PieceColor.Black, new Location(1, 1), true);
        Assert.assertNotNull(knight2);
    }

    @Test
    public void value() throws Exception {
        Assert.assertEquals(knight.value(), 3);
    }

    @Test
    public void getLetter() throws Exception {
        Assert.assertEquals(knight.getLetter(), "N");
    }

    @Test
    public void moveModifiers() throws Exception {

    }

    @Test
    public void validMoves() throws Exception {

    }
}