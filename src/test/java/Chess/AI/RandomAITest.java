package Chess.AI;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by konzy on 2/19/2017.
 */
public class RandomAITest {
    private RandomAI randomAI;
    private ChessGame normalGame;
    private ChessGame stalemateGame;
    private ChessGame checkmateGame;
    private ChessBoard board;
    private ArrayList<ChessPiece> pieces;

    @Before
    public void setUp() throws Exception {
        RandomAI seedAI = new RandomAI(new ChessGame(), 12345);

        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(new Bishop(ChessPiece.PieceColor.White, new Location(1,1)));
        pieces.add(new Pawn(ChessPiece.PieceColor.Black, new Location(0,2)));
        pieces.add(new Pawn(ChessPiece.PieceColor.Black, new Location(1,2)));
        pieces.add(new Pawn(ChessPiece.PieceColor.White, new Location(2,2)));
        pieces.add(new Pawn(ChessPiece.PieceColor.White, new Location(2,1)));
        pieces.add(new King(ChessPiece.PieceColor.Black, new Location(5, 5)));
        pieces.add(new King(ChessPiece.PieceColor.White, new Location(7, 7)));
        pieces.add(new Bishop(ChessPiece.PieceColor.Black, new Location(3,1)));
        board = new ChessBoard(pieces);
        normalGame = new ChessGame();
        randomAI = new RandomAI(normalGame);

        pieces.clear();
        pieces.add(new Rook(ChessPiece.PieceColor.Black, new Location(1,2)));
        pieces.add(new Rook(ChessPiece.PieceColor.Black, new Location(2,1)));
        pieces.add(new King(ChessPiece.PieceColor.White, new Location(0, 0)));
        pieces.add(new King(ChessPiece.PieceColor.Black, new Location(7, 7)));
        stalemateGame = new ChessGame(new ChessBoard(pieces));

        pieces.clear();
        pieces.add(new Rook(ChessPiece.PieceColor.Black, new Location(2,0)));
        pieces.add(new Rook(ChessPiece.PieceColor.Black, new Location(2,1)));
        pieces.add(new King(ChessPiece.PieceColor.White, new Location(0, 0)));
        pieces.add(new King(ChessPiece.PieceColor.Black, new Location(7, 7)));
        checkmateGame = new ChessGame(new ChessBoard(pieces));

    }

    @After
    public void tearDown() throws Exception {
        RandomAI randomAI = null;
        ChessGame normalGame = null;
        ChessGame stalemateGame = null;
        ChessGame checkmateGame = null;
        ChessBoard board = null;
        ArrayList<ChessPiece> pieces = null;
    }

    @Test
    public void getNextMove() throws Exception {
        randomAI = new RandomAI(normalGame);
        Assert.assertNotNull(randomAI.getNextMove());

        randomAI = new RandomAI(stalemateGame);
        Assert.assertNull(randomAI.getNextMove());

        randomAI = new RandomAI(checkmateGame);
        Assert.assertNull(randomAI.getNextMove());
    }

}