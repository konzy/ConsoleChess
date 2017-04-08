package Data;

import Chess.ChessGame;

import java.io.*;

import static Data.FileConstants.FILE_LOCATOR;

/**
 * Save system for replaying and retaining moves.
 */
public class Save {

    /**
     * Clears out the current autosave to allow for a new game to write to the autosave file
     */
    public static void clearAutoSave(){
        BufferedWriter writer;
        File autoSaveFile = new File(FILE_LOCATOR.toString() + "/resources/main/AutoSave.txt");
        try {
            if(!autoSaveFile.exists()){
                autoSaveFile.createNewFile();
            } else {
                writer = new BufferedWriter(new
                        FileWriter(FILE_LOCATOR.toString() + "/resources/main/AutoSave.txt"));
                writer.append("");
                writer.flush();
                writer.close();
            }
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
                new FileWriter(FILE_LOCATOR.toString() + "/resources/main/AutoSave.txt",
                        true));

        autoSaveFile.append(game.toString());
        autoSaveFile.newLine();
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
        File autoSaveFile = new File(FILE_LOCATOR.toString() + "/resources/main/" + fromStr + ".txt");
        File saveFile = new File(FILE_LOCATOR.toString() + "/resources/main/" + toStr + ".txt");

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