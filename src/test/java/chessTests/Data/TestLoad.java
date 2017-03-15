package chessTests.Data;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.*;
import Data.Load;
import Data.Save;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 2/28/2017.
 */
public class TestLoad {
    ChessGame expected;
    @Before
    public void setup() throws Exception {
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        // Pawns
        pieces.add(new Pawn(ChessPiece.PieceColor.White, new Location(0,4)));
        for(int i = 0; i < 8; i++){
            pieces.add(new Pawn(ChessPiece.PieceColor.Black, new Location(i, 1)));
            if(i != 0) {
                pieces.add(new Pawn(ChessPiece.PieceColor.White, new Location(i, 6)));
            }
        }

        // Rooks
        pieces.add(new Rook(ChessPiece.PieceColor.Black, new Location(0, 0)));
        pieces.add(new Rook(ChessPiece.PieceColor.Black, new Location(7, 0)));
        pieces.add(new Rook(ChessPiece.PieceColor.White, new Location(0, 7)));
        pieces.add(new Rook(ChessPiece.PieceColor.White, new Location(7, 7)));

        // Knight
        pieces.add(new Knight(ChessPiece.PieceColor.Black, new Location(1, 0)));
        pieces.add(new Knight(ChessPiece.PieceColor.Black, new Location(6, 0)));
        pieces.add(new Knight(ChessPiece.PieceColor.White, new Location(1, 7)));
        pieces.add(new Knight(ChessPiece.PieceColor.White, new Location(6, 7)));

        // Bishop
        pieces.add(new Bishop(ChessPiece.PieceColor.Black, new Location(2, 0)));
        pieces.add(new Bishop(ChessPiece.PieceColor.Black, new Location(5, 0)));
        pieces.add(new Bishop(ChessPiece.PieceColor.White, new Location(5, 7)));
        pieces.add(new Bishop(ChessPiece.PieceColor.White, new Location(2, 7)));

        // Queens
        pieces.add(new Queen(ChessPiece.PieceColor.Black, new Location(3, 0)));
        pieces.add(new Queen(ChessPiece.PieceColor.White, new Location(3, 7)));

        // Kings
        pieces.add(new King(ChessPiece.PieceColor.Black, new Location(4, 0)));
        pieces.add(new King(ChessPiece.PieceColor.White, new Location(4, 7)));
        expected = new ChessGame(new ChessBoard(pieces));
        expected.setCurrentPlayer(ChessPiece.PieceColor.Black);
    }

    @Test
    public void loadBoardTest() throws Exception {
        ChessGame result = Load.Load("testFiles\\loadTestFile", new ChessGame());
        assertEquals(expected.getBoard().toString(), result.getBoard().toString());
    }

    @Test
    public void loadAutoSaveLoaded() throws Exception {
        Load.Load("testFiles\\loadTestFile", new ChessGame());
        File autoSaveFile = new File(Save.BASE_SAVE_LOCATION + "\\Autosave.txt");
        File expectedFileString = new File (Save.BASE_SAVE_LOCATION + "\\testFiles\\loadTestFile.txt");
        InputStream inputAutosave = new FileInputStream(autoSaveFile);
        InputStream expectedAutosave = new FileInputStream(expectedFileString);
        String resultStr = "";
        String expectedStr = "";
        int bytesRead;
        while((bytesRead = inputAutosave.read(new byte[1024])) > 0) {
            resultStr = resultStr + bytesRead;
        }
        while((bytesRead = expectedAutosave.read(new byte[1024])) > 0) {
            expectedStr = expectedStr + bytesRead;
        }
        assertEquals(expectedStr, resultStr);
    }

    @Test
    public void loadTurnTest() throws Exception {
        ChessGame result = Load.Load("testFiles\\loadTestFile", new ChessGame());
        assertEquals(expected.getCurrentPlayer(), result.getCurrentPlayer());
    }
}
