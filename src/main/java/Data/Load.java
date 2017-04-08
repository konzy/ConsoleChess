package Data;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.*;

import java.io.*;
import java.util.ArrayList;

import static Data.FileConstants.FILE_LOCATOR;

/**
 * Loads files from a static txt file, starting on the turn where the players left off on and puts the current moves
 * into the autosave for replay purposes.
 */
public class Load {
    public static final FileLocator FILE_LOCATOR = new FileLocator();
    public static final String WHITE_PIECE_REGEX = "[a-z]";
    public static final String PIECE_MOVED_REGEX = "[1]";


    public static ChessGame Load(String fileStr, ChessGame game) {
        File loadFile = new File(FILE_LOCATOR.toString() +"/resources/main/" + fileStr + ".txt");
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        BufferedReader input = null;
        ChessPiece.PieceColor currentPlayer = ChessPiece.PieceColor.White;
        boolean isTwoPlayer = true;

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

                if(y == 8) {
                    if(line.equals(ChessPiece.PieceColor.White.name())){
                        currentPlayer = ChessPiece.PieceColor.White;
                    } else {
                        currentPlayer = ChessPiece.PieceColor.Black;
                    }
                } else if(y == 9) {
                    isTwoPlayer = Boolean.valueOf(line);
                } else if(y == 10) {
                    //blank on purpose
                } else {
                    lineArray = line.split("\\]");
                    int x = 0;
                    for (String tile : lineArray) {
                        Location location = new Location(x, y);

                        ChessPiece.PieceColor color = ChessPiece.PieceColor.Black;
                        if(tile.substring(1, 2).matches(WHITE_PIECE_REGEX)) {
                            color = ChessPiece.PieceColor.White;
                        }

                        boolean hasMoved = false;
                        if(tile.substring(2, 3).matches(PIECE_MOVED_REGEX)) {
                            hasMoved = true;
                        }

                        String letter = tile.substring(1, 2).toUpperCase();

                        ChessPiece piece = null;
                        switch (letter) {
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
                            piece.setHasMoved(hasMoved);
                            pieces.add(piece);
                        }
                        x++;
                    }
                }
                y = (y + 1) % 11;
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
        game = new ChessGame(chessBoard, isTwoPlayer);
        game.setCurrentPlayer(currentPlayer);
        return game;
    }
}