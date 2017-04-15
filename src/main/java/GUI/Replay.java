package GUI;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.*;
import Data.FileLocator;
import Data.Save;
import GUI.GameBoard;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

import static Data.FileConstants.FILE_LOCATOR;
import static Data.Load.PIECE_MOVED_REGEX;
import static Data.Load.WHITE_PIECE_REGEX;

/**
 * Replays all moves from the start of the game using the current autosave file as reference
 */
public class Replay {

    public static void replayConsole(){
        File loadFile = new File(FILE_LOCATOR.toString() + "\\resources\\main\\AutoSave.txt");
        BufferedReader input = null;
        ChessPiece.PieceColor currentPlayer = ChessPiece.PieceColor.White;
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        try {
			/* FileInputStream to read streams */
            input = new BufferedReader(new FileReader(loadFile));
            String line;
            String[] lineArray;
            int y = 0;
            while ((line = input.readLine()) != null) {
                if(y % 9 == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(line.equals(ChessPiece.PieceColor.Black.name())){
                        System.out.println( ChessPiece.PieceColor.White);
                    } else {
                        System.out.println( ChessPiece.PieceColor.Black);
                    }
                    pieces = new ArrayList<>();
                } else {
                    lineArray = line.split("\\]");
                    for (int i = 0; i < lineArray.length; i = i + 2) {
                        System.out.print("[" + lineArray[i].substring(1, 2) + "]");
                    }
                    System.out.println("");
                }
                y++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ChessGame undoMove(int i,ChessGame game){
        File loadFile = new File(FILE_LOCATOR.toString() +"/resources/main/AutoSave.txt");
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        BufferedReader input = null;
        ChessPiece.PieceColor currentPlayer = ChessPiece.PieceColor.White;
        boolean isTwoPlayer = true;
        int count = 0;

        try {
            /* FileInputStream to read streams */
            input = new BufferedReader(new FileReader(loadFile));
            String line;
            String[] lineArray;
            int y = 0;
            while ((line = input.readLine()) != null && count <= i*10) {
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
                count++;
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
        }
        ChessBoard chessBoard = new ChessBoard(pieces);
        game = new ChessGame(chessBoard, isTwoPlayer);
        game.setCurrentPlayer(currentPlayer);
        return game;
    }


}
