package Data;


import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 3/11/2017.
 */
public class TestSave {
    ChessGame boardToSave;
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
        boardToSave = new ChessGame(new ChessBoard(pieces));
        boardToSave.setCurrentPlayer(ChessPiece.PieceColor.Black);
    }

    @After
    public void cleanup(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new
                    FileWriter("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\testFiles" +
                    "\\saveTestFile.txt"));
            writer.append("");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void clearAutoSave() throws Exception {
        String expected = "";
        Save.clearAutoSave();
        File autoSaveFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\" +
                "src\\Data\\Autosave.txt");
        InputStream inputAutosave = new FileInputStream(autoSaveFile);
        String resultStr = "";
        int bytesRead;
        while((bytesRead = inputAutosave.read(new byte[1024])) > 0) {
            resultStr = resultStr + bytesRead;
        }
        assertEquals(expected,resultStr);
    }

    @Test
    public void autoSave() throws Exception {
        Save.autoSave(boardToSave);
        File expectedAutoSaveFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\" +
                "src\\Data\\testFiles\\autoSaveTestFile.txt");
        BufferedReader expectedAutosaveInput = new BufferedReader (new FileReader(expectedAutoSaveFile));
        String expectedStr = "";
        String line;
        while((line = expectedAutosaveInput.readLine())!= null){
            expectedStr = expectedStr + line + "\n";
        }

        File autoSaveFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\" +
                "src\\Data\\Autosave.txt");
        BufferedReader inputAutosave = new BufferedReader (new FileReader(autoSaveFile));

        String resultStr = "";
        while((line = inputAutosave.readLine())!= null){
            resultStr = resultStr + line + "\n";
        }

       assertEquals(expectedStr, resultStr);
    }

    @Test
    public void save() throws Exception {
        Save.autoSave(boardToSave);
        Save.save("AutoSave","testFiles\\saveTestFile");
        File autoSaveFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\" +
                "src\\Data\\Autosave.txt");
        BufferedReader inputAutosave = new BufferedReader (new FileReader(autoSaveFile));
        String line;
        String expectedStr = "";
        while((line = inputAutosave.readLine())!= null){
            expectedStr = expectedStr + line + "\n";
        }

        File expectedAutoSaveFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\" +
                "src\\Data\\testFiles\\saveTestFile.txt");
        BufferedReader expectedAutosaveInput = new BufferedReader (new FileReader(expectedAutoSaveFile));
        String resultStr = "";
        while((line = expectedAutosaveInput.readLine())!= null){
            resultStr = resultStr + line + "\n";
        }
        assertEquals(expectedStr, resultStr);
    }
}