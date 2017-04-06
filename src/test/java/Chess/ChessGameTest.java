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
    public void getBoard() throws Exception {
        Assert.assertNotNull(game.getBoard());
    }

    @Test
    public void failure() throws Exception {
        Assert.assertEquals(1, 2);
    }

    @Test
    public void getCurrentPlayer() throws Exception {
        Assert.assertEquals(game.getCurrentPlayer(), ChessPiece.PieceColor.White);
        game.playMove(new Location(0, 6), new Location(0, 5));
        Assert.assertEquals(game.getCurrentPlayer(), ChessPiece.PieceColor.Black);
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
        String startGameString = "[R][N][B][Q][K][B][N][R]\n" +
                "[P][P][P][P][P][P][P][P]\n" +
                "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[p][p][p][p][p][p][p][p]\n" +
                "[r][n][b][q][k][b][n][r]\n" +
                "White\n";

        Assert.assertEquals(initialGame.toString(), startGameString);

    }
}