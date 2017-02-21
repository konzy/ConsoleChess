package Chess.Pieces;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by konzy on 2/17/2017.
 */
public class PawnTest {

    private Pawn pawn = new Pawn(ChessPiece.PieceColor.Black, new Location(1,1));
    private Pawn whiteSingleMovePawn = new Pawn(ChessPiece.PieceColor.White, new Location(1, 5));
    private Pawn blackSingleMovePawn = new Pawn(ChessPiece.PieceColor.Black, new Location(1, 2));
    private Pawn whiteDoubleMovePawn = new Pawn(ChessPiece.PieceColor.White, new Location(2, 6));
    private Pawn blackDoubleMovePawn = new Pawn(ChessPiece.PieceColor.Black, new Location(2, 1));

    private Pawn whiteCaptureRightPawn = new Pawn(ChessPiece.PieceColor.White, new Location(5, 6));
    private Pawn blackCaptureLeftPawn = new Pawn(ChessPiece.PieceColor.Black, new Location(5, 1));

    private Rook whiteRookToCapture = new Rook(ChessPiece.PieceColor.White, new Location(6, 5));
    private Rook blackRookToCapture = new Rook(ChessPiece.PieceColor.Black, new Location(6, 2));

    private Bishop whiteBlockingBishop = new Bishop(ChessPiece.PieceColor.White, new Location(5, 5));
    private Bishop blackBlockingBishop = new Bishop(ChessPiece.PieceColor.Black, new Location(5, 2));

    private ChessBoard board;

    @Before
    public void setUp() throws Exception {
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(whiteSingleMovePawn);
        pieces.add(blackSingleMovePawn);
        pieces.add(whiteDoubleMovePawn);
        pieces.add(blackDoubleMovePawn);

        pieces.add(whiteCaptureRightPawn);
        pieces.add(blackCaptureLeftPawn);
        pieces.add(whiteRookToCapture);
        pieces.add(blackRookToCapture);

        pieces.add(whiteBlockingBishop);
        pieces.add(blackBlockingBishop);

        pieces.add(new King(ChessPiece.PieceColor.Black, new Location(0, 7)));
        pieces.add(new King(ChessPiece.PieceColor.White, new Location(7, 7)));
        board = new ChessBoard(pieces);
    }

    @After
    public void tearDown() throws Exception {
        pawn = null;
        whiteSingleMovePawn = null;
        blackSingleMovePawn = null;
        whiteDoubleMovePawn = null;
        blackDoubleMovePawn = null;
        whiteCaptureRightPawn = null;
        blackCaptureLeftPawn = null;
        whiteRookToCapture = null;
        blackRookToCapture = null;
        whiteBlockingBishop = null;
        blackBlockingBishop = null;
        board = null;
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
        Assert.assertNull(pawn.moveModifiers());
    }

    @Test
    public void potentialMoves() throws Exception {
        Assert.assertEquals(whiteSingleMovePawn.potentialMoves(board).size(), 1);
        Assert.assertEquals(blackSingleMovePawn.potentialMoves(board).size(), 1);

        Assert.assertEquals(whiteDoubleMovePawn.potentialMoves(board).size(), 2);
        Assert.assertEquals(blackDoubleMovePawn.potentialMoves(board).size(), 2);

        Assert.assertEquals(whiteCaptureRightPawn.potentialMoves(board).size(), 1);
        Assert.assertEquals(blackCaptureLeftPawn.potentialMoves(board).size(), 1);

        Assert.assertEquals(whiteSingleMovePawn.potentialMoves(board).get(0).getTo(), new Location(1,4));
        Assert.assertEquals(blackSingleMovePawn.potentialMoves(board).get(0).getTo(), new Location(1,3));

        Assert.assertEquals(whiteDoubleMovePawn.potentialMoves(board).get(0).getTo(), new Location(2,5));
        Assert.assertEquals(whiteDoubleMovePawn.potentialMoves(board).get(1).getTo(), new Location(2,4));
        Assert.assertEquals(blackDoubleMovePawn.potentialMoves(board).get(0).getTo(), new Location(2,2));
        Assert.assertEquals(blackDoubleMovePawn.potentialMoves(board).get(1).getTo(), new Location(2,3));

        Assert.assertEquals(whiteCaptureRightPawn.potentialMoves(board).get(0).getTo(), new Location(5,4));
        Assert.assertEquals(blackCaptureLeftPawn.potentialMoves(board).get(0).getTo(), new Location(5,3));
    }

    @Test
    public void validMoves() throws Exception {

    }

}