package Chess;

import Chess.Pieces.Bishop;
import Chess.Pieces.ChessPiece;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Elizabeth on 3/14/2017.
 */
public class MoveTest {
    private Bishop testPiece1;
    private Bishop testPiece2;
    private Location testLocation1;
    private Location testLocation2;
    private Move move;
    private Move move2;
    private Move move3;
    private Move clone;
    @Before
    public void setUp() throws Exception {
        testPiece1 = new Bishop(ChessPiece.PieceColor.White, new Location(1,1));
        testPiece2 = new Bishop(ChessPiece.PieceColor.White, new Location(2,2));
        testLocation1 = new Location(1,1);
        testLocation2 = new Location(2,2);
        move = new Move(testPiece1,testLocation1);
        move2 = new Move(testPiece1,testLocation1);
        move3 = new Move(testPiece2,testLocation2);
        //testLocation2 = new Location(2,);

    }

    @After
    public void tearDown() throws Exception {
        Bishop testPiece1 = null;
        Bishop testPiece2 = null;
        Location testLocation1= null;
        Location testLocation2= null;
        Move move = null;
        Move move2 = null;
        Move move3 = null;
        Move clone = null;
    }

    @Test
    public void getPiece() throws Exception {
        Assert.assertEquals(move.getPiece(), testPiece1);
    }

    @Test
    public void getTo() throws Exception {
        Assert.assertEquals(move.getTo(), testLocation1);
        Assert.assertNotEquals(move.getTo(),testLocation2);
    }

    @Test
    public void equals() throws Exception {
        Assert.assertEquals(move.equals(move2),true);
        Assert.assertEquals(move.equals(move3),false);
    }

    @Test
    public void testClone() throws Exception {
        clone = (Move)move.clone();
        Assert.assertNotSame(move,clone);

        Assert.assertEquals(clone.equals(move),true);
        Assert.assertEquals(clone.equals(move3),false);
    }

}