package Chess;

import Chess.Pieces.ChessPiece;
import Chess.Pieces.King;
import Chess.Pieces.Rook;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Created by konzy on 3/13/2017.
 */
public class ChessGameTest {
    private ChessGame game;
    private ChessGame stalemate;
    private ChessGame checkMate;
    private ChessGame initialGame = new ChessGame();

    @Before
    public void setUp() throws Exception {
        game = new ChessGame();
        ArrayList<ChessPiece> stalematePieces = new ArrayList<>();
        stalematePieces.add(new King(ChessPiece.PieceColor.White,new Location(0, 0)));
        stalematePieces.add(new King(ChessPiece.PieceColor.Black,new Location(7, 7)));
        stalematePieces.add(new Rook(ChessPiece.PieceColor.Black,new Location(5, 1)));
        stalematePieces.add(new Rook(ChessPiece.PieceColor.Black,new Location(1, 5)));
        stalemate = new ChessGame(new ChessBoard(stalematePieces));
        ArrayList<ChessPiece> checkMatePieces = new ArrayList<>();
        checkMatePieces.add(new King(ChessPiece.PieceColor.White,new Location(0, 0)));
        checkMatePieces.add(new King(ChessPiece.PieceColor.Black,new Location(7, 7)));
        checkMatePieces.add(new Rook(ChessPiece.PieceColor.Black,new Location(0, 5)));
        checkMatePieces.add(new Rook(ChessPiece.PieceColor.Black,new Location(1, 5)));
        checkMate = new ChessGame(new ChessBoard(checkMatePieces));
        ChessGame test = new ChessGame(null);
    }

    @After
    public void tearDown() throws Exception {
        game = null;
        stalemate = null;
        checkMate = null;
    }

    @Test
    public void constructors() throws Exception {
        ChessGame gameConstructor1 = new ChessGame();
        ChessGame gameConstructor2 = new ChessGame(new ChessBoard());
        ChessGame gameConstructor3 = new ChessGame(true);
        ChessGame gameConstructor4 = new ChessGame(new ChessBoard(), true);

        Assert.assertNotNull(gameConstructor1);
        Assert.assertNotNull(gameConstructor2);
        Assert.assertNotNull(gameConstructor3);
        Assert.assertNotNull(gameConstructor4);
    }

    @Test
    public void setCurrentPlayer() throws Exception {
        ChessGame currentPlayerGame = new ChessGame();
        currentPlayerGame.setCurrentPlayer(ChessPiece.PieceColor.Black);

        Assert.assertEquals(currentPlayerGame.getCurrentPlayer(), ChessPiece.PieceColor.Black);
    }

    @Test
    public void getPiecesWeThreaten() throws Exception {
        Assert.assertEquals(game.getPiecesWeThreaten(ChessPiece.PieceColor.White).size(), 0);
    }

    @Test
    public void getBoard() throws Exception {
        Assert.assertNotNull(game.getBoard());
    }

    @Test
    public void getCurrentPlayer() throws Exception {
        Assert.assertEquals(game.getCurrentPlayer(), ChessPiece.PieceColor.White);
        game.playMove(new Location(0, 6), new Location(0, 5));
        Assert.assertEquals(game.getCurrentPlayer(), ChessPiece.PieceColor.Black);
    }

    @Test
    public void isColorInCheck() throws Exception {
        Assert.assertFalse(game.isColorInCheck(ChessPiece.PieceColor.Black));
        Assert.assertFalse(game.isColorInCheck(ChessPiece.PieceColor.White));
    }

    @Test
    public void getAllValidMoves() throws Exception {
        Assert.assertEquals(initialGame.getAllValidMoves(ChessPiece.PieceColor.White).size(), 20);
    }

    @Test
    public void getPotentialMoves() throws Exception {
        Assert.assertEquals(initialGame.getPotentialMoves(ChessPiece.PieceColor.White).size(), 20);
    }

    @Test
    public void playMove() throws Exception {
        ChessGame clone = (ChessGame) game.clone();
        Assert.assertTrue(game.playMove(new Location(0, 6), new Location(0, 5)));
        Assert.assertNotEquals(game, clone);
        Assert.assertFalse(game.playMove(new Location(0, 0), new Location(-1, -1)));
        game.playMove(new Location(0, 6), new Location(0, 5));
        Assert.assertTrue(game.playMove(new Location(0, 1), new Location(0, 2)));
    }

    @Test
    public void getState() throws Exception {
        System.out.println();
        Assert.assertEquals(game.getState(), ChessGame.GameState.PLAY);
        Assert.assertEquals(checkMate.getState(), ChessGame.GameState.CHECKMATE);
        Assert.assertEquals(stalemate.getState(), ChessGame.GameState.STALEMATE);
    }

    @Test
    public void testToString() throws Exception {
        String startGameString = "[R0][N0][B0][Q0][K0][B0][N0][R0]\n" +
                "[P0][P0][P0][P0][P0][P0][P0][P0]\n" +
                "[  ][  ][  ][  ][  ][  ][  ][  ]\n" +
                "[  ][  ][  ][  ][  ][  ][  ][  ]\n" +
                "[  ][  ][  ][  ][  ][  ][  ][  ]\n" +
                "[  ][  ][  ][  ][  ][  ][  ][  ]\n" +
                "[p0][p0][p0][p0][p0][p0][p0][p0]\n" +
                "[r0][n0][b0][q0][k0][b0][n0][r0]\n" +
                "White\n" +
                "true\n";

        Assert.assertEquals(initialGame.toString(), startGameString);

    }
}