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

    private Rook whiteRookToCapture = new Rook(ChessPiece.PieceColor.White, new Location(6, 2));
    private Rook blackRookToCapture = new Rook(ChessPiece.PieceColor.Black, new Location(6, 5));
    private Rook whiteRookToCapture2 = new Rook(ChessPiece.PieceColor.White, new Location(4, 2));
    private Rook blackRookToCapture2 = new Rook(ChessPiece.PieceColor.Black, new Location(4, 5));

    private Bishop whiteBlockingBishop = new Bishop(ChessPiece.PieceColor.White, new Location(5, 5));
    private Bishop blackBlockingBishop = new Bishop(ChessPiece.PieceColor.Black, new Location(5, 2));

    private ChessGame game;

    private ChessGame promotionGame;
    private ChessGame castleGame;
    private ChessGame enPassantGame;

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
        pieces.add(whiteRookToCapture2);
        pieces.add(blackRookToCapture2);

        pieces.add(whiteBlockingBishop);
        pieces.add(blackBlockingBishop);

        pieces.add(new King(ChessPiece.PieceColor.Black, new Location(0, 7)));
        pieces.add(new King(ChessPiece.PieceColor.White, new Location(0, 0)));
        game = new ChessGame(new ChessBoard(pieces));

        ArrayList<ChessPiece> promotionPieces = new ArrayList<>();

        promotionPieces.add(new Pawn(ChessPiece.PieceColor.White, new Location(0, 1)));
        promotionPieces.add(new Pawn(ChessPiece.PieceColor.Black, new Location(0, 6)));
        promotionPieces.add(new King(ChessPiece.PieceColor.White, new Location(7, 6)));
        promotionPieces.add(new King(ChessPiece.PieceColor.Black, new Location(7, 1)));

        promotionGame = new ChessGame(new ChessBoard(promotionPieces));

        ArrayList<ChessPiece> castlePieces = new ArrayList<>();

        castlePieces.add(new King(ChessPiece.PieceColor.Black, new Location(4, 0)));
        castlePieces.add(new King(ChessPiece.PieceColor.White, new Location(4, 7)));
        castlePieces.add(new Rook(ChessPiece.PieceColor.Black, new Location(0, 0)));
        castlePieces.add(new Rook(ChessPiece.PieceColor.Black, new Location(7, 0)));
        castlePieces.add(new Rook(ChessPiece.PieceColor.White, new Location(0, 7)));
        castlePieces.add(new Rook(ChessPiece.PieceColor.White, new Location(7, 7)));

        castleGame = new ChessGame(new ChessBoard(castlePieces));

        enPassantGame = new ChessGame();
        enPassantGame.playMove(new Location(1, 6), new Location(1, 4));
        enPassantGame.playMove(new Location(6, 1), new Location(6, 3));

        enPassantGame.playMove(new Location(1, 4), new Location(1, 3));
        enPassantGame.playMove(new Location(6, 3), new Location(6, 4));

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
        whiteRookToCapture2 = null;
        blackRookToCapture2 = null;
        whiteBlockingBishop = null;
        blackBlockingBishop = null;
        game = null;
        promotionGame = null;
        castleGame = null;
        enPassantGame = null;
    }

    @Test
    public void constructors() throws Exception {
        Pawn pawn1 = new Pawn(ChessPiece.PieceColor.Black, new Location(0, 0));
        Assert.assertNotNull(pawn1);
        Pawn pawn2 = new Pawn(ChessPiece.PieceColor.Black, new Location(1, 1), true);
        Assert.assertNotNull(pawn2);
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
    public void promotionCheck() throws Exception {
        promotionGame.playMove(new Location(0, 1), new Location(0, 0));
        Assert.assertTrue(promotionGame.getBoard().getPieceAtLocation(new Location(0, 0)) instanceof Queen);

        promotionGame.playMove(new Location(0, 6), new Location(0, 7));
        Assert.assertTrue(promotionGame.getBoard().getPieceAtLocation(new Location(0, 7)) instanceof Queen);
    }

    @Test
    public void castleCheck() throws Exception {
        ChessGame cloneGame = (ChessGame) castleGame.clone();

        cloneGame.playMove(new Location(4, 7), new Location(2, 7));
        cloneGame.playMove(new Location(4, 0), new Location(6, 0));
        Assert.assertNull(cloneGame.getBoard().getPieceAtLocation(new Location(4, 7)));
        Assert.assertNull(cloneGame.getBoard().getPieceAtLocation(new Location(4, 0)));
        Assert.assertNull(cloneGame.getBoard().getPieceAtLocation(new Location(0, 7)));
        Assert.assertNull(cloneGame.getBoard().getPieceAtLocation(new Location(7, 0)));

        Assert.assertTrue(cloneGame.getBoard().getPieceAtLocation(new Location(2, 7)) instanceof King);
        Assert.assertTrue(cloneGame.getBoard().getPieceAtLocation(new Location(6, 0)) instanceof King);
        Assert.assertTrue(cloneGame.getBoard().getPieceAtLocation(new Location(3, 7)) instanceof Rook);
        Assert.assertTrue(cloneGame.getBoard().getPieceAtLocation(new Location(5, 0)) instanceof Rook);

        cloneGame = (ChessGame) castleGame.clone();
        cloneGame.playMove(new Location(4, 7), new Location(6, 7));
        cloneGame.playMove(new Location(4, 0), new Location(2, 0));
        Assert.assertNull(cloneGame.getBoard().getPieceAtLocation(new Location(4, 7)));
        Assert.assertNull(cloneGame.getBoard().getPieceAtLocation(new Location(4, 0)));
        Assert.assertNull(cloneGame.getBoard().getPieceAtLocation(new Location(7, 7)));
        Assert.assertNull(cloneGame.getBoard().getPieceAtLocation(new Location(0, 0)));

        Assert.assertTrue(cloneGame.getBoard().getPieceAtLocation(new Location(6, 7)) instanceof King);
        Assert.assertTrue(cloneGame.getBoard().getPieceAtLocation(new Location(2, 0)) instanceof King);
        Assert.assertTrue(cloneGame.getBoard().getPieceAtLocation(new Location(5, 7)) instanceof Rook);
        Assert.assertTrue(cloneGame.getBoard().getPieceAtLocation(new Location(3, 0)) instanceof Rook);
    }

    @Test
    public void enPassantCheck() throws Exception {
        ChessGame captureRight = (ChessGame) enPassantGame.clone();
        ChessGame captureLeft = (ChessGame) enPassantGame.clone();

        //right
        captureRight.playMove(new Location(5, 6), new Location(5, 4));
        captureRight.playMove(new Location(6, 4), new Location(5, 5));
        Assert.assertNull(captureRight.getBoard().getPieceAtLocation(new Location(5, 4)));
        Assert.assertTrue(captureRight.getBoard().getPieceAtLocation(new Location(5, 5)) instanceof Pawn);

        //just to change player
        captureRight.playMove(new Location(0, 6), new Location(0, 5));

        captureRight.playMove(new Location(2, 1), new Location(2, 3));
        captureRight.playMove(new Location(1, 3), new Location(2, 2));
        Assert.assertNull(captureRight.getBoard().getPieceAtLocation(new Location(2, 3)));
        Assert.assertTrue(captureRight.getBoard().getPieceAtLocation(new Location(2, 2)) instanceof Pawn);

        //left
        captureLeft.playMove(new Location(7, 6), new Location(7, 4));
        captureLeft.playMove(new Location(6, 4), new Location(7, 5));
        Assert.assertNull(captureLeft.getBoard().getPieceAtLocation(new Location(7, 4)));
        Assert.assertTrue(captureLeft.getBoard().getPieceAtLocation(new Location(7, 5)) instanceof Pawn);

        //just to change player
        captureLeft.playMove(new Location(0, 6), new Location(0, 5));

        captureLeft.playMove(new Location(0, 1), new Location(0, 3));
        captureLeft.playMove(new Location(1, 3), new Location(0, 2));
        Assert.assertNull(captureLeft.getBoard().getPieceAtLocation(new Location(0, 3)));
        Assert.assertTrue(captureLeft.getBoard().getPieceAtLocation(new Location(0, 2)) instanceof Pawn);

    }

    @Test
    public void potentialMoves() throws Exception {

        //need a no move too from starting position
        Assert.assertEquals(whiteSingleMovePawn.potentialMoves(game).size(), 1);
        Assert.assertEquals(blackSingleMovePawn.potentialMoves(game).size(), 1);

        Assert.assertEquals(whiteDoubleMovePawn.potentialMoves(game).size(), 2);
        Assert.assertEquals(blackDoubleMovePawn.potentialMoves(game).size(), 2);

        Assert.assertEquals(whiteCaptureRightPawn.potentialMoves(game).size(), 2);
        Assert.assertEquals(blackCaptureLeftPawn.potentialMoves(game).size(), 2);

        Assert.assertEquals(whiteSingleMovePawn.potentialMoves(game).get(0).getTo(), new Location(1,4));
        Assert.assertEquals(blackSingleMovePawn.potentialMoves(game).get(0).getTo(), new Location(1,3));

        Assert.assertEquals(whiteDoubleMovePawn.potentialMoves(game).get(0).getTo(), new Location(2,5));
        Assert.assertEquals(whiteDoubleMovePawn.potentialMoves(game).get(1).getTo(), new Location(2,4));
        Assert.assertEquals(blackDoubleMovePawn.potentialMoves(game).get(0).getTo(), new Location(2,2));
        Assert.assertEquals(blackDoubleMovePawn.potentialMoves(game).get(1).getTo(), new Location(2,3));

        Assert.assertEquals(whiteCaptureRightPawn.potentialMoves(game).get(0).getTo(), new Location(6,5));
        Assert.assertEquals(blackCaptureLeftPawn.potentialMoves(game).get(0).getTo(), new Location(4,2));
        Assert.assertEquals(whiteCaptureRightPawn.potentialMoves(game).get(1).getTo(), new Location(4,5));
        Assert.assertEquals(blackCaptureLeftPawn.potentialMoves(game).get(1).getTo(), new Location(6,2));
    }

    @Test
    public void validMoves() throws Exception {

    }

}