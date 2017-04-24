package Chess.Pieces;

import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.ChessPiece;
import Chess.Pieces.Rook;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by konzy on 2/17/2017.
 */
public class RookTest {

    private Rook rook = new Rook(ChessPiece.PieceColor.Black, new Location(4,4));

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        rook = null;
    }

    @Test
    public void constructors() throws Exception {
        Rook rook1 = new Rook(ChessPiece.PieceColor.Black, new Location(0, 0));
        Assert.assertNotNull(rook1);
        Rook rook2 = new Rook(ChessPiece.PieceColor.Black, new Location(1, 1), true);
        Assert.assertNotNull(rook2);
    }

    @Test
    public void value() throws Exception {
        Assert.assertEquals(rook.value(new ChessGame()), 5, 0);
    }

    @Test
    public void getLetter() throws Exception {
        Assert.assertEquals(rook.getLetter(), "R");
    }

    @Test
    public void moveModifiers() throws Exception {

    }

    @Test
    public void validMoves() throws Exception {

    }

}