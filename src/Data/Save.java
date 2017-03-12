package Data;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Pieces.ChessPiece;
import Chess.Tile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Save system for replaying and retaining moves.
 */



public class Save {

    public static final String BASE_SAVE_LOCATION = "C:\\Users\\konzy\\IdeaProjects\\ConsoleChess\\";

    public enum Tags{
        BLACK("[B]"),
        WHITE("[W]"),
        BLANK("[ ]");
        private String value;
        Tags(String value){
                this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Clears out the current autosave to allow for a new game to write to the autosave file
     */

    public static void clearAutoSave(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new
                    FileWriter(BASE_SAVE_LOCATION + "src\\Data\\AutoSave.txt"));
            writer.append("");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Autosaves the current save to the board
     *
     * @param game
     * @throws IOException
     */
    public static void autoSave(ChessGame game) throws IOException {
        BufferedWriter autoSaveFile = new BufferedWriter(
                new FileWriter(BASE_SAVE_LOCATION + "src\\Data\\AutoSave.txt",
                        true));
        Tile[][] currentBoard = game.getBoard().getBoardArray();
        autoSaveFile.append(game.getCurrentPlayer().name());
        autoSaveFile.newLine();
        autoSaveFile.flush();
        for(int i = 0; i < currentBoard.length; i++) {
            for (int x = 0; x < currentBoard[i].length; x++) {
                if (currentBoard[x][i] != null) {
                    autoSaveFile.append(currentBoard[x][i].toString());
                    if (currentBoard[x][i].getPiece().color() == ChessPiece.PieceColor.Black) {
                        autoSaveFile.append(Tags.BLACK.getValue());
                    } else if (currentBoard[x][i].getPiece().color() == ChessPiece.PieceColor.White) {
                        autoSaveFile.append(Tags.WHITE.getValue());
                    }
                } else {
                    autoSaveFile.append(Tags.BLANK.getValue());
                    autoSaveFile.append(Tags.BLANK.getValue());

                }
                if (x == 7) {
                    autoSaveFile.newLine();
                }
                autoSaveFile.flush();
            }
        }
        autoSaveFile.flush();
        autoSaveFile.close();
    }


    /**
     *  Save takes the fromStr file and copies it into the toStr file
     *
     *  This is usually used for taking the auto save and writing it to a new file or taking a load file and loading
     *  it into the auto
     *
     * @param fromStr
     * @param toStr
     * @throws IOException
     */
    public static void save(String fromStr,String toStr) throws IOException {
        File autoSaveFile = new File(BASE_SAVE_LOCATION + "\\src\\Data\\" +
                fromStr + ".txt");
        File saveFile = new File(BASE_SAVE_LOCATION + "\\src\\Data\\" +
                toStr + ".txt");

        if(!saveFile.exists()){
            saveFile.createNewFile();
        }
        try {
            /* FileInputStream to read streams */
            InputStream input = new FileInputStream(autoSaveFile);
            /* FileOutputStream to write streams */
            OutputStream output = new FileOutputStream(saveFile);

            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
            output.flush();
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}