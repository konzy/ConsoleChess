package Chess.Pieces;


import Chess.Location;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by konzy on 2/16/2017.
 */
public class KingTest {

    private King king = new King(ChessPiece.PieceColor.Black, new Location(4,4));

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void value() throws Exception {
        Assert.assertEquals(king.value(), 4);
    }

    @Test
    public void getLetter() throws Exception {
        Assert.assertEquals(king.getLetter(), "K");
    }

    @Test
    public void moveModifiers() throws Exception {

    }

    @Test
    public void validMoves() throws Exception {

    }

}