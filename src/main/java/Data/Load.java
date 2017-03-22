package Data;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.*;

import java.io.*;
import java.util.ArrayList;

import static Data.Save.FILE_LOCATOR;

/**
 * Loads files from a static txt file, starting on the turn where the players left off on and puts the current moves
 * into the autosave for replay purposes.
 */
public class Load {

    private static final String UPPER_CASE_REGEX = "[A-Z]";

    public static ChessGame Load(String fileStr, ChessGame game) {
        File loadFile = new File(FILE_LOCATOR.baseFileLocation.substring(0,
                FILE_LOCATOR.baseFileLocation.length()) + "/resources/main/" + fileStr + ".txt");

        ArrayList<ChessPiece> pieces = new ArrayList<>();
        BufferedReader input = null;
        ChessPiece.PieceColor currentPlayer = ChessPiece.PieceColor.White;

        try {
            /* FileInputStream to read streams */
            input = new BufferedReader(new FileReader(loadFile));
            String line;

            int y = 0;
            while ((line = input.readLine()) != null) {
                if(y == 8) {
                    if(line.equals(ChessPiece.PieceColor.White.name())){
                        currentPlayer = ChessPiece.PieceColor.White;
                    } else {
                        currentPlayer = ChessPiece.PieceColor.Black;
                    }

                } else {
                    line = line.replace("[", "");
                    line = line.replace("]", "");
                    for (int x = 0; x < 8; x++) {
                        String s = String.valueOf(line.charAt(x));
                        ChessPiece.PieceColor color;
                        if (s.matches(UPPER_CASE_REGEX)) {
                            color = ChessPiece.PieceColor.Black;
                        } else {
                            color = ChessPiece.PieceColor.White;
                        }
                        ChessPiece piece = null;

                        Location location = new Location(x, y);
                        switch (s.toUpperCase()) {
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
                }
                y = (y + 1) % 9;
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
        game = new ChessGame(chessBoard);
        game.setCurrentPlayer(currentPlayer);
        return game;
    }
}