package Data;

import Chess.ChessBoard;
import Chess.Tile;

import java.io.*;

/**
 * Created by Ryan on 1/29/2017.
 */

public class Save {

    public static void clearAutoSave(){
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new
                    FileWriter("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\AutoSave.txt"));
            writer.write("");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void AutoSave(ChessBoard board) throws IOException {
        BufferedWriter autoSaveFile = new BufferedWriter(
                new FileWriter("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\AutoSave.txt"));
        Tile[][] currentBoard = board.getBoardArray();
        for(int i =0; i < currentBoard.length; i++){
            for(int x = 0; x < currentBoard[i].length; x++) {
                autoSaveFile.append(currentBoard[i][x].toString());

                if(x==7){
                    autoSaveFile.newLine();
                }
                autoSaveFile.flush();
            }
        }

        autoSaveFile.close();
    }

    public void Save() {
    }
}