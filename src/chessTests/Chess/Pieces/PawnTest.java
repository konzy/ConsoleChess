package Chess.Pieces;

import Chess.Location;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by konzy on 2/17/2017.
 */
public class PawnTest {

    private Pawn pawn = new Pawn(ChessPiece.PieceColor.Black, new Location(1,1));

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void value() throws Exception {
        Assert.assertEquals(pawn.value(), 1);
    }

    @Test
    public void getLetter() throws Exception {
        Assert.assertEquals(pawn.getLetter(), "P");
    }

    @Test
    public void moveModifiers() throws Exception {

    }

    @Test
    public void potentialMoves() throws Exception {

    }

    @Test
    public void validMoves() throws Exception {

    }

}