package Chess.Pieces;

import Chess.ChessGame;
import Chess.Pieces.*;
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
        king = null;
    }

    @Test
    public void constructors() throws Exception {
        King king1 = new King(ChessPiece.PieceColor.Black, new Location(0, 0));
        Assert.assertNotNull(king1);
        King king2 = new King(ChessPiece.PieceColor.Black, new Location(1, 1), true);
        Assert.assertNotNull(king2);
    }

    @Test
    public void canCastle() throws Exception {

    }

    @Test
    public void value() throws Exception {
        Assert.assertEquals(king.value(new ChessGame()), 100.0, 0);
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