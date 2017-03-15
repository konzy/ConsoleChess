package Data;

import Chess.ChessGame;
import Chess.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static Data.Save.BASE_SAVE_LOCATION;
import static org.junit.Assert.*;

/**
 * Created by Ryan on 3/11/2017.
 */
public class TestSave {
    private ChessGame boardToSave;
    @Before
    public void setup() throws Exception {
        Save.clearAutoSave();
        boardToSave = new ChessGame();
        boardToSave.playMove(new Location(0, 6), new Location(0, 4));
    }

    @After
    public void cleanup(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new
                    FileWriter(BASE_SAVE_LOCATION + "src\\Data\\testFiles" +
                    "\\saveTestFile.txt"));
            writer.append("");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void clearAutoSave() throws Exception {
        String expected = "";
        Save.clearAutoSave();
        File autoSaveFile = new File(BASE_SAVE_LOCATION +
                "src\\Data\\Autosave.txt");
        InputStream inputAutosave = new FileInputStream(autoSaveFile);
        String resultStr = "";
        int bytesRead;
        while((bytesRead = inputAutosave.read(new byte[1024])) > 0) {
            resultStr = resultStr + bytesRead;
        }
        assertEquals(expected,resultStr);
    }

    @Test
    public void autoSave() throws Exception {
        Save.autoSave(boardToSave);
        File expectedAutoSaveFile = new File(BASE_SAVE_LOCATION +
                "src\\Data\\testFiles\\autoSaveTestFile.txt");

        BufferedReader expectedAutosaveInput = new BufferedReader (new FileReader(expectedAutoSaveFile));
        String expectedStr = "";
        String line;
        while((line = expectedAutosaveInput.readLine())!= null){
            expectedStr = expectedStr + line + "\n";
        }

        File autoSaveFile = new File(BASE_SAVE_LOCATION +
                "src\\Data\\Autosave.txt");
        BufferedReader inputAutosave = new BufferedReader (new FileReader(autoSaveFile));

        String resultStr = "";
        while((line = inputAutosave.readLine())!= null){
            resultStr = resultStr + line + "\n";
        }

       assertEquals(expectedStr, resultStr);
    }

    @Test
    public void save() throws Exception {
        Save.autoSave(boardToSave);
        Save.save("AutoSave","testFiles\\saveTestFile");
        File autoSaveFile = new File(BASE_SAVE_LOCATION +
                "src\\Data\\Autosave.txt");
        BufferedReader inputAutosave = new BufferedReader (new FileReader(autoSaveFile));
        String line;
        String expectedStr = "";
        while((line = inputAutosave.readLine())!= null){
            expectedStr = expectedStr + line + "\n";
        }

        File expectedAutoSaveFile = new File(BASE_SAVE_LOCATION +
                "src\\Data\\testFiles\\saveTestFile.txt");
        BufferedReader expectedAutosaveInput = new BufferedReader (new FileReader(expectedAutoSaveFile));
        String resultStr = "";
        while((line = expectedAutosaveInput.readLine())!= null){
            resultStr = resultStr + line + "\n";
        }
        assertEquals(expectedStr, resultStr);
    }
}