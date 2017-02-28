package Data;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.*;
import GUI.GameBoard;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 * Replays all moves from the start of the game using the current autosave file as reference
 */
public class Replay {
    public static void replayConsole(){
        File loadFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\AutoSave.txt");
        BufferedReader input = null;
        try {
			/* FileInputStream to read streams */
            input = new BufferedReader(new FileReader(loadFile));
            String line;
            String[] lineArray;
            int count = 1;
            while((line = input.readLine()) != null){
                lineArray = line.split("\\]");
                for(int i = 0; i < lineArray.length; i = i+2){
                    System.out.print("[" + lineArray[i].substring(1,2) + "]");
                }
                System.out.println("");
                if(count%8 == 0) {
                    try {
                        System.out.println("");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count++;
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

    public static void replayGUI(GameBoard gameBoard, Stage stage){
        File loadFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\AutoSave.txt");
        BufferedReader input = null;
        try {
			/* FileInputStream to read streams */
            ArrayList<ChessPiece> pieces = new ArrayList<>();
            input = new BufferedReader(new FileReader(loadFile));
            String line;
            String[] lineArray;
            int y = 0;
            while((line = input.readLine()) != null) {
                lineArray = line.split("\\]");
                if (y == 0) {
                    pieces = new ArrayList<>();
                }
                for (int x = 0; x < lineArray.length; x = x + 2) {
                    ChessPiece.PieceColor color;
                    if (lineArray[x + 1].substring(1, 2).equals("B")) {
                        color = ChessPiece.PieceColor.Black;
                    } else {
                        color = ChessPiece.PieceColor.White;
                    }
                    ChessPiece piece = null;
                    Location location = new Location(x / 2, y);
                    switch (lineArray[x].substring(1, 2)) {
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
                if(y == 0){
                    ChessBoard chessBoard = new ChessBoard(pieces);
                    gameBoard.setGame(new ChessGame(chessBoard));
                    System.out.println(gameBoard.toString());
                    gameBoard.setBoard(stage);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
}
