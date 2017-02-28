package Data;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Loads files from a static txt file, starting on the turn where the players left off on and puts the current moves
 * into the autosave for replay purposes.
 */
public class Load {
    public static ChessBoard Load(String fileStr, ChessGame game) {
        File loadFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\" + fileStr + ".txt");
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        BufferedReader input = null;

        try {
            /* FileInputStream to read streams */
            input = new BufferedReader(new FileReader(loadFile));
            String line;
            String[] lineArray;
            int y = 0;
            while ((line = input.readLine()) != null) {
                if(y == 0) {
                    pieces = new ArrayList<>();
                }
                lineArray = line.split("\\]");
                for (int x = 0; x < lineArray.length; x = x + 2) {
                    ChessPiece.PieceColor color;
                    if (lineArray[x + 1].substring(1,2).equals("B")) {
                        color = ChessPiece.PieceColor.Black;
                    } else {
                        color = ChessPiece.PieceColor.White;
                    }
                    ChessPiece piece = null;
                    Location location = new Location(x / 2, y);
                    switch (lineArray[x].substring(1,2)) {
                        case Pawn.LETTER:
                            piece = new Pawn(color, location);
                            break;
                        case Knight.LETTER:
                            piece = new Knight(color, location);
                            break;
                        case Bishop.LETTER:
                            piece = new Bishop(color, location);
                            break;
                        case Queen.LETTER:
                            piece = new Queen(color, location);
                            break;
                        case King.LETTER:
                            piece = new King(color, location);
                            break;
                        case Rook.LETTER:
                            piece = new Rook(color, location);
                            break;
                    }
                    if (piece != null) {
                        pieces.add(piece);
                    }
                }
                y = (y + 1) % 8;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Save.save(fileStr, "AutoSave");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ChessBoard chessBoard = new ChessBoard(pieces);
        return chessBoard;
    }
}