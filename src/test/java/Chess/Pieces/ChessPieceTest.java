package Chess.Pieces;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by konzy on 2/17/2017.
 */
public class ChessPieceTest {

    private ChessGame game;
    private Bishop testBishop1 = new Bishop(ChessPiece.PieceColor.White, new Location(1,1));
    private Bishop testBishop2 = new Bishop(ChessPiece.PieceColor.White, new Location(1,1));
    private Pawn pawn1 = new Pawn(ChessPiece.PieceColor.Black, new Location(0,2));
    private Pawn pawn2 = new Pawn(ChessPiece.PieceColor.White, new Location(2,2));
    private King king1 = new King(ChessPiece.PieceColor.Black, new Location(5, 5));
    private King king2 = new King(ChessPiece.PieceColor.White, new Location(7, 7));

    private ArrayList<ChessPiece> pieces = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        pieces.add(pawn2);
        pieces.add(king2);
        pieces.add(testBishop1);
        pieces.add(pawn1);
        pieces.add(king1);

        game = new ChessGame(new ChessBoard(pieces));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void setHasMoved() throws Exception {
        Bishop bishop = new Bishop(ChessPiece.PieceColor.White, new Location(0, 0));
        bishop.setHasMoved(true);

        Assert.assertTrue(bishop.hasMoved());

    }

    @Test
    public void testCompareTo() throws Exception {
        Assert.assertEquals(king1.compareTo(new Object()), 0);
    }

    @Test
    public void equals() throws Exception {
        Bishop bishop = null;
        Assert.assertEquals(testBishop1.equals(bishop), false);

        bishop = new Bishop(ChessPiece.PieceColor.White, new Location(1, 1));
        Rook rook = new Rook(ChessPiece.PieceColor.White, new Location(1, 1));
        Assert.assertEquals(bishop.equals((ChessPiece)rook), false);

        Pawn pawn = new Pawn(ChessPiece.PieceColor.White, new Location(1, 1));
        Assert.assertEquals(bishop.equals((ChessPiece)pawn), false);

        Bishop blackBishop = new Bishop(ChessPiece.PieceColor.Black, new Location(1, 1));
        Assert.assertEquals(bishop.equals(blackBishop), false);

        Bishop locationBishop = new Bishop(ChessPiece.PieceColor.Black, new Location(2, 2));
        Assert.assertEquals(bishop.equals(locationBishop), false);

        Assert.assertEquals(bishop.equals(bishop), true);

        Bishop bishop2 = new Bishop(ChessPiece.PieceColor.White, new Location(1, 1));
        Assert.assertEquals(bishop.equals(bishop2), true);
    }

    @Test
    public void testClone() throws Exception {
        Bishop clone = (Bishop) testBishop1.clone();
        Assert.assertNotSame(clone, testBishop1);

        Assert.assertEquals(clone.getLetter(), testBishop1.getLetter());
        Assert.assertEquals(clone.charValue(), testBishop1.charValue());
        Assert.assertEquals(clone.value(game), testBishop1.value(game), 0);
        Assert.assertEquals(clone.repeatableMoves(), testBishop1.repeatableMoves());
        Assert.assertEquals(clone.opponent(), clone.opponent());
        Assert.assertEquals(clone.color(), testBishop1.color());
        Assert.assertEquals(clone.getColor(), testBishop1.getColor());

        Assert.assertEquals(clone.getLocation().x, testBishop1.getLocation().x);
        Assert.assertEquals(clone.getLocation().y, testBishop1.getLocation().y);
        Assert.assertEquals(clone.getLocation(), testBishop1.getLocation());

        Assert.assertEquals(clone.validMoves(game), testBishop1.validMoves(game));
        Assert.assertEquals(clone.numPiecesThreateningThis(game), testBishop1.numPiecesThreateningThis(game));
        Assert.assertEquals(clone.potentialMoves(game), testBishop1.potentialMoves(game));

        Assert.assertEquals(clone, testBishop1);

        clone = new Bishop(ChessPiece.PieceColor.Black, new Location(3, 1));
        Assert.assertNotSame(clone, testBishop1);
        Assert.assertNotEquals(clone, testBishop1);

        Assert.assertEquals(testBishop1, testBishop2);

        clone = testBishop2;

        Assert.assertNotSame(clone, testBishop1);
        Assert.assertEquals(clone, testBishop1);
    }

    @Test
    public void compareTo() throws Exception {
        Collections.sort(pieces);

        Assert.assertEquals(pieces.get(0), testBishop1);
        Assert.assertEquals(pieces.get(1), pawn1);
        Assert.assertEquals(pieces.get(2), pawn2);
        Assert.assertEquals(pieces.get(3), king1);
        Assert.assertEquals(pieces.get(4), king2);
    }

    @Test
    public void getDeclaringClass() throws Exception {

    }

    @Test
    public void valueOf() throws Exception {

    }

    @Test
    public void finalize() throws Exception {

    }

}