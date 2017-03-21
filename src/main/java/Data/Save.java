package Data;

import Chess.ChessGame;
import java.io.*;

/**
 * Save system for replaying and retaining moves.
 */



public class Save {

    public static final String BASE_SAVE_LOCATION = "C:\\Users\\konzy\\IdeaProjects\\ConsoleChess\\src\\main\\java\\Data\\";

    /**
     * Clears out the current autosave to allow for a new game to write to the autosave file
     */

    public static void clearAutoSave(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new
                    FileWriter(BASE_SAVE_LOCATION + "AutoSave.txt"));
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
                new FileWriter(BASE_SAVE_LOCATION + "AutoSave.txt",
                        true));
        autoSaveFile.append(game.toString());
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
        File autoSaveFile = new File(BASE_SAVE_LOCATION +
                fromStr + ".txt");
        File saveFile = new File(BASE_SAVE_LOCATION +
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