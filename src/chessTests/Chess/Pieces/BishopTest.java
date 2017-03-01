package chessTests.Chess.Pieces;

import Chess.ChessBoard;
import Chess.Location;
import Chess.Pieces.Bishop;
import Chess.Pieces.ChessPiece;
import Chess.Pieces.King;
import Chess.Pieces.Pawn;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

/**
 * Created by konzy on 2/15/2017.
 */

public class BishopTest {

    private ChessBoard board;
    private Bishop testBishop1;
    private Bishop testBishop2;

    @Before
    public void setUp() throws Exception {
        testBishop1 = new Bishop(ChessPiece.PieceColor.White, new Location(1,1));
        testBishop2 = new Bishop(ChessPiece.PieceColor.Black, new Location(3,1));
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(testBishop1);
        pieces.add(new Pawn(ChessPiece.PieceColor.Black, new Location(0,2)));
        pieces.add(new Pawn(ChessPiece.PieceColor.Black, new Location(1,2)));
        pieces.add(new Pawn(ChessPiece.PieceColor.White, new Location(2,2)));
        pieces.add(new Pawn(ChessPiece.PieceColor.White, new Location(2,2)));
        pieces.add(new King(ChessPiece.PieceColor.Black, new Location(5, 5)));
        pieces.add(new King(ChessPiece.PieceColor.White, new Location(7, 7)));
        pieces.add(testBishop2);

        board = new ChessBoard(pieces);
    }

    @After
    public void tearDown() throws Exception {
        ChessBoard board = null;
        Bishop testBishop1 = null;
        Bishop testBishop2 = null;
    }

    @Test
    public void value() throws Exception {
        Assert.assertEquals(testBishop1.value(), 3);
        Assert.assertEquals(testBishop2.value(), 3);
    }

    @Test
    public void getLetter() throws Exception {
        Assert.assertEquals(testBishop1.getLetter(), "B");
        Assert.assertEquals(testBishop2.getLetter(), "B");
    }

    @Test
    public void moveModifiers() throws Exception {

    }

    @Test
    public void validMoves() throws Exception {

    }

    @Test
    public void validatedMoves() throws Exception {

    }

    @Test
    public void potentialMoves() throws Exception {

    }



    @Test
    public void numPiecesThreateningThis() throws Exception {

    }

    @Test
    public void validMoves1() throws Exception {

    }

    @Test
    public void getLocation() throws Exception {

    }

    @Test
    public void color() throws Exception {

    }

    @Test
    public void charValue() throws Exception {

    }

    @Test
    public void repeatableMoves() throws Exception {

    }

    @Test
    public void opponentOf() throws Exception {

    }

    @Test
    public void opponent() throws Exception {

    }

}