package GUI;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.*;
import Data.FileLocator;
import GUI.GameBoard;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;



/**
 * Replays all moves from the start of the game using the current autosave file as reference
 */
public class Replay {
    public static final FileLocator FILE_LOCATOR = new FileLocator();

    public static void replayConsole(){
        File loadFile = new File(FILE_LOCATOR.baseFileLocation.substring(0,
                FILE_LOCATOR.baseFileLocation.length() - 14) + "\\resources\\AutoSave.txt");

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

//    public static void replayGUI(GameBoard gameBoard, Stage stage) {
//        File loadFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\AutoSave.txt");
//        BufferedReader input = null;
//        ChessPiece.PieceColor currentPlayer = ChessPiece.PieceColor.White;
//        ArrayList<ChessPiece> pieces = new ArrayList<>();
//        try {
//			/* FileInputStream to read streams */
//            input = new BufferedReader(new FileReader(loadFile));
//            String line;
//            String[] lineArray;
//            int y = 0;
//            while ((line = input.readLine()) != null) {
//                if (y == 0) {
//                    if (line.equals(ChessPiece.PieceColor.White.name())) {
//                        currentPlayer = ChessPiece.PieceColor.White;
//                    } else {
//                        currentPlayer = ChessPiece.PieceColor.Black;
//                    }
//                    pieces = new ArrayList<>();
//                } else {
//                    lineArray = line.split("\\]");
//                    for (int i = 0; i < lineArray.length; i = i + 2) {
//                        System.out.print("[" + lineArray[i].substring(1, 2) + "]");
//                    }
//                    System.out.println("");
//                    if (y % 9 == 0) {
//                        try {
//                            System.out.println("");
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    y++;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != input) {
//                try {
//                    input.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
