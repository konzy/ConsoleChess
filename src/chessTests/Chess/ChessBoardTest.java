package Chess;

import Chess.Pieces.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by konzy on 2/28/2017.
 */
public class ChessBoardTest {

    private Pawn pawn = new Pawn(ChessPiece.PieceColor.Black, new Location(1,1));

    private ChessBoard board;
    @Before
    public void setUp() throws Exception {
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(pawn);

        pieces.add(new King(ChessPiece.PieceColor.Black, new Location(0, 7)));
        pieces.add(new King(ChessPiece.PieceColor.White, new Location(0, 0)));
        board = new ChessBoard(pieces);
    }

    @After
    public void tearDown() throws Exception {
        pawn = null;
    }

    @Test
    public void getPieceAtCoord() throws Exception {

    }

    @Test
    public void isInsideBoard() throws Exception {

    }

    @Test
    public void isInsideBoard1() throws Exception {

    }

    @Test
    public void removePiece() throws Exception {

    }

    @Test
    public void removePiece1() throws Exception {

    }

    @Test
    public void move() throws Exception {

    }

    @Test
    public void isColorInCheck() throws Exception {

    }

    @Test
    public void getAllValidMoves() throws Exception {

    }

    @Test
    public void testClone() throws Exception {

    }

    @Test
    public void getBoardArrayList() throws Exception {

    }

    @Test
    public void getKingPiece() throws Exception {

    }

    @Test
    public void getAllPiecesLocationForColor() throws Exception {

    }

    @Test
    public void getBoardArray() throws Exception {

    }

    @Test
    public void getPotentialMoves() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }

}