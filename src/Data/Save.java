package Data;

import Chess.ChessBoard;
import Chess.Pieces.ChessPiece;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Save system for replaying and retaining moves.
 */

public class Save {
    /**
     *
     */

    private static String ROOT_SAVE_LOCATION = "C:\\Users\\konzy\\IdeaProjects\\ConsoleChess\\src\\Data\\";
    private static String AUTO_SAVE_LOCATION = ROOT_SAVE_LOCATION + "AutoSave.txt";


    public static void clearAutoSave(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new
                    FileWriter(AUTO_SAVE_LOCATION));
            writer.append("");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AutoSave(ChessBoard board) throws IOException {
        BufferedWriter autoSaveFile = new BufferedWriter(new FileWriter(AUTO_SAVE_LOCATION,true));
        ArrayList<ChessPiece> pieces = board.getBoardArrayList();
        Collections.sort(pieces);
        Iterator<ChessPiece> iterator = pieces.iterator();
        ChessPiece piece = null;
        if (iterator.hasNext()) {
            piece = iterator.next();
        }

        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++) {
                if (piece != null && piece.getLocation().X() == x && piece.getLocation().Y() == y) {
                    autoSaveFile.append(piece.getLetter());
                    autoSaveFile.append(piece.getColor().toString().toLowerCase().charAt(0));
                    if (iterator.hasNext()) {
                        piece = iterator.next();
                    }
                } else {
                    autoSaveFile.append("  ");
                }
                if(x == 7){
                    autoSaveFile.newLine();
                }
                autoSaveFile.flush();
            }
        }
        autoSaveFile.flush();
        autoSaveFile.close();
    }

    public static void Save(String fromStr, String toStr) throws IOException {
        File autoSaveFile = new File(ROOT_SAVE_LOCATION + fromStr + ".txt");
        File saveFile = new File(ROOT_SAVE_LOCATION + toStr + ".txt");
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