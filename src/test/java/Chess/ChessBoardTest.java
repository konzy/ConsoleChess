package Chess;

import Chess.Pieces.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by konzy on 3/13/2017.
 */
public class ChessBoardTest {
    private ChessBoard board;
    private Bishop testBishop1;
    private Bishop testBishop2;
    private Location bishopOneLocation = new Location(3,3);
    private Location bishopTwoLocation = new Location(3,1);

    private ChessGame initialGame = new ChessGame();

    private ChessPiece blackKingPiece = new King(ChessPiece.PieceColor.Black, new Location(5, 5));
    private ChessPiece whiteKingPiece = new King(ChessPiece.PieceColor.White, new Location(7, 7));

    @Before
    public void setUp() throws Exception {
        testBishop1 = new Bishop(ChessPiece.PieceColor.White, bishopOneLocation);
        testBishop2 = new Bishop(ChessPiece.PieceColor.Black, bishopTwoLocation);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(testBishop1);
        pieces.add(new Pawn(ChessPiece.PieceColor.Black, new Location(0, 2)));
        pieces.add(new Pawn(ChessPiece.PieceColor.Black, new Location(1, 2)));
        pieces.add(new Pawn(ChessPiece.PieceColor.White, new Location(2, 2)));
        pieces.add(new Pawn(ChessPiece.PieceColor.White, new Location(2, 2)));
        pieces.add(new Rook(ChessPiece.PieceColor.White, new Location(5, 6)));
        pieces.add(blackKingPiece);
        pieces.add(whiteKingPiece);
        pieces.add(testBishop2);
        board = new ChessBoard(pieces);
    }
    @After
    public void tearDown() throws Exception {
        board = null;
    }

    @Test
    public void getPieceAtCoord() throws Exception {
        Assert.assertEquals(board.getPieceAtLocation(bishopOneLocation), testBishop1);
        Assert.assertEquals(board.getPieceAtLocation(bishopTwoLocation), testBishop2);
        Assert.assertNull(board.getPieceAtLocation(new Location(-1, -1)));
        Assert.assertNull(board.getPieceAtLocation(new Location(0, 0)));
    }

    @Test
    public void isInsideBoardOneLocation() throws Exception {
        //single location
        Assert.assertTrue(board.isInsideBoard(new Location(1, 1)));
        Assert.assertFalse(board.isInsideBoard(new Location(-1, -1)));
    }

    @Test
    public void isInsideBoardTwoLocations() throws Exception {
        //two locations
        Assert.assertTrue(board.isInsideBoard(new Location(1,1), new Location(0, 0)));
    }

    @Test
    public void removePieceLocation() throws Exception {
        Assert.assertTrue(board.removePiece(bishopOneLocation));
    }

    @Test
    public void removePieceChessPiece() throws Exception {
        Assert.assertTrue(board.removePiece(testBishop2));
    }

    @Test
    public void move() throws Exception {
        ChessBoard clone = (ChessBoard) board.clone();

        board.move(new Move(testBishop1,
                new Location(testBishop1.getLocation().x + 1, testBishop1.getLocation().y + 1)));

        Assert.assertNotEquals(clone, board);
    }

    @Test
    public void testClone() throws Exception {

    }

    @Test
    public void getBoardArrayList() throws Exception {
        Assert.assertNotNull(board.getBoardArrayList());
        Assert.assertNotNull(initialGame.getBoard().getBoardArrayList());
    }

    @Test
    public void getKingPiece() throws Exception {
        Assert.assertEquals(board.getKingPiece(ChessPiece.PieceColor.White), whiteKingPiece);
        Assert.assertEquals(board.getKingPiece(ChessPiece.PieceColor.Black), blackKingPiece);
        Assert.assertEquals(initialGame.getBoard().getKingPiece(ChessPiece.PieceColor.White).getLocation(),
                new Location(4, 7));
        Assert.assertEquals(initialGame.getBoard().getKingPiece(ChessPiece.PieceColor.Black).getLocation(),
                new Location(4, 0));

        ChessBoard tempBoard = new ChessBoard();
        tempBoard.removePiece(new Location(4, 7));

        Assert.assertNull(tempBoard.getKingPiece(ChessPiece.PieceColor.White));
    }

    @Test
    public void getAllPiecesLocationForColor() throws Exception {
        Assert.assertEquals(initialGame.getBoard().getAllPiecesLocationForColor(ChessPiece.PieceColor.White).size(), 16);
    }

    @Test
    public void testToString() throws Exception {
        String startBoardString = "[R][N][B][Q][K][B][N][R]\n" +
                "[P][P][P][P][P][P][P][P]\n" +
                "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[p][p][p][p][p][p][p][p]\n" +
                "[r][n][b][q][k][b][n][r]\n";

        Assert.assertEquals(initialGame.getBoard().toString(), startBoardString);
    }

}